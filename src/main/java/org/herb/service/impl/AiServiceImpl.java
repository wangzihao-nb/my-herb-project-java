package org.herb.service.impl;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.zhipu.oapi.ClientV4;
import org.herb.utils.zhipu.oapi.Constants;
import com.zhipu.oapi.service.v4.model.*;
import io.reactivex.Flowable;
import org.herb.service.AiService;
import org.herb.service.DiagnosisHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.zhipu.oapi.service.v4.api.ChatApiService.defaultObjectMapper;

@Service
public class AiServiceImpl implements AiService {

    @Autowired
    private DiagnosisHistoryService diagnosisHistoryService;

    private static final String API_KEY = "77ac40f6a6004646825d2561dcf9719d.g60df54MeiEE6Nkj";
    private static final ClientV4 client = new ClientV4.Builder(API_KEY).build();
    private static final ObjectMapper mapper = defaultObjectMapper();
    private static final String requestIdTemplate = "mycompany-%d";

    public static ObjectMapper defaultObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        mapper.addMixIn(ChatFunction.class, ChatFunctionMixIn.class);
        mapper.addMixIn(ChatCompletionRequest.class, ChatCompletionRequestMixIn.class);
        mapper.addMixIn(ChatFunctionCall.class, ChatFunctionCallMixIn.class);
        return mapper;
    }

    private String sseInvoke(String prompt) {
        StringBuilder builder = new StringBuilder();
        List<ChatMessage> messages = new ArrayList<>();
        ChatMessage chatMessage = new ChatMessage(ChatMessageRole.USER.value(), prompt);
        messages.add(chatMessage);
        String requestId = String.format(requestIdTemplate, System.currentTimeMillis());

        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .model(Constants.ModelChatGLM4Flash)
                .stream(Boolean.TRUE)
                .messages(messages)
                .requestId(requestId)
                .build();
        
        ModelApiResponse sseModelApiResp = client.invokeModelApi(chatCompletionRequest);
        if (!sseModelApiResp.isSuccess()) {
            return "抱歉，服务暂时不可用，请稍后再试。";
        }
        if (sseModelApiResp.isSuccess()) {
            AtomicBoolean isFirst = new AtomicBoolean(true);
            ChatMessageAccumulator chatMessageAccumulator = mapStreamToAccumulator(sseModelApiResp.getFlowable())
                    .doOnNext(accumulator -> {
                        {
                            if (isFirst.getAndSet(false)) {
                                System.out.print("Response: ");
                            }
                            if (accumulator.getDelta() != null && accumulator.getDelta().getTool_calls() != null) {
                                String jsonString = mapper.writeValueAsString(accumulator.getDelta().getTool_calls());
                                System.out.println("tool_calls: " + jsonString);
                            }
                            if (accumulator.getDelta() != null && accumulator.getDelta().getContent() != null) {
                                System.out.print(accumulator.getDelta().getContent());
                                builder.append(accumulator.getDelta().getContent());
                            }
                        }
                    })
                    .doOnComplete(System.out::println)
                    .lastElement()
                    .blockingGet();

            Choice choice = new Choice(chatMessageAccumulator.getChoice().getFinishReason(), 0L, chatMessageAccumulator.getDelta());
            List<Choice> choices = new ArrayList<>();
            choices.add(choice);
            ModelData data = new ModelData();
            data.setChoices(choices);
            data.setUsage(chatMessageAccumulator.getUsage());
            data.setId(chatMessageAccumulator.getId());
            data.setCreated(chatMessageAccumulator.getCreated());
            data.setRequestId(chatCompletionRequest.getRequestId());
            sseModelApiResp.setFlowable(null);
            sseModelApiResp.setData(data);
        }
        String answer = builder.toString();
        return answer;
    }

    public static Flowable<ChatMessageAccumulator> mapStreamToAccumulator(Flowable<ModelData> flowable) {
        return flowable.map(chunk -> {
            return new ChatMessageAccumulator(chunk.getChoices().get(0).getDelta(), null, chunk.getChoices().get(0), chunk.getUsage(), chunk.getCreated(), chunk.getId());
        });
    }

    @Override
    public String herbQA(Integer userId, String herbName, String question) {
        String prompt = "你是一位经验丰富的中医药专家。关于中药\"" + herbName + "\"，" + question +
                       "请从中医专业角度详细解答，包括其性味归经、功效主治、用法用量、注意事项等方面。";
        String answer = sseInvoke(prompt);
        // 保存历史记录
        diagnosisHistoryService.addDiagnosisHistory(userId, "herb_qa", herbName + ": " + question, answer);
        return answer;
    }

    @Override
    public String prescriptionAnalysis(Integer userId, String prescriptionData) {
        String prompt = "你是一位资深的中医方剂学专家。请分析以下方剂信息：\n" + prescriptionData +
                       "\n请从以下几个方面进行分析：\n" +
                       "1. 方剂组成分析\n" +
                       "2. 配伍意义\n" +
                       "3. 功效与主治\n" +
                       "4. 配伍禁忌\n" +
                       "5. 使用注意事项";
        String answer = sseInvoke(prompt);
        // 保存历史记录
        diagnosisHistoryService.addDiagnosisHistory(userId, "prescription_analysis", prescriptionData, answer);
        return answer;
    }

    @Override
    public String diagnosis(Integer userId, String symptoms) {
        String prompt = "你是一位经验丰富的中医师。患者描述的症状如下：\n" + symptoms +
                       "\n请基于中医理论进行辨证分析，包括：\n" +
                       "1. 病因分析\n" +
                       "2. 病机分析\n" +
                       "3. 辨证结论\n" +
                       "4. 治疗原则\n" +
                       "5. 推荐方药\n" +
                       "6. 生活调养建议\n\n" +
                       "注意：这只是初步的辨证分析，建议患者到正规中医院就诊。";
        String answer = sseInvoke(prompt);
        // 保存历史记录
        diagnosisHistoryService.addDiagnosisHistory(userId, "diagnosis", symptoms, answer);
        return answer;
    }
}

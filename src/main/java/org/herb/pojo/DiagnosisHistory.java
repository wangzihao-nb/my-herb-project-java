package org.herb.pojo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class DiagnosisHistory {
    private Integer id;
    private Integer userId;
    private String type; // 类型: diagnosis(问诊), herb_qa(中药问答), prescription_analysis(方剂分析)
    private String question;
    private String answer;
    private LocalDateTime createTime;
}

package org.herb.service.impl;

import org.herb.mapper.DiagnosisHistoryMapper;
import org.herb.pojo.DiagnosisHistory;
import org.herb.service.DiagnosisHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiagnosisHistoryServiceImpl implements DiagnosisHistoryService {

    @Autowired
    private DiagnosisHistoryMapper diagnosisHistoryMapper;

    @Override
    public void addDiagnosisHistory(Integer userId, String type, String question, String answer) {
        DiagnosisHistory history = new DiagnosisHistory();
        history.setUserId(userId);
        history.setType(type);
        history.setQuestion(question);
        history.setAnswer(answer);
        diagnosisHistoryMapper.add(history);
    }

    @Override
    public List<DiagnosisHistory> getHistoryByUserId(Integer userId) {
        return diagnosisHistoryMapper.findByUserId(userId);
    }

    @Override
    public List<DiagnosisHistory> getHistoryByUserIdAndType(Integer userId, String type) {
        return diagnosisHistoryMapper.findByUserIdAndType(userId, type);
    }

    @Override
    public void deleteHistory(Integer id) {
        diagnosisHistoryMapper.delete(id);
    }
}

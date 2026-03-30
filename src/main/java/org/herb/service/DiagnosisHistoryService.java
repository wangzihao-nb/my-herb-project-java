package org.herb.service;

import org.herb.pojo.DiagnosisHistory;

import java.util.List;

public interface DiagnosisHistoryService {
    void addDiagnosisHistory(Integer userId, String type, String question, String answer);
    List<DiagnosisHistory> getHistoryByUserId(Integer userId);
    List<DiagnosisHistory> getHistoryByUserIdAndType(Integer userId, String type);
    void deleteHistory(Integer id);
}

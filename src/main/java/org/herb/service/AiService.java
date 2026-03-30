package org.herb.service;

public interface AiService {
    String herbQA(Integer userId, String herbName, String question);
    String prescriptionAnalysis(Integer userId, String prescriptionData);
    String diagnosis(Integer userId, String symptoms);
}

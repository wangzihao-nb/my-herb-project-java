package org.herb.controller;

import org.herb.pojo.Result;
import org.herb.service.PcmRecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recommend")
public class PcmRecommendController {
    @Autowired
    private PcmRecommendService pcmRecommendService;

    @GetMapping
    public Result<String> recommend(@RequestParam String question){
        String answer = pcmRecommendService.recommend(question);
        return Result.success(answer);
    }
}

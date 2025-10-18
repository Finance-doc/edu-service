package com.financedoc.edu_service.controller;

import com.financedoc.edu_service.dto.KeywordQuiz;
import com.financedoc.edu_service.dto.KeywordResponse;
import com.financedoc.edu_service.service.KeywordQuizService;
import com.financedoc.edu_service.service.KeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class KeywordController {

    private final KeywordService keywordService;
    private final KeywordQuizService keywordQuizService;

    @GetMapping("/keyword/random")
    public ResponseEntity<?> getRandomKeyword( @RequestParam(required = false) Long currentKeywordId ){
        KeywordResponse randomKeyword = keywordService.getRandomKeyword(currentKeywordId);
        return ResponseEntity.ok(randomKeyword);
    }

    @GetMapping("/keyword/quiz")
    public ResponseEntity<?> getQuizKeyword(){
        List<KeywordQuiz> keywordQuizs = keywordQuizService.generateQuiz(5);
        return ResponseEntity.ok(keywordQuizs);
    }
}

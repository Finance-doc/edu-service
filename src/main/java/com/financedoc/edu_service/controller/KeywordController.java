package com.financedoc.edu_service.controller;

import com.financedoc.edu_service.dto.KeywordResponse;
import com.financedoc.edu_service.service.KeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KeywordController {

    private final KeywordService keywordService;

    @GetMapping("/keyword/random")
    public ResponseEntity<?> getRandomKeyword( @RequestParam(required = false) Long currentKeywordId ){
        KeywordResponse randomKeyword = keywordService.getRandomKeyword(currentKeywordId);
        return ResponseEntity.ok(randomKeyword);
    }
}

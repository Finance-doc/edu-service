package com.financedoc.edu_service.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class KeywordQuiz {
    private String question;
    private String answer;
    private List<String> choices;
}

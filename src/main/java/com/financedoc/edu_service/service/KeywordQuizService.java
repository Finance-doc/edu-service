package com.financedoc.edu_service.service;

import com.financedoc.edu_service.domain.Keyword;
import com.financedoc.edu_service.domain.TermOnly;
import com.financedoc.edu_service.dto.KeywordQuiz;
import com.financedoc.edu_service.repository.KeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class KeywordQuizService {

    private final KeywordRepository keywordRepository;

    public List<KeywordQuiz> generateQuiz(int count){
        List<KeywordQuiz> quizzes = new ArrayList<>();

        // (1) 중복 없는 랜덤 키워드 5개 뽑기
        List<Keyword> randomKeywords = keywordRepository.findRandomKeywords(count);

        for(Keyword answerKeyword : randomKeywords){
            String term = answerKeyword.getTerm();
            String description = answerKeyword.getDescription();

            // (2) 정답 마스킹
            String maskedDesc = description;

            // 정확히 같은 term이 들어 있으면 그대로 마스킹
            maskedDesc = maskedDesc.replaceAll("(?i)" + Pattern.quote(term), "____");
            // 괄호가 있는 경우
            if (term.contains("(")) {
                // 괄호 앞 단어가 term에 있으면, 그것도 마스킹
                String beforeParen = term.substring(0, term.indexOf("(")).trim();
                if (beforeParen.length() > 1) {
                    maskedDesc = maskedDesc.replaceAll("(?i)" + Pattern.quote(beforeParen), "____");
                }

                // 괄호 안 단어도 마스킹
                String insideParen = term.substring(term.indexOf("(") + 1, term.indexOf(")")).trim(); // 예: 카멜지수
                if (insideParen.length() > 1) {
                    maskedDesc = maskedDesc.replaceAll("(?i)" + Pattern.quote(insideParen), "____");
                }
            }

            // (3) 보기(choices) : 정답 1개 + 랜덤 3개
            // 정답제외한 랜덤보기 3개
            List<String> choices = keywordRepository.findRandomTermsExcept(term, 3)
                    .stream()
                    .map(TermOnly::getTerm)
                    .collect(Collectors.toList());

            // 보기가 부족할 경우 보충
            if (choices.size() < 3) {
                List<String> fill = keywordRepository.findRandomTerms(3 - choices.size())
                        .stream()
                        .map(TermOnly::getTerm)
                        .collect(Collectors.toList());
                choices.addAll(fill);
            }

            // 정답추가후 순서섞기
            choices.add(term); // 정답 포함
            choices = choices.stream().distinct().collect(Collectors.toList());
            Collections.shuffle(choices); // 순서 섞기

            // (4) 퀴즈로 생성
            KeywordQuiz quiz = KeywordQuiz.builder()
                    .question(maskedDesc)
                    .answer(term)
                    .choices(choices)
                    .build();

            quizzes.add(quiz);
        }

        return quizzes;
    }
}

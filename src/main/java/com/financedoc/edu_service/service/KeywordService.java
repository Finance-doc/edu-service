package com.financedoc.edu_service.service;

import com.financedoc.edu_service.domain.Keyword;
import com.financedoc.edu_service.dto.KeywordResponse;
import com.financedoc.edu_service.repository.KeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class KeywordService {
    private final KeywordRepository keywordRepository;

    public KeywordResponse getRandomKeyword(Long keywordId){
        Keyword keyword = (keywordId == null)
                ? keywordRepository.findyRandomKeyword()
                : keywordRepository.findyRandomKeywordExcept(keywordId);

        return KeywordResponse.builder()
                .keywordId(keyword.getKeywordId())
                .keywordName(keyword.getTerm())
                .keywordDesc(keyword.getDescription())
                .keywordSrc(keyword.getSource())
                .keywordLink(keyword.getLink())
                .build();
    }
}

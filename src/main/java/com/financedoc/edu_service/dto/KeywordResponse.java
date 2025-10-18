package com.financedoc.edu_service.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class KeywordResponse {
    private Long keywordId;
    private String keywordName;
    private String keywordDesc;
    private String keywordSrc;
    private String keywordLink;
}

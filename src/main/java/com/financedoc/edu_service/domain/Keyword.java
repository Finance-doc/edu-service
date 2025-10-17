package com.financedoc.edu_service.domain;

import jakarta.persistence.*;

@Entity
public class Keyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long keywordId;

    private String term;

    @Column(columnDefinition = "TEXT")
    private String desc;
}

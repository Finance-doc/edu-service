package com.financedoc.edu_service.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "keyword")
public class Keyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long keywordId;

    private String term;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String source;

    private String link;
}

package com.financedoc.edu_service.repository;

import com.financedoc.edu_service.domain.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, Long> {

    @Query(value = "SELECT * FROM keyword ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Keyword findyRandomKeyword();

    @Query(value = "SELECT * FROM keyword WHERE keyword_id <> :keywordId ORDER BY RANDOM() LIMIT 1",
            nativeQuery = true)
    Keyword findyRandomKeywordExcept(@Param("keywordId") Long keywordId);
}

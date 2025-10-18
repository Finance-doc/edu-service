package com.financedoc.edu_service.repository;

import com.financedoc.edu_service.domain.Keyword;
import com.financedoc.edu_service.domain.TermOnly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, Long> {

    // 카드뉴스용
    @Query(value = "SELECT * FROM keyword ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Keyword findRandomKeyword();

    @Query(value = "SELECT * FROM keyword WHERE keyword_id <> :keywordId ORDER BY RANDOM() LIMIT 1",
            nativeQuery = true)
    Keyword findRandomKeywordExcept(@Param("keywordId") Long keywordId);


    // 퀴즈용
    @Query(value = "SELECT * FROM keyword ORDER BY RANDOM() LIMIT :count", nativeQuery = true)
    List<Keyword> findRandomKeywords(@Param("count") int count);

    @Query(value = "SELECT term FROM keyword WHERE term <> :excludeTerm ORDER BY RANDOM() LIMIT :count", nativeQuery = true)
    List<TermOnly> findRandomTermsExcept(@Param("excludeTerm") String excludeTerm,
                                         @Param("count") int count);

    @Query(value = "SELECT term FROM keyword ORDER BY RANDOM() LIMIT :count", nativeQuery = true)
    List<TermOnly> findRandomTerms(@Param("count") int count);
}

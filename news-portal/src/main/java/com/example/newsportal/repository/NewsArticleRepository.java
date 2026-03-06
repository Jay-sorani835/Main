package com.example.newsportal.repository;

import com.example.newsportal.model.NewsArticle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsArticleRepository extends MongoRepository<NewsArticle, String>, CustomNewsRepository {

    // Pagination built-in: Find all active articles
    Page<NewsArticle> findByIsActiveTrue(Pageable pageable);

    // Raw JSON Query equivalent to filtering by category
    @Query("{ 'category' : ?0, 'isActive' : true }")
    Page<NewsArticle> findActiveByCategory(String category, Pageable pageable);

}

package com.example.newsportal.repository;

import com.example.newsportal.model.NewsArticle;

import java.util.List;

public interface CustomNewsRepository {

    // Dynamic search across title and content
    List<NewsArticle> searchArticlesByKeyword(String keyword);

}

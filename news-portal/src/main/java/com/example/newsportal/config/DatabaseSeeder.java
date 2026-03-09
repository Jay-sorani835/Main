package com.example.newsportal.config;

import com.example.newsportal.model.NewsArticle;
import com.example.newsportal.repository.NewsArticleRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

	@Autowired
    private NewsArticleRepository newsArticleRepository;

    @Override
    public void run(String... args) throws Exception {
        if (newsArticleRepository.count() == 0) {
            NewsArticle article1 = new NewsArticle();
            article1.setTitle("Welcome to NewsPortal Pro!");
            article1.setContent(
                    "This is the first default article in our newly launched NewsPortal Pro! Here you can read news from everywhere. Stay tuned for more updates. This application is built with Spring Boot and Vanilla CSS.");
            article1.setAuthor("Admin");
            article1.setCategory("Technology");
            article1.setPublishedDate(LocalDateTime.now().minusDays(1));
            article1.setActive(true);

            NewsArticle article2 = new NewsArticle();
            article2.setTitle("Global Tech Conference 2026");
            article2.setContent(
                    "The Global Tech Conference 2026 showcased the latest in Artificial Intelligence, machine learning, and quantum computing. Many companies have revealed their latest products.");
            article2.setAuthor("Tech Reporter");
            article2.setCategory("Technology");
            article2.setPublishedDate(LocalDateTime.now().minusHours(5));
            article2.setActive(true);

            NewsArticle article3 = new NewsArticle();
            article3.setTitle("Stock Market Reaches New Heights");
            article3.setContent(
                    "The stock market indices have reached an all-time high today. Investors are seeing massive gains in tech, real estate, and renewable energy sectors.");
            article3.setAuthor("Finance Guru");
            article3.setCategory("Business");
            article3.setPublishedDate(LocalDateTime.now().minusHours(2));
            article3.setActive(true);

            newsArticleRepository.saveAll(List.of(article1, article2, article3));
        }
    }
}

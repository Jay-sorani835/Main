package com.example.newsportal;

import com.example.newsportal.config.FileStorageProperties;
import com.example.newsportal.model.NewsArticle;
import com.example.newsportal.repository.NewsArticleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class NewsPortalApplication {
    public static void main(String[] args) {
        SpringApplication.run(NewsPortalApplication.class, args);
    }

    @org.springframework.context.annotation.Bean
    CommandLineRunner initData(NewsArticleRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                System.out.println("No articles found in DB. Inserting default articles...");

                NewsArticle article1 = new NewsArticle();
                article1.setTitle("Global Tech Innovations Continue to Surge");
                article1.setContent(
                        "The technology sector is experiencing unprecedented growth with new breakthroughs in AI and quantum computing. Industry leaders are optimistic about the upcoming quarter.");
                article1.setAuthor("Tech Reporter");
                article1.setCategory("technology");
                article1.setPublishedDate(java.time.LocalDateTime.now());
                article1.setActive(true);

                NewsArticle article2 = new NewsArticle();
                article2.setTitle("Local Sports Team Wins Championship");
                article2.setContent(
                        "In an astonishing upset, the local underdogs secured the main championship title. Fans flooded the streets to celebrate this historic victory.");
                article2.setAuthor("Sports Editor");
                article2.setCategory("sports");
                article2.setPublishedDate(java.time.LocalDateTime.now());
                article2.setActive(true);

                NewsArticle article3 = new NewsArticle();
                article3.setTitle("Market Hits Record Highs Amids Optimism");
                article3.setContent(
                        "Financial markets responded positively to the recent policy changes, hitting new all-time highs. Analysts warn this might be volatile but investors are highly optimistic.");
                article3.setAuthor("Finance Desk");
                article3.setCategory("business");
                article3.setPublishedDate(java.time.LocalDateTime.now());
                article3.setActive(true);

                NewsArticle article4 = new NewsArticle();
                article4.setTitle("Major Discoveries in Space Exploration");
                article4.setContent(
                        "Astronomers have found evidence of what could possibly be an exoplanet holding water, pushing the boundaries of what we understand about our universe.");
                article4.setAuthor("Science Weekly");
                article4.setCategory("knowledge");
                article4.setPublishedDate(java.time.LocalDateTime.now());
                article4.setActive(true);

                repository.saveAll(java.util.Arrays.asList(article1, article2, article3, article4));
                System.out.println("Default articles inserted.");
            }
        };
    }
}

package com.example.newsportal.service;

import com.example.newsportal.model.Article;
import com.example.newsportal.model.Category;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsService {

    private final List<Article> articles = new ArrayList<>();

    public NewsService() {
        // Initialize with dummy data
        articles.add(new Article(1L, "Local Team Wins Championship", "Thrilling victory in the final match.",
                "In an unprecedented turn of events, the local football team secured a massive 3-0 victory over their long-time rivals...",
                Category.SPORTS, "John Doe", LocalDateTime.now().minusHours(2),
                "https://images.unsplash.com/photo-1579952363873-27f3bade9f55?q=80&w=600&auto=format&fit=crop"));

        articles.add(new Article(2L, "New Tech Hub Opens Downtown",
                "City officials inaugurate the new innovation center.",
                "The mayor today cut the ribbon on the new $50M technology hub designed to accelerate start-ups in the region...",
                Category.DAILY, "Jane Smith", LocalDateTime.now().minusHours(5),
                "https://images.unsplash.com/photo-1497215728101-856f4ea42174?q=80&w=600&auto=format&fit=crop"));

        articles.add(new Article(3L, "Mystery Solved in Art Heist", "Detectives recover painting missing for 20 years.",
                "A breakthrough in the 2004 museum heist led police to a hidden basement where the masterpiece was perfectly preserved...",
                Category.CRIME, "Det. Miller", LocalDateTime.now().minusDays(1),
                "https://images.unsplash.com/photo-1533134486753-c833f0ed4866?q=80&w=600&auto=format&fit=crop"));

        articles.add(new Article(4L, "James Webb Telescope Discovers New Exoplanet",
                "Scientists amazed by earth-like world.",
                "NASA's James Webb Space Telescope has captured data suggesting a new, potentially habitable exoplanet in the Goldilocks zone...",
                Category.KNOWLEDGE, "Dr. Allen", LocalDateTime.now().minusDays(2),
                "https://images.unsplash.com/photo-1614730321146-b6fa6a46bcb4?q=80&w=600&auto=format&fit=crop"));

        articles.add(new Article(5L, "Tennis Star Retires", "End of an era for the 20-time grand slam champion.",
                "After a spectacular 15-year career, the tennis legend has officially announced their retirement from professional sports...",
                Category.SPORTS, "John Doe", LocalDateTime.now().minusDays(3),
                "https://images.unsplash.com/photo-1595435934249-5df7ed86e1c0?q=80&w=600&auto=format&fit=crop"));

        articles.add(new Article(6L, "Market Hits Record High", "Stocks surge as inflation cools.",
                "Investors rejoiced today as the S&P 500 hit a new all-time high, driven by strong earnings reports from the tech sector...",
                Category.DAILY, "Jane Smith", LocalDateTime.now().minusHours(12),
                "https://images.unsplash.com/photo-1611974789855-9c2a0a223690?q=80&w=600&auto=format&fit=crop"));
    }

    public List<Article> getAllArticles() {
        return new ArrayList<>(articles);
    }

    public List<Article> getArticlesByCategory(Category category) {
        return articles.stream()
                .filter(article -> article.getCategory() == category)
                .collect(Collectors.toList());
    }

    public Article getArticleById(Long id) {
        return articles.stream()
                .filter(article -> article.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}

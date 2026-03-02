package com.example.newsportal.controller;

import com.example.newsportal.model.Category;
import com.example.newsportal.service.NewsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sports")
public class SportsController {

    private final NewsService newsService;

    public SportsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    public String sportsNews(Model model) {
        model.addAttribute("articles", newsService.getArticlesByCategory(Category.SPORTS));
        model.addAttribute("title", "Sports - News Portal");
        model.addAttribute("currentCategory", "sports");
        return "index"; // Reusing the same index template for category feeds
    }
}

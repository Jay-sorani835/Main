package com.example.newsportal.controller;

import com.example.newsportal.service.NewsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("articles", newsService.getAllArticles());
        model.addAttribute("title", "Home - News Portal");
        model.addAttribute("currentCategory", "all");
        return "index";
    }

    @GetMapping("/article/{id}")
    public String articleDetail(@PathVariable Long id, Model model) {
        model.addAttribute("article", newsService.getArticleById(id));
        return "article-detail";
    }
}

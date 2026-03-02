package com.example.newsportal.controller;

import com.example.newsportal.model.Category;
import com.example.newsportal.service.NewsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/knowledge")
public class KnowledgeController {

    private final NewsService newsService;

    public KnowledgeController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    public String knowledgeNews(Model model) {
        model.addAttribute("articles", newsService.getArticlesByCategory(Category.KNOWLEDGE));
        model.addAttribute("title", "Knowledge - News Portal");
        model.addAttribute("currentCategory", "knowledge");
        return "index";
    }
}

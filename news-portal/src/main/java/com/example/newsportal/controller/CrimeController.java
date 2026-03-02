package com.example.newsportal.controller;

import com.example.newsportal.model.Category;
import com.example.newsportal.service.NewsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/crime")
public class CrimeController {

    private final NewsService newsService;

    public CrimeController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    public String crimeNews(Model model) {
        model.addAttribute("articles", newsService.getArticlesByCategory(Category.CRIME));
        model.addAttribute("title", "Crime - News Portal");
        model.addAttribute("currentCategory", "crime");
        return "index";
    }
}

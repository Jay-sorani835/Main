package com.example.newsportal.controller;

import com.example.newsportal.model.Category;
import com.example.newsportal.service.NewsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/daily")
public class DailyController {

    private final NewsService newsService;

    public DailyController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    public String dailyNews(Model model) {
        model.addAttribute("articles", newsService.getArticlesByCategory(Category.DAILY));
        model.addAttribute("title", "Daily News - News Portal");
        model.addAttribute("currentCategory", "daily");
        return "index";
    }
}

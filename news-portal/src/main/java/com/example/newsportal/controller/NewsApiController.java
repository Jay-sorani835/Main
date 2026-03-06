package com.example.newsportal.controller;

import com.example.newsportal.model.NewsArticle;
import com.example.newsportal.service.FileStorageService;
import com.example.newsportal.service.NewsService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class NewsApiController {

	@Autowired
    private NewsService newsService;
	@Autowired
	private FileStorageService fileStorageService;

    @GetMapping
    public ResponseEntity<Page<NewsArticle>> getAllArticles(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sortBy", defaultValue = "publishedDate") String sortBy,
            @RequestParam(name = "direction", defaultValue = "DESC") String direction,
            @RequestParam(name = "category", required = false) String category) {

        Page<NewsArticle> articles;
        if (category != null && !category.isEmpty()) {
            articles = newsService.getArticlesByCategory(category, page, size, sortBy, direction);
        } else {
            articles = newsService.getAllArticles(page, size, sortBy, direction);
        }
        return ResponseEntity.ok(articles);
    }

    @GetMapping("/search")
    public ResponseEntity<List<NewsArticle>> searchArticles(@RequestParam(name = "keyword") String keyword) {
        return ResponseEntity.ok(newsService.searchArticles(keyword));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsArticle> getArticleById(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok(newsService.getArticleById(id));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<NewsArticle> createArticle(
            @RequestPart(name = "article") NewsArticle article,
            @RequestPart(value = "image", required = false) MultipartFile imageFile) {
        return new ResponseEntity<>(newsService.createArticle(article, imageFile), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<NewsArticle> updateArticle(
            @PathVariable(name = "id") String id,
            @RequestPart(name = "article") NewsArticle article,
            @RequestPart(value = "image", required = false) MultipartFile newImageFile) {
        return ResponseEntity.ok(newsService.updateArticle(id, article, newImageFile));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable(name = "id") String id) {
        newsService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint to serve images directly to the UI
    @GetMapping("/images/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable(name = "fileName") String fileName,
            HttpServletRequest request) {
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            // Ignored
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}

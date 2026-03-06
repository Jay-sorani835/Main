package com.example.newsportal.service;

import com.example.newsportal.model.NewsArticle;
import com.example.newsportal.repository.NewsArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NewsService {

        private NewsArticleRepository newsArticleRepository;
        private FileStorageService fileStorageService;

        public Page<NewsArticle> getAllArticles(int page, int size, String sortBy, String direction) {
                Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                                : Sort.by(sortBy).descending();
                Pageable pageable = PageRequest.of(page, size, sort);
                return newsArticleRepository.findByIsActiveTrue(pageable);
        }

        public Page<NewsArticle> getArticlesByCategory(String category, int page, int size, String sortBy,
                        String direction) {
                Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                                : Sort.by(sortBy).descending();
                Pageable pageable = PageRequest.of(page, size, sort);
                return newsArticleRepository.findActiveByCategory(category, pageable);
        }

        public List<NewsArticle> searchArticles(String keyword) {
                return newsArticleRepository.searchArticlesByKeyword(keyword);
        }

        public NewsArticle getArticleById(String id) {
                return newsArticleRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Article not found with id: " + id));
        }

        public NewsArticle createArticle(NewsArticle article, MultipartFile imageFile) {
                if (imageFile != null && !imageFile.isEmpty()) {
                        String fileName = fileStorageService.storeFile(imageFile);
                        article.setImageUrl(fileName);
                }

                article.setPublishedDate(LocalDateTime.now());
                article.setActive(true);
                return newsArticleRepository.save(article);
        }

        public NewsArticle updateArticle(String id, NewsArticle updatedArticle, MultipartFile newImageFile) {
                NewsArticle existingArticle = getArticleById(id);

                existingArticle.setTitle(updatedArticle.getTitle());
                existingArticle.setContent(updatedArticle.getContent());
                existingArticle.setAuthor(updatedArticle.getAuthor());
                existingArticle.setCategory(updatedArticle.getCategory());
                existingArticle.setActive(updatedArticle.isActive());

                // Update image if a new one is provided
                if (newImageFile != null && !newImageFile.isEmpty()) {
                        // Delete old image first
                        if (existingArticle.getImageUrl() != null) {
                                fileStorageService.deleteFile(existingArticle.getImageUrl());
                        }

                        // Store new image
                        String fileName = fileStorageService.storeFile(newImageFile);
                        existingArticle.setImageUrl(fileName);
                }

                return newsArticleRepository.save(existingArticle);
        }

        public void deleteArticle(String id) {
                NewsArticle article = getArticleById(id);

                // Delete associated image
                if (article.getImageUrl() != null) {
                        fileStorageService.deleteFile(article.getImageUrl());
                }

                newsArticleRepository.delete(article);
        }
}

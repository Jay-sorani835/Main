package com.example.newsportal.repository;

import com.example.newsportal.model.NewsArticle;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomNewsRepositoryImpl implements CustomNewsRepository {

    private MongoTemplate mongoTemplate;

    @Override
    public List<NewsArticle> searchArticlesByKeyword(String keyword) {
        Query query = new Query();
        query.addCriteria(new Criteria().orOperator(
                Criteria.where("title").regex(keyword, "i"),
                Criteria.where("content").regex(keyword, "i")));
        query.addCriteria(Criteria.where("isActive").is(true));

        return mongoTemplate.find(query, NewsArticle.class);
    }
}

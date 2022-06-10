package com.robertciotoiu.resolver;

import com.robertciotoiu.model.NewsEntity;
import com.robertciotoiu.repository.NewsRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {

    private final NewsRepository newsRepository;

    @Autowired
    public Query(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public Iterable<NewsEntity> findAllNews(){
        return newsRepository.findAll();
    }

    public Long countNews(){
        return newsRepository.count();
    }
}

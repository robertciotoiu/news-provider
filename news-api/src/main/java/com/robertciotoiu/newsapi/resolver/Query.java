package com.robertciotoiu.newsapi.resolver;

import com.robertciotoiu.newscommons.model.NewsEntity;
import com.robertciotoiu.newscommons.repository.NewsRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {

    private final NewsRepository newsRepository;

    @Autowired
    public Query(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public Iterable<NewsEntity> getAllNews(Integer page, Integer size){
        //Safety check. TODO: better handle: https://www.baeldung.com/spring-graphql-error-handling
        if(size > 100)
            throw new UnsupportedOperationException("Lower the size and use pagination!");

        Pageable pageable = PageRequest.of(page, size);
        return newsRepository.findAll(pageable);
    }

    public Long countNews(){
        return newsRepository.count();
    }
}

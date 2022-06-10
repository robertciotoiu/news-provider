package com.robertciotoiu.service;

import com.robertciotoiu.model.NewsEntity;

import java.util.List;

public interface NewsIngestService {
    void saveFetchedNews(List<NewsEntity> fetchedEntities);
}

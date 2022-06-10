package com.robertciotoiu.service;

import com.robertciotoiu.model.NewsEntity;

import java.util.List;

public interface NewsIngestService {
    /**
     * Saves new articles to the news table.
     * @param fetchedEntities
     * @return an int[] representing:
     *                - number of new articles;
     *                - number of successfully saved new articles;
     *                - number of existing articles that have an update available;
     *                - number of successfully saved existing articles that have an update available
     */
    int[] saveFetchedNews(List<NewsEntity> fetchedEntities);
}

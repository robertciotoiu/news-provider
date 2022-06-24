package com.robertciotoiu.newsingestion;

import com.robertciotoiu.newscommons.model.NewsEntity;
import com.robertciotoiu.newsingestion.service.NewsFetcherService;
import com.robertciotoiu.newsingestion.service.NewsIngestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class NewsIngestTasks {
    private static final String URL = "http://feeds.nos.nl/nosjournaal?format=xml";
    NewsFetcherService newsFetcherService;
    NewsIngestService newsIngestService;

    @Autowired
    public NewsIngestTasks(NewsFetcherService newsFetcherService, NewsIngestService newsIngestService) {
        this.newsFetcherService = newsFetcherService;
        this.newsIngestService = newsIngestService;
    }

    @Scheduled(fixedRate = 5000)
    public void pollNews(){
        log.info("Starting to poll news from: {}", URL);
        List<NewsEntity> fetchedEntities = new ArrayList<>();

        try {
            fetchedEntities = newsFetcherService.fetchNews(URL);
            newsIngestService.saveFetchedNews(fetchedEntities);
        } catch (Exception e){
            log.error("Exception while fetching news: ", e);
        }
        log.info("Fetched: {} news.\n", fetchedEntities.size());
    }
}

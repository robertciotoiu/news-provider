package com.robertciotoiu.service;

import com.robertciotoiu.repository.NewsRepository;
import com.robertciotoiu.model.NewsEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.List;

@Slf4j
@Service
public class NewsIngestServiceImpl implements NewsIngestService {
    private final NewsRepository newsRepository;


    @Autowired
    public NewsIngestServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    /**
     * Saves new articles to the news table.
     * Updates the articles only if new @NewsEntity.content or @NewsEntity.publishDate is has been modified.
     * Ignores other existing unmodified articles.
     * @param fetchedEntities
     * @return an int[] representing:
     *                - number of new articles;
     *                - number of successfully saved new articles;
     *                - number of existing articles that have an update available;
     *                - number of successfully saved existing articles that have an update available
     */
    @Override
    public int[] saveFetchedNews(List<NewsEntity> fetchedEntities) {
        var existingArticles = fetchedEntities.stream()
                //retrieve already saved articles
                //save into a KeyValue pair: <fetched, retrieved>, we'll need both
                .map(newsEntity -> new AbstractMap.SimpleEntry<>(newsEntity, newsRepository.findByTitle(newsEntity.getTitle())))
                //unpack only the found elements
                .filter(pair -> pair.getValue().isPresent())
                .map(pair -> new AbstractMap.SimpleEntry<>(pair.getKey(), pair.getValue().get()))
                .toList();

        //get existing articles with new data
        var articlesToUpdate = existingArticles.stream()
                //filter by publishDate
                .filter(pair -> pair.getKey().getPublishDate().equals(pair.getValue().getUpdateDate()))
                //filter by content
                .filter(pair -> pair.getKey().getContent().equals(pair.getValue().getContent()))
                //prepare entities for DB update
                .peek(pair -> pair.getKey().setId(pair.getValue().getId()))
                //map and return save to a list for using the batch operation: saveAllAndFlush
                .map(AbstractMap.SimpleEntry::getKey)
                .toList();

        //get new articles
        var newArticles = fetchedEntities.stream()
                .filter(fetched -> !existingArticles.stream().map(pair -> pair.getValue().getTitle()).toList().contains(fetched.getTitle()))
                .toList();

        //save existing articles
        var savedExistingArticles = newsRepository.saveAllAndFlush(articlesToUpdate);

        //save new articles
        var savedNewArticles = newsRepository.saveAllAndFlush(newArticles);

        log.info("Polled: {} news.\nNew articles: {}\nNew articles saved in DB: {}\nExisting articles: {}.\nUpdated articles in DB: {}",
                fetchedEntities.size(),
                newArticles.size(),
                savedNewArticles.size(),
                existingArticles.size(),
                savedExistingArticles.size()
        );

        return new int[]{newArticles.size(),
                savedNewArticles.size(),
                existingArticles.size(),
                savedExistingArticles.size()};
    }
}

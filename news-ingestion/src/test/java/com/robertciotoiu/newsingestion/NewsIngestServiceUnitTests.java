package com.robertciotoiu.newsingestion;

import com.robertciotoiu.newsingestion.model.NewsEntity;
import com.robertciotoiu.newsingestion.repository.NewsRepository;
import com.robertciotoiu.newsingestion.service.NewsIngestServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class NewsIngestServiceUnitTests {

    @Mock
    NewsRepository newsRepository;

    @InjectMocks
    NewsIngestServiceImpl newsIngestService;

    @Test
    void testNewsIngestionOfOneUpdatedArticleAndTwoNewArticles(){
        //given
        var article1 = NewsEntity.builder().title("Test 1").description("-----").publishedDate(OffsetDateTime.MIN).imageUrl("not found").build();
        var article2 = NewsEntity.builder().title("Test 2").description("-----").publishedDate(OffsetDateTime.MAX).imageUrl("not found").build();
        var article3 = NewsEntity.builder().title("Test 3").description("-----").publishedDate(OffsetDateTime.MAX).imageUrl("not found").build();

        List<NewsEntity> fetchedEntities = new ArrayList<>();
        fetchedEntities.add(article1);
        fetchedEntities.add(article2);
        fetchedEntities.add(article3);

        article1.setDescription("new description");
        Mockito.when(newsRepository.findByTitle("Test 1")).thenReturn(Optional.of(article1));
        Mockito.when(newsRepository.findByTitle("Test 2")).thenReturn(Optional.empty());
        Mockito.when(newsRepository.findByTitle("Test 3")).thenReturn(Optional.empty());

        //when
        var result = newsIngestService.saveFetchedNews(fetchedEntities);

        //then
        Assertions.assertEquals(2, result[0]);
        Assertions.assertEquals(1, result[2]);
    }
}

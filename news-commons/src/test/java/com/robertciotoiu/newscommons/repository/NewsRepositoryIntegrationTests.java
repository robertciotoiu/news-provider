package com.robertciotoiu.newscommons.repository;

import com.robertciotoiu.newscommons.model.NewsEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.OffsetDateTime;


//@ExtendWith(SpringExtension.class)
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@ContextConfiguration(classes = {NewsRepository.class})
//@PropertySource(value = "data.properties")
@ExtendWith(SpringExtension.class)
@DataJpaTest
//@ComponentScan(
//        value = "com.robertciotoiu.newscommons"
//)
//@AutoConfigureTestDatabase(replace = NONE)
class NewsRepositoryIntegrationTests {
    @Autowired
    private NewsRepository newsRepository;

    private NewsEntity newsEntity;

    private OffsetDateTime currentTime;

    @BeforeEach
    public void setUp() {
        currentTime = OffsetDateTime.now();
        newsEntity = NewsEntity.builder()
                .title("News title")
                .description("News description")
                .imageUrl("localhost")
                .publishedDate(currentTime)
                .build();
    }

    @Test
    void findByTitleShouldReturnNewsEntity() {
        newsRepository.saveAndFlush(newsEntity);
        var optionalNewsEntity = newsRepository.findByTitle("News title");

        Assertions.assertTrue(optionalNewsEntity.isPresent());

        var newsEntity = optionalNewsEntity.get();

        Assertions.assertNotNull(newsEntity.getId());
        Assertions.assertEquals("News title", newsEntity.getTitle());
        Assertions.assertEquals("News description", newsEntity.getDescription());
        Assertions.assertEquals(currentTime, newsEntity.getPublishedDate());
        Assertions.assertEquals("localhost", newsEntity.getImageUrl());
    }
}

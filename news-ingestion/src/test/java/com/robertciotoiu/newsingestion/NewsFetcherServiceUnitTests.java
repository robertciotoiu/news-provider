package com.robertciotoiu.newsingestion;

import com.robertciotoiu.newsingestion.model.NewsEntity;
import com.robertciotoiu.newsingestion.service.NewsFetcherServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.xml.sax.SAXException;

import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class NewsFetcherServiceUnitTests {

    private static final String link = "http://feeds.nos.nl/nosjournaal?format=xml";

    @InjectMocks
    NewsFetcherServiceImpl newsFetcher;

    @Test
    void testNewsFetching() throws XPathExpressionException, IOException, SAXException {
        List<NewsEntity> news = newsFetcher.fetchNews(link);

        Assertions.assertNotNull(news);
        Assertions.assertNotEquals(0, news.size());
    }
}

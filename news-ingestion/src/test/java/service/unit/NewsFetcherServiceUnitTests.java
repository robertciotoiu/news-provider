package service.unit;

import com.robertciotoiu.model.NewsEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import com.robertciotoiu.repository.NewsRepository;
import com.robertciotoiu.service.NewsFetcherServiceImpl;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class NewsFetcherServiceUnitTests {

    private static final String link = "http://feeds.nos.nl/nosjournaal?format=xml";

    @InjectMocks
    NewsFetcherServiceImpl newsFetcher;

    @Test
    void testNewsFetching(){
        List<NewsEntity> news = null;
        try {
            news = newsFetcher.fetchNews(link);
        } catch(Exception e){
            Assertions.fail("Exception: "+e);
        }
        Assertions.assertNotNull(news);
        Assertions.assertNotEquals(0, news.size());
    }
}

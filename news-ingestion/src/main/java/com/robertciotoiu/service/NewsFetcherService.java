package com.robertciotoiu.service;

import com.robertciotoiu.model.NewsEntity;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.List;

public interface NewsFetcherService {
    /**
     * Opens a new connection to the URL, parses the XML and extracts a list of news.
     *
     * @param url - url from where to fetch the news. It should be: http://feeds.nos.nl/nosjournaal?format=xml
     * @return a List<NewsEntity>
     * @throws IOException
     * @throws SAXException
     * @throws XPathExpressionException
     */
    List<NewsEntity> fetchNews(String url) throws IOException, SAXException, ParserConfigurationException, XPathExpressionException;
}

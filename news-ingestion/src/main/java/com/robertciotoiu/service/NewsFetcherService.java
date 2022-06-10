package com.robertciotoiu.service;

import com.robertciotoiu.model.NewsEntity;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.List;

public interface NewsFetcherService {
    List<NewsEntity> fetchNews(String url) throws IOException, SAXException, ParserConfigurationException, XPathExpressionException;
}

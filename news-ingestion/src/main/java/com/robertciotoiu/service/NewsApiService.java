package com.robertciotoiu.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NewsApiService {
//TODO: implement
    private final String url;

    @Autowired
//    private Environment environment;

    public NewsApiService(@Value("http://localhost:8080") String url) {
        this.url = url;
    }

}

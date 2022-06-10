package com.robertciotoiu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class NewsIngestionApplication {
    public static void main(String[] args){
        SpringApplication.run(NewsIngestionApplication.class, args);
    }
}

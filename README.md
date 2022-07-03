[![build](https://github.com/robertciotoiu/news-provider/actions/workflows/build.yml/badge.svg?branch=main)](https://github.com/robertciotoiu/news-provider/actions/workflows/build.yml)

[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=robertciotoiu_news-provider&metric=bugs)](https://sonarcloud.io/summary/new_code?id=robertciotoiu_news-provider) [![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=robertciotoiu_news-provider&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=robertciotoiu_news-provider) [![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=robertciotoiu_news-provider&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=robertciotoiu_news-provider) [![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=robertciotoiu_news-provider&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=robertciotoiu_news-provider) [![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=robertciotoiu_news-provider&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=robertciotoiu_news-provider) [![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=robertciotoiu_news-provider&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=robertciotoiu_news-provider) [![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=robertciotoiu_news-provider&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=robertciotoiu_news-provider)
# News feed project

- Fetch news from http://feeds.nos.nl/nosjournaal?format=xml
- Save them to a PostgreSQL database
- Expose news via GraphQL endpoint

## Getting started

### Prerequisites:

- openjdk 18
- docker
- maven 3.8.5

### Setup:

1. git clone \<this repository>
2. cd news-provider
3. mvn clean install
4. docker-compose up

The PostgreSql db will start, news-ingest spring boot application will start to fetch & save news in the database and a GraphQL Endpoint will be available at address: http://localhost:8888/v1/news

Now you can simply access the GraphQL endpoint using Postman:
- POST http://localhost:8888/v1/news

And add the GraphQL query in the body:

```graphql
{
    getAllNews{
        title,
        content,
        publishDate,
        imageUrl
    }
}
```
*NOTE: by default, a maximum of 100 articles will be retrieved!*

Controling the pagination and size(default is 10):
```graphql
{
    getAllNews(page: 0, size: 5){
        title,
        publishDate,
        content
    }
}
```

Other examples:
```graphql
{
    getAllNews(page: 1, size: 10){
        title,
        publishDate,
        content
    }
}
```

```graphql
{
    getAllNews{
        title,
        publishDate
    }
}
```

```graphql
{
    countNews
}
```
Or any other combination of the fields.

## Project description

One Maven parent project, packing three Maven modules: 
- news-api: Spring Boot application for exposing GraphQL endpoint
- news-commons: Holding common data between the other two modules
- news-ingestion: Spring Boot application with Scheduling Tasks for fetching and saving data

- ### News ingest application
<pre>
&nbsp;- Scheduling task
&nbsp;- Polling news feed from: <a>http://feeds.nos.nl/nosjournaal?format=xml</a>
&nbsp;- Frequency: 5 minutes
&nbsp;- Save and update news in a relational database using Spring Data jpa
&nbsp;- PostgreSql database
</pre>  

&nbsp;Doc reference: https://spring.io/guides/gs/scheduling-tasks/

- ### News feed GraphQL API
<pre>
&nbsp;Endpoint:
&nbsp;- /v1/news
</pre>

Doc reference: https://spring.io/projects/spring-graphql#learn

TODO:
- DTO object
- Graphql Query
- Graphql Mutation
- 
https://medium.com/decathlontechnology/minimal-graphql-client-request-with-spring-boot-22e0041b170

https://spring.io/projects/spring-cloud/

https://www.youtube.com/watch?v=LkmhwaxlduQ&ab_channel=TechTutor

https://github.com/2much2learn/article_dec_28_mavengradle-based-multi-module-spring-boot-microservices/blob/master/service-a/src/main/java/com/toomuch2learn/microservices/servicea/GreetingController.java
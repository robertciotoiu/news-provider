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
    findAllNews{
        title,
        description,
        publishedDate,
        imageUrl
    }
}
```
or

```graphql
{
    findAllNews{
        title,
        publishedDate
    }
}
```

```graphql
{
    findAllNews{
        title,
        publishedDate
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

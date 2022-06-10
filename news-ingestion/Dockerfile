FROM openjdk:18-jdk
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} news-ingestion.jar
ENTRYPOINT ["java","-jar","/news-ingestion.jar"]
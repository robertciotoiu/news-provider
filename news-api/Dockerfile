FROM openjdk:18-jdk
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} news-api.jar
ENTRYPOINT ["java","-jar","/news-api.jar"]
EXPOSE 8888
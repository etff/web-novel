FROM openjdk:17-alpine AS builder

ARG APP_HOME=/usr/app/
ARG JAR_FILE=target/*.jar
ENV APP_NAME=app.jar

WORKDIR $APP_HOME

COPY ${JAR_FILE} $APP_HOME/$APP_NAME
ENTRYPOINT ["java","-jar", "/usr/app/app.jar"]

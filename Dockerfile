FROM openjdk:17-jdk-alpine

EXPOSE 8080

ENV APP_HOME /app

RUN mkdir $APP_HOME

COPY target/*.jar $APP_HOME/app.jar

WORKDIR $APP_HOME

CMD ["sh", "-c", "java -jar app.jar"]
FROM openjdk:17-jdk-slim
COPY target/*-jar-with-dependencies.jar /app/app.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "app.jar"]
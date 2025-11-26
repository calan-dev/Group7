#
# BUILD stage
#
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -B -q -DskipTests package


#
# RUNTIME stage
#
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

RUN addgroup -S app && adduser -S app -G app

# copy fat jar
COPY --from=build /app/target/app.jar /app/app.jar

USER app
ENTRYPOINT ["java","-jar","/app/app.jar"]

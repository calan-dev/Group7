#
# BUILD stage
#

FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
#package into a fat jar
RUN mvn -B -q -DskipTests package



#
# RUNTIME stage
#

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
#creates non root app user for "security"
RUN addgroup -S app && adduser -S app -G app
COPY --from=build /app/target/*-jar-with-dependencies.jar /app/app.jar
#contiues from non root user
USER app
ENTRYPOINT ["java","-jar","/app/app.jar"]




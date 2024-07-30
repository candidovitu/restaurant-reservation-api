FROM gradle:8.9.0-jdk17-alpine AS build

WORKDIR /app

COPY build.gradle.kts settings.gradle.kts /app/

COPY . /app

RUN gradle bootJar --no-daemon

FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=build /app/build/libs/restaurant-reservation-api.jar app.jar

RUN ls /app


EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
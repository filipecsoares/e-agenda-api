FROM adoptopenjdk/openjdk11:alpine-jre

WORKDIR /app

COPY target/e-agenda-api.jar /app/e-agenda-api.jar

ENTRYPOINT ["java", "-jar", "e-agenda-api.jar"]
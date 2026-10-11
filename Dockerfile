
FROM eclipse-temurin:21-jre

LABEL maintainer="moreirajoaopaulo157@senar"

WORKDIR /app

COPY target/helpDesk-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

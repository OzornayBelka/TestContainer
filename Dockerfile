FROM openjdk:18-oracle

EXPOSE 8081

ADD target/SpringBoot-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
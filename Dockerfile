FROM openjdk:17
ADD target/sbtaskmanagementsystem-0.0.1-SNAPSHOT.jar sbtaskmanagementsystem-0.0.1-SNAPSHOT.jar
EXPOSE 9237
ENTRYPOINT ["java", "-jar", "sbtaskmanagementsystem-0.0.1-SNAPSHOT.jar"]

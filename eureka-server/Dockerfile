FROM eclipse-temurin:17-jdk-jammy
LABEL authors="santhoshsai"
WORKDIR /app

ADD target/Service-Registry.jar /tmp/Service-Registry.jar

EXPOSE 8761

ENTRYPOINT ["java","-jar","/tmp/Service-Registry.jar"]
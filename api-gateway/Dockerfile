FROM eclipse-temurin:17-jdk-jammy
LABEL authors="santhoshsai"
WORKDIR /app

ADD target/ApiGateway.jar /tmp/ApiGateway.jar

EXPOSE 8765

ENTRYPOINT ["java","-jar","/tmp/ApiGateway.jar"]
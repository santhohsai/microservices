FROM eclipse-temurin:17-jdk-jammy
LABEL authors="santhoshsai"

ADD target/QuestionService.jar /tmp/QuestionService.jar

ENTRYPOINT ["java", "-jar", "/tmp/QuestionService.jar"]
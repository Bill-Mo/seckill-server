FROM openjdk:8-jre-slim
WORKDIR /app
COPY seckill-0.0.1-SNAPSHOT.jar seckill.jar
ENTRYPOINT ["java", "-jar", "seckill.jar"]

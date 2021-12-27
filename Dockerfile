FROM maven:3.6.3-openjdk-11 as builder
WORKDIR /app
COPY . /app
RUN ls -la /app
RUN mvn clean package

FROM builder as test
RUN echo "Running tests"
RUN ["mvn", "test"]


FROM openjdk:11.0.7-jre-slim
COPY --from=builder /app/target/*.jar ./app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Xmx128m","-jar","app.jar"]
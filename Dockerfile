FROM openjdk:22-jdk-alpine
COPY /Users/taylorsmacbookair/IdeaProjects/d387-advanced-java/target/D387_sample_code-0.0.2-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
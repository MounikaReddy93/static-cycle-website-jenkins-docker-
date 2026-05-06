FROM eclipse-temurin:8-jre
WORKDIR /app
COPY target/jenkins-maven-demo-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-cp", "-jar", "app.jar"]

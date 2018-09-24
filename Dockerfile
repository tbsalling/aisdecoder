FROM openjdk:11-jre-slim
MAINTAINER Thomas Borg Salling "tbsalling@tbsalling.dk"
COPY build/libs/aisdecoder-0.0.1-SNAPSHOT.jar /app/aisdecoder.war
ENTRYPOINT ["java", "-jar", "/app/aisdecoder.war"]
EXPOSE 8080/tcp

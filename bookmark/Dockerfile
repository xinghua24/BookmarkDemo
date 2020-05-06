FROM adoptopenjdk:11-jre-hotspot
EXPOSE 8080
ADD /target/bookmark-0.0.1-SNAPSHOT.jar /app/bookmark.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/bookmark.jar"]

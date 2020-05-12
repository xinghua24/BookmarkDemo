FROM openjdk:11.0-jdk-buster as dev
WORKDIR /work

COPY mvnw /work/mvnw
COPY .mvn /work/.mvn
COPY pom.xml /work/pom.xml

RUN /work/mvnw dependency:go-offline

COPY . /work/
RUN /work/mvnw clean install

RUN chmod +x /work/target/bookmark-0.0.1-SNAPSHOT.jar

EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/work/target/bookmark-0.0.1-SNAPSHOT.jar"]

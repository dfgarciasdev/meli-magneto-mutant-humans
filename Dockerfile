FROM adoptopenjdk/openjdk11:alpine-jre
EXPOSE 8080
ADD ./build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
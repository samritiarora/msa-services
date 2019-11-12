FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/*.jar /
ENTRYPOINT ["java","-jar","/mservices-config-server.jar"]
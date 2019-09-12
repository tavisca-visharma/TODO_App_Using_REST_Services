FROM openjdk:12

COPY target/ /var/www/java

WORKDIR /var/www/java

EXPOSE 8000

ENTRYPOINT ["java", "-jar", "TODO_Rest_Api-0.0.1-SNAPSHOT.jar"]

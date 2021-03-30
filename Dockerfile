FROM openjdk:11
ADD build/libs/currency-convert-0.0.1-SNAPSHOT.jar currency-convert-0.0.1-SNAPSHOT.jar
EXPOSE 8000
ENTRYPOINT ["java", "-jar", "currency-convert-0.0.1-SNAPSHOT.jar"]

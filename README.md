## Kafka Idempotency

### Overview
This project demonstrates a microservices-based architecture using **Spring Boot**, **Java 21**, and **Apache Kafka**. It focuses on implementing idempotency in Kafka-based messaging systems to ensure message processing reliability.

#### SDKMAN
Using .**_sdkmanrc_** file we can enable project specific java and maven versions

```
java=21.0.7-tem
maven=3.9.6
```

#### Maven Wrapper
Install maven wrapper to run maven commands on ease.

```
mvn wrapper:wrapper
./mvnw clean build
```
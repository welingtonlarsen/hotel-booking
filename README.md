<div align="center">

# Hotel Booking API

![](https://img.shields.io/badge/Status-Done-brightgreen)


</div>
<div align="center">

![](https://img.shields.io/badge/Autor-Welington%20Larsen-brightgreen)
![](https://img.shields.io/badge/Language-Java-brightgreen)
![](https://img.shields.io/badge/Framework-Spring-brightgreen)

</div>

## OpenAPI

OpenAPI documentation can be found in: ```/swagger-ui.html```

## Java Version

It's recommended use java 18

## Deploy recommendation

Infra team can deploy the application in different ways. If the choice is to use kubernetes, there
is a Dockerfile on root that could be used. If the choice is to use the .jar file, it's located
inside target folder. To generate it, is necessary run the following command ```./mvnw install```.
Also, the following environments variables should be informed:

- SERVER_PORT
- MYSQL_URL
- MSQL_USERNAME
- MYSQL_PASSWORD
- profile="prod"

## Local setup

Install project dependencies

```bash
$ ./mvnw install
```

Up necessary infrastructure

```bash
$ docker-compose up -d
```

Start application

```bash
$ java -jar ./target/hotel-booking-0.0.1-SNAPSHOT.jar
```

## Run tests

You may execute the following command via CLI:

```bash
$ ./mvnw test
```

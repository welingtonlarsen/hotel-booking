FROM openjdk:18
MAINTAINER welingtonlarsen
COPY target/hotel-booking-0.0.1-SNAPSHOT.jar hotel-booking.jar
ENTRYPOINT ["java","-jar","/hotel-booking.jar"]

#CMD ["java", "-jar", "-Dspring.profiles.active=prod" ,"./star-wars-api.jar"]
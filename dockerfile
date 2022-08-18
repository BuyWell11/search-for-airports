#
# Build stage
#
FROM maven AS build 
COPY src /app/src
COPY pom.xml /app
RUN mvn -f /app/pom.xml clean package

#
# Package stage
#
FROM openjdk
WORKDIR /app
COPY --from=build /app/target/airports-1.0.jar airports-search.jar
COPY airports.csv airports.csv
EXPOSE 8080
ENTRYPOINT ["java","-jar","airports-search.jar","2"]
### Test the app ###
mvn test

### Build the app ###
mvn clean package

### Run the app using maven ###
mvn spring-boot:run

### Run the jar file ###
java -jar target/levenshtein-0.0.1-SNAPSHOT.jar







### Docker build ###
mvn package docker:build

### Run using docker ###
docker run -p 8080:8080 -t levenshtein-docker-image-prefix/levenshtein:latest
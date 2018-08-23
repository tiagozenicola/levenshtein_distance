### This is an implementation of Levenshtein_distance ###
More information can be found here: https://en.wikipedia.org/wiki/Levenshtein_distance



---

### Test the app ###
mvn test

### Build the app ###
mvn clean package

### Run the app using maven ###
mvn spring-boot:run

### Run the jar file ###
java -jar target/levenshtein-0.0.1-SNAPSHOT.jar




___



### Create new word ###
curl -X POST  localhost:8080/products -d '{"name":"`word1`"}' -H "Content-type: application/json"

### Read all words ###
curl localhost:8080/products

### Read specific word ###
curl localhost:8080/products/3

### Search for similar words (default limit is 3) ###
curl "localhost:8080/products/search?word=x&limit=10"
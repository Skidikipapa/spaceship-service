# SPACESHIP

## HOW-TO

### Deploy

Run
````
docker compose up
./gradlew bootRun
````

*Note: Java 17 is required to run this project*

### API

All requests are defined into `Spaceship.postman_collection.json`

### Database

PostreSQL v16
* Url: localhost
* Port: 5432
* Database: spaceship_data
* Username: spaceship
* Password: spaceship

### ActiveMQ

* Url: localhost
* Post: 8167

### Flyway migration

To execute Flyway migration execute the following steps.

Create baseline (only once)
````
./gradlew flywayBaseline -i
````
Do migration
````
./gradlew flywayMigrate
````

### Execute unit testing

````
./gradlew test
````
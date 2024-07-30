# Restaurant Reservation

### Running

#### using docker-compose
to run the application with docker-compose you just need run `docker-compose up` command on the terminal.

#### using gradle task (for development)
first you need update `env.sh` file with your enviroment configurations. after updating the env file, run `source env.sh` to export the variables to your terminal environment, then you can start the application by running `./gradlew bootRun`.

---

### Swagger
swagger ui with controllers documentation are available on `/swagger-ui/index.html` path.

---

### TODO
  - [ ] configure phone regex pattern on CreateRestaurantDto and UpdateRestaurantDto
  - [ ] endpoint to cancel reservation
  - [ ] response message code to frontend translations
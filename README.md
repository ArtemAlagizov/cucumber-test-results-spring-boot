[![Build Status](https://travis-ci.org/ArtemAlagizov/cucumber-test-results-spring-boot.svg?branch=master)](https://travis-ci.org/ArtemAlagizov/cucumber-test-results-spring-boot)
[![Coverage Status](https://img.shields.io/coveralls/github/ArtemAlagizov/cucumber-test-results-spring-boot.svg)](https://coveralls.io/github/ArtemAlagizov/cucumber-test-results-spring-boot?branch=master)

# Usage with Docker:
## Prerequisites:
  * Docker installed
  * Docker running
## Usage:
* To start the app
  ````
  docker-compose up
  ````
* To build a Docker image: in root folder run 
  ````
  docker build .
  ````
* Swagger is accessible here:
   * Docker Desktop:
      ```` 
      http://localhost:3036/swagger-ui.html
      ````
   * Docker toolbox:
     ````
     http://192.168.99.100:3036/swagger-ui.html
     ````

# Usage without Docker:
* Swagger is accessible here (once the app is started):
  ````
  http://localhost:8080/swagger-ui.html
  ````

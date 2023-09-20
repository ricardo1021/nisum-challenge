# nisum-challenge
Nisum Challenge Java Developer Spring Rest Api  
Author: Ricardo Gavilanez  
Date: 20-09-2023
## Introduction
* Restful api to create users.
* Endpoints must accept and return json format including error messages.
### Run the app
* Donwnload the git repository.
* Once you have the project in your local environment, run the main Springboot class named
   "UserApiRestfulApplication.java".
* Load this swagger url http://localhost:8080/swagger-ui/ where you can test all the endpoints using swagger api.
* This project has JWT as security module, so we need to generate
    a "Bearer token" using this own app, consume the AuthController endpoint.
  * You can use this credentials to generate the token:
    * "userName":"Ricardo"
    * "password": "pwd1"
  * Copy and paste the token in the authorization swagger api and then consumes the endpoints.  
* Here is a json request example:
  * {"name": "Juan Rodriguez","email": "juan@rodriguez.org","password": "hunter2","phones": [{"number": "1234567",
    "citycode": "1","contrycode": "57"}]}
### JUnit tests
* This SpringBoot project has JUnit test classes implemented with mockito mvc, so you can test
 the methods in controllers and services implementations.
* You have to copy the "Bearer Token" generated from the application 
    and paste in the static variable BEARER_TOKEN in the UserControllerTest Class, 
    then you can test each method.
#### NOTE: The Bearer token has an expire time of 10 hours.
#### Validations:
* The password has a regex validation, it must contains at least:
  * 1 upercase letter.
  * 1 lowercase letter.
  * 1 special character.
  * 1 digit 0-9.

* Exist an email validation so you need to fill a correct email format. 


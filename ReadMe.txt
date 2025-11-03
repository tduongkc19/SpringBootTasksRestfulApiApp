######################## READ ME #############################

##################--Dev Documentation--#######################
Author: Tommy Duong
Contact: tommy.duong.kc@gmail.com
Date: 11/2025

By following these steps, we have created a simple RESTful API 
using Spring Boot and Spring MVC. This setup provides a strong 
foundation for developing more complex APIs and adding additional 
features as needed.


Backend Tech Stack:
Spring Boot 3.5.6
Java Version 21
RxJS 7
Bootstrap 4

Dependencies:
Spring Web
Spring Boot DevTools
Spring Data JPA
Spring Framework
Spring Data Redis Version:7.4.6
Hibernate Validator (The Bean Validator)
Apache Commons Lang & Google Guava
JUnit 5 & Mockito
MySQL Driver
MapStruct
Lombok
Log4j

Frontend Tech Stack:
Angular 20

======================= API Testing  ===========================
Prerequisites:
Install Docker & Redis


1. Start Spring Boot Application
2. Start Docker
3. Start the Redis Server 
Open Docker Desktop on Windows, then click the Start 
button for both redis-stack and redis_persistent_container 
in the Containers tab.

Step 2: Verify that the Redis container is running.

-> docker ps

CONTAINER ID   IMAGE                      COMMAND                  CREATED      STATUS          PORTS                                                                                      NAMES
ddd7c2baef88   redis                      "docker-entrypoint.s…"   7 days ago   Up 52 minutes   6379/tcp                                                                                   redis_persistent_container
81165c2dd79b   redis/redis-stack:latest   "/entrypoint.sh"         7 days ago   Up 53 minutes   0.0.0.0:6379->6379/tcp, [::]:6379->6379/tcp, 0.0.0.0:8001->8001/tcp, [::]:8001->8001/tcp   redis-stack

Step 2: Connect to the Redis Database
Run the following command to connect to the server using redis-cli.

-> docker exec -it redis-stack redis-cli

Testing Redis container
127.0.0.1:6379> ping
PONG

Checking for cache example.
127.0.0.1:6379> keys *
1) "task::69"
2) "task::75"
3) "task::162"
4) "task::55"
5) "task::74"
6) "task::62"
7) "task::83"
8) "task::84"

Note: The above data is being pulled from the cache.


#---------------------------------------------------------------
REST-API-Spring Security Test
#---------------------------------------------------------------
Note: To enable or disable Spring Security, refer to the 
application.properties file for details.

Step-1: REST-API-Register Request

Endpoint: http://localhost:8080/api/v1/register

Request Body:

 {
    "username": "admin",
    "password": "password",
    "userEmail": "hello.world@gmail.com",
    "userPhone": "816-825-8548"
 }


Step-2: REST-API-Login Request

Endpoint: http://localhost:8080/api/v1/login

Request Body: 
 {
    "username": "admin",
    "password": "password"
    
 }

To Do: Copy the Bearer Token from the “Response” panel 
in Postman and save it for later use in the Authorization 
header in the next step.


Step-3: REST-API-Get Request

Endpoint: http://localhost:8080/api/v1/hello

Note: To access the API, you need to provide a Bearer Token.
In Postman, click on the “Authorization” tab, then select 
“Bearer Token” from the “Auth Type” dropdown menu.


#---------------------------------------------------------------
REST-API-Single Post Request
#---------------------------------------------------------------

Notes: Use Postman or cURL to test the endpoints.

Endpoint: http://localhost:8080/api/v1/tasks


JSON Request Body:

    {

        "taskTitle" : "Demo-Task-Title-1",
        "taskDescription": "Demo-Task description-1-Description should have at least 50 characters",
        "taskStatus":""
    }

#---------------------------------------------------------------
REST-API-Bulk Post Request
#---------------------------------------------------------------
Endpoint: http://localhost:8080/api/v1/tasks/bulk

Request Body:

[
    {

        "taskTitle" : "Demo-Task-Title-1",
        "taskDescription": "Demo-Task description-1-Description should have at least 50 characters",
        "taskStatus":"Test1New"
    },

    {

        "taskTitle" : "Demo-Task-Title-2",
        "taskDescription": "Demo-Task description-2-Description should have at least 50 characters",
        "taskStatus":""
    },

        {

        "taskTitle" : "Demo-Task-Title-3",
        "taskDescription": "Demo-Task description-3-Description should have at least 50 characters",
        "taskStatus":""
    }

]


#---------------------------------------------------------------
REST-API-Single Get Request
#---------------------------------------------------------------
Endpoint: http://localhost:8080/api/v1/tasks/63


#---------------------------------------------------------------
REST-API-Bulk Put Request
#---------------------------------------------------------------
Endpoint: http://localhost:8080/api/v1/tasks/bulk-update


JSON Request Body:

[
    {
        "taskId": 1,
        "taskTitle": "Demo-title-1-update-test",
        "taskDescription": "Demo-description-2-Description should have at least 50 characters",
        "taskStatus": ""
    },
    {
        "taskId": 2,
        "taskTitle": "Demo-Task-Title-2-update-test",
        "taskDescription": "Demo-Task description-1-Description should have at least 50 characters",
        "taskStatus": "Pending"
    }
]

#---------------------------------------------------------------
REST-API-Single Delete Request
#---------------------------------------------------------------
Endpoint: http://localhost:8080/api/v1/tasks/2


#---------------------------------------------------------------
REST-API-Pagination Search By Tile Request
#---------------------------------------------------------------
Endpoint: http://localhost:8080/api/v1/tasks/search-pages?title=hello&page=1&size=5

Example Parameters:
title: Hello  
page: 1 
size: 5

#---------------------------------------------------------------
REST-API-Single Search By Tile Request
#---------------------------------------------------------------
Endpoint: http://localhost:8080/api/v1/tasks/search?title=hello


#---------------------------------------------------------------

#---------------------------------------------------------------

#---------------------------------------------------------------

#---------------------------------------------------------------

#---------------------------------------------------------------

#---------------------------------------------------------------










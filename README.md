## Dev Documentation

<strong>Author:</strong> Tommy Duong
<br>
<strong>Contact:</strong> tommy.duong.kc@gmail.com

A simple yet highly scalable and robust RESTful API built with Spring Boot and Spring MVC. This setup provides a solid foundation for developing more complex APIs and integrating additional features as needed. It can be easily customized and optimized to meet your specific requirements for a production-ready environment.

<strong>Backend Tech Stack:</strong><br>
Spring Boot 3.5.6<br>
Java Version 21
<p>
<strong>Dependencies:</strong><br>
Spring Web, 
Spring Boot DevTools, 
Spring Data JPA, 
Spring Security, 
Spring Framework, 
Spring Data Redis Version:7.4.6, 
Hibernate Validator, 
Apache Commons Lang & Google Guava, 
JUnit 5 & Mockito, 
MySQL Driver, 
MapStruct, 
JSWT(JSON Web Token), 
Redis Bloom Filter, 
Actuator, 
Okhttp, 
Lombok, 
SLF4J, 
Log4j, 
Guava.



<p>
<strong>Frontend Tech Stack:</strong><br>
Angular 20, 
RxJS 7, 
Bootstrap 4 

### API Testing

<strong>Prerequisites:</strong><br>

<strong>Step-1: Install MySQL Database, Docker, and Redis Server.</strong>

0. Create a database table named devdb
1. Start Spring Boot Application
2. Start Docker
3. Start the Redis Server 
Open Docker Desktop on Windows, then click the Start 
button for both redis-stack and redis_persistent_container 
in the Containers tab.

<strong>Step 2: Verify that the Redis container is running.</strong>

-> docker ps

CONTAINER ID   IMAGE                      COMMAND                  CREATED      STATUS          PORTS                                                                                      NAMES
ddd7c2baef88   redis                      "docker-entrypoint.s…"   7 days ago   Up 52 minutes   6379/tcp                                                                                   redis_persistent_container
81165c2dd79b   redis/redis-stack:latest   "/entrypoint.sh"         7 days ago   Up 53 minutes   0.0.0.0:6379->6379/tcp, [::]:6379->6379/tcp, 0.0.0.0:8001->8001/tcp, [::]:8001->8001/tcp   redis-stack

<strong>Step 3: Connect to the Redis Database</strong>

Run the following command to connect to the server using redis-cli.

-> docker exec -it redis-stack redis-cli

Testing Redis container
127.0.0.1:6379> ping
PONG

Checking for cache example.
127.0.0.1:6379> keys *
1) "task::69"
2) "task::75"
...
7) "task::83"
8) "task::84"

<strong>Note:</strong> The above data is being pulled from the cache.

### REST-API-Spring Security Test
Note: To enable or disable Spring Security, refer to the 
application.properties file for details.

<strong>Step-1: REST-API-Register Request</strong>

Endpoint: http://localhost:8080/api/v1/register

Request Body:

 {
    "username": "admin",
    "password": "password",
    "userEmail": "hello.world@gmail.com",
    "userPhone": "816-825-8548"
 }


<strong>Step-2: REST-API-Login Request</strong>

Endpoint: http://localhost:8080/api/v1/login

Request Body: 
 {
    "username": "admin",
    "password": "password"
    
 }

<strong>To Do:</strong> Copy the Bearer Token from the “Response” panel 
in Postman and save it for later use in the Authorization 
header in the next step.


<strong>Step-3: REST-API-Get Request</strong>

Endpoint: http://localhost:8080/api/v1/hello

<strong>Note:</strong> To access the API, you need to provide a Bearer Token.
In Postman, click on the “Authorization” tab, then select 
“Bearer Token” from the “Auth Type” dropdown menu.


### REST-API-Single Post Request

<strong>Note:</strong> Use Postman or cURL to test the endpoints.

Endpoint: http://localhost:8080/api/v1/tasks


JSON Request Body:

    {

        "taskTitle" : "Demo-Task-Title-1",
        "taskDescription": "Demo-Task description-1-Description should have at least 50 characters",
        "taskStatus":""
    }


### REST-API-Bulk Post Request

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



### REST-API-Single Get Request

Endpoint: http://localhost:8080/api/v1/tasks/63



### REST-API-Bulk Put Request

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


### REST-API-Single Delete Request

Endpoint: http://localhost:8080/api/v1/tasks/2



### REST-API-Pagination Search By Tile Request

Endpoint: http://localhost:8080/api/v1/tasks/search-pages?title=hello&page=1&size=5

Example Parameters:

title: Hello
<br>
page: 1 
<br>
size: 5


### REST-API-Single Search By Tile Request

Endpoint: http://localhost:8080/api/v1/tasks/search?title=hello



### App Custom Health Indicators

Endpoints: http://localhost:8080/actuator/customStats

Response Body:

{
    "systemMode": "dev",
    "activeUsers": 120
}

#---------------------------------------------------------------


Endpoints: http://localhost:8080/actuator/health

Response Body:

{
    "status": "DOWN",
    "components": {
        "db": {
            "status": "UP",
            "details": {
                "database": "MySQL",
                "validationQuery": "isValid()"
            }
        },
        "diskSpace": {
            "status": "UP",
            "details": {
                "total": 1023026728960,
                "free": 484624191488,
                "threshold": 10485760,
                "path": "C:\\Users\\tduon\\Documents\\workspace-spring-tool-suite-4-4.21.1.Dev\\SpringBootTasksRestfulApiApp\\.",
                "exists": true
            }
        },
        "externalApi": {
            "status": "UP",
            "details": {
                "status": "API is reachable",
                "response": "Hello, World!"
            }
        },
        "mail": {
            "status": "DOWN",
            "details": {
                "location": "smtp.gmail.com:587",
                "error": "jakarta.mail.AuthenticationFailedException: 535-5.7.8 Username and Password not accepted. For more information, go to\n535 5.7.8  https://support.google.com/mail/?p=BadCredentials 46e09a7af769-7c6f0fb491asm7676068a34.15 - gsmtp\n"
            }
        },
        "mongo": {
            "status": "UP",
            "details": {
                "maxWireVersion": 27
            }
        },
        "ping": {
            "status": "UP"
        },
        "redis": {
            "status": "UP",
            "details": {
                "version": "7.0.15"
            }
        },
        "ssl": {
            "status": "UP",
            "details": {
                "validChains": [],
                "invalidChains": []
            }
        }
    }
}

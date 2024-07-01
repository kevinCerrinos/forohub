# API FORO-HUB
## Spring Boot, Spring Boot Security , PostgreSQL, JPA, Hibernate Rest API

Build Restful API application using Spring Boot, Postgres, JPA and Hibernate.

## Requirements

1. Java - 21

2. Maven - 3.x.x

3. PostgreSql - 16.x.x

## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/kevinCerrinos/forohub.git
```

**2. Create PostgreSql database**
```bash
create database foro
```

**3. Change PostgreSQL username and password as per your installation**

+ open `src/main/resources/application.properties`

+ change `spring.datasource.username` and `spring.datasource.password` as per your PostgreSql installation

**4. Build and run the app using maven**


you can run the app without packaging it using -

```bash
mvn spring-boot:run
```

The app will start running at <http://localhost:8080>.

+ all endpoints require an account to obtain the access token.

+ you can create an account at the following endpoint: http://localhost:8080/register then login in http://localhost:8080/login to obtain the access token

## Explore Rest APIs

The app defines following CRUD APIs.

    POST /curso
    
    POST /topico

    GET /topico

    GET /topico/{id}

    UPDATE /topico/{id}
    
    DELETE /topico/{id}
    
    PUT /topico/{id}/respuestas
    
    GET /respuesta
    
    PUT /respuesta/{id}

You can test and check the documentation them using the url http://localhost:8080/swagger-ui.html
    

alternative you can test them using postman or any other rest client.

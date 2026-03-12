# Spring MVC Practice

A simple REST API for user management built with Spring Boot.

## Tech Stack

- Java / Spring Boot 4.0.3
- Spring MVC, Spring Data JPA
- PostgreSQL
- MapStruct, Lombok, Bean Validation
- Spring Actuator

## Prerequisites

- Java 17+
- PostgreSQL running on `localhost:5432`

## Setup

1. Create a PostgreSQL database named `mvctest`
2. Update credentials in `src/main/resources/application.properties` if needed:
   ```
   spring.datasource.username=postgres
   spring.datasource.password=your_password
   ```
3. Run the app:
   ```bash
   ./mvnw spring-boot:run
   ```

The server starts at `http://localhost:8080/api`.

## API Endpoints

| Method | Endpoint          | Description      |
|--------|-------------------|------------------|
| GET    | `/api/user`       | Get all users    |
| GET    | `/api/user/{id}`  | Get user by ID   |
| POST   | `/api/user`       | Create user      |
| PUT    | `/api/user/{id}`  | Update user      |
| PATCH  | `/api/user/{id}`  | Partial update   |
| DELETE | `/api/user/{id}`  | Delete user      |
| GET    | `/actuator/health`| Health check     |

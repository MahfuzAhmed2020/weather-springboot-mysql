# Weather Spring Boot + MySQL + Swagger
- https://www.youtube.com/watch?v=95zTDdXbl_Q 

A beginner-friendly Spring Boot 3 application that:
- Calls the Weatherstack Current Weather API
- Stores each weather lookup in MySQL
- Exposes REST endpoints
- Provides Swagger/OpenAPI documentation
- Loads API/database configuration from `.env`

## Technology
- Java 17
- Spring Boot 3.5.6
- Maven
- Spring Web
- Spring Data JPA / Hibernate
- MySQL
- Swagger / OpenAPI
- Lombok

## 1. Create the MySQL database

Run:

```sql
CREATE DATABASE weatherdb;
```

The application creates the `weather_search` table automatically.

## 2. Configure `.env`

Open `.env` and change:

```properties
WEATHER_API_KEY=YOUR_WEATHERSTACK_API_KEY
DB_USERNAME=root
DB_PASSWORD=YOUR_MYSQL_PASSWORD
```

Do not commit a real API key or database password to Git.

## 3. Run

From the project directory:

```powershell
mvn clean spring-boot:run
```

Or in VS Code:

```powershell
mvn clean package
java -jar target/weather-api-0.0.1-SNAPSHOT.jar
```

## 4. Swagger

Open:

http://localhost:8080/swagger-ui/index.html

OpenAPI JSON:

http://localhost:8080/v3/api-docs

## 5. REST endpoints

### Get weather

```http
GET http://localhost:8080/api/weather?city=Buffalo
```

### Get saved searches

```http
GET http://localhost:8080/api/weather/history
```

### Get one saved search

```http
GET http://localhost:8080/api/weather/history/1
```

## 6. Example response

```json
{
  "id": 1,
  "city": "Buffalo",
  "temperature": 18,
  "feelsLike": 17,
  "description": "Partly cloudy",
  "searchedAt": "2026-10-06T18:00:00"
}
```

## Architecture

```text
Controller
    |
    v
Service
    |
    +----> Weatherstack API
    |
    v
Repository
    |
    v
MySQL
```

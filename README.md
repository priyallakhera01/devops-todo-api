# Todo REST API

A small Spring Boot REST API for creating and listing todos. It stores data in memory, so todos are cleared whenever the application restarts. No database is used.

## Prerequisites

- Java Development Kit (JDK) 17
- Apache Maven 3.6.3 or later

## Build

From the project root, run:

```powershell
mvn clean test
mvn package
```

The packaged application is created at `target/todo-api-0.0.1-SNAPSHOT.jar`.

## Run

```powershell
mvn spring-boot:run
```

The API listens on `http://localhost:8080`. To run the packaged JAR instead:

```powershell
java -jar target/todo-api-0.0.1-SNAPSHOT.jar
```

## API examples

### Check application health

```powershell
Invoke-RestMethod -Method Get -Uri http://localhost:8080/health
```

Example response:

```json
{"status":"UP"}
```

### List todos

```powershell
Invoke-RestMethod -Method Get -Uri http://localhost:8080/todos
```

Initially this returns an empty JSON array: `[]`.

### Create a todo

```powershell
$body = @{ title = 'Read a book' } | ConvertTo-Json
Invoke-RestMethod -Method Post -Uri http://localhost:8080/todos `
  -ContentType 'application/json' -Body $body
```

The API returns the new todo with HTTP `201 Created`, for example:

```json
{"id":1,"title":"Read a book"}
```

A missing or blank title returns HTTP `400 Bad Request` with a JSON error message.

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /todos | Retrieve all todos |
| POST | /todos | Create a new todo |
| GET | /health | Check application health |

## Git Workflow

This project uses Git branches for development.
Features are developed in separate branches and merged into the main branch.

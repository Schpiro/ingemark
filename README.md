# Ingemark Application

## Getting Started

To start the application, you only need to run:

```bash
docker-compose build
```
```bash
docker-compose up
```

This will build the Docker images and start the services, including the Spring Boot application and PostgreSQL database.

### Swagger Documentation
Once the application is running, you can access the API documentation via Swagger UI at:
http://localhost:8080/swagger-ui.html

### Ports
```
8080 – Spring Boot application
5432 – PostgreSQL database
5005 – Remote debugging port for the application
```
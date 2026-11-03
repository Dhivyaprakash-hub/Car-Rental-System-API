# Car Rental System API

A Java REST API project for a car-rental system. The application uses Spring Boot, Spring Data JPA and Hibernate for persistence, PostgreSQL, and Swagger-based API documentation. The project also includes exception handling.

## Technology

- Java 8
- Spring Boot 2.6.6
- Spring Data JPA and Hibernate
- PostgreSQL
- Springfox Swagger

## Run locally

1. Install Java 8 and PostgreSQL.
2. Set `DB_URL`, `DB_USERNAME`, and `DB_PASSWORD` in your environment before starting the app. The checked-in configuration contains no database password. The database schema must already exist because Hibernate is set to validate it.
3. Open a terminal in the CarRentalSystem-API folder.
4. On Windows, run mvnw.cmd spring-boot:run. On macOS or Linux, run ./mvnw spring-boot:run.

When the application is running, the existing Swagger UI is configured at http://localhost:8080/swagger-ui.html.

This is a learning project, not ready for public deployment. The current API has no authentication or authorization and exposes customer and booking endpoints; do not put real customer data into it or expose it to the internet until access control and ownership checks are added. Upgrade the older Spring Boot/Springfox stack before production use.

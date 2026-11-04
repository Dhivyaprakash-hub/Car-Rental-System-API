# Car Rental API

A modern Java 21 / Spring Boot reservation API rebuilt from the original CRUD coursework. It demonstrates vehicle inventory, date-range availability checks, server-side pricing, conflict handling, database migrations, and health probes. The local demo uses H2 and a seeded sample fleet; PostgreSQL can be supplied through environment variables.

## Run locally

From `CarRentalSystem-API/` with Java 21 installed:

```sh
./mvnw spring-boot:run
```

On Windows use `mvnw.cmd spring-boot:run`. The default database is in-memory H2, so a restart resets sample reservations. Health: `http://localhost:8080/actuator/health`.

## API

- `GET /api/v1/vehicles` — available demo fleet and daily rates
- `POST /api/v1/bookings` — reserve a vehicle for a date range
- `GET /api/v1/bookings/{reference}` — retrieve a non-personal reservation receipt by unguessable UUID

Example request:

```json
{"vehicleId":"10c88058-0c59-4d38-9d63-12de2e44a002","startDate":"2030-06-10","endDate":"2030-06-13"}
```

The service computes the total from the stored vehicle rate, rejects invalid date ranges, checks overlapping bookings while holding a vehicle row lock, and returns `409 Conflict` when a vehicle is already reserved. It never accepts a client-supplied total or stores renter identity/payment data.

## PostgreSQL and container

Set `DB_URL`, `DB_USERNAME`, and `DB_PASSWORD` to connect to PostgreSQL. Flyway migrations own schema creation; Hibernate validates it. Build with `./mvnw clean package`, then build the included Dockerfile from this directory. Keep real secrets in deployment secret settings.

## Production boundary

This is a production-shaped portfolio API, not a live rental business. It has no authentication, payment, cancellation workflow, or public write throttling. Keep it on local/demo data until those controls and operational policies are added; do not expose it as a real booking service.

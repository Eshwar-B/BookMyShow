# BookMyShow Monolith (Spring Boot)

Step-by-step implementation of a BookMyShow-like system using clean layering and production-oriented Spring Boot patterns.

## Step 1 Completed

- Project baseline and dependencies configured for Java 17 + Spring Boot monolith.
- Required backend package structure created:
  - `controller`, `service`, `repository`, `entity`, `dto`, `config`, `security`, `kafka`, `exception`
- Database schema design added with Flyway migration:
  - `users`, `movies`, `theatres`, `screens`, `shows`, `seats`
  - `show_seat_inventory` (seat_id + show_id + status + version)
  - `bookings`, `booking_seats`, `payments`

## Run Locally

1. Configure MySQL in environment variables (optional if using defaults):
   - `DB_URL`
   - `DB_USERNAME`
   - `DB_PASSWORD`
2. Start dependencies (MySQL/Kafka/Redis) as needed.
3. Run:
   - `./mvnw.cmd spring-boot:run` (Windows)

## Design Patterns Planned (implemented from upcoming steps)

- **Strategy Pattern**: payment method behaviors.
- **Factory Pattern**: payment/notification object creation.
- **Builder Pattern**: booking aggregate creation workflow.
- **Singleton Pattern**: centralized producer/config instances.
- **Observer Pattern**: domain event subscribers (alongside Kafka flow).

## Class Naming Convention (strict)

- Service classes: `src/main/java/com/bookmyshow/service` with names like `userService.java`.
- Repository classes: `src/main/java/com/bookmyshow/repository` with names like `userRepository.java`.
- Entity classes: `src/main/java/com/bookmyshow/entity` with names like `userEntity.java`.

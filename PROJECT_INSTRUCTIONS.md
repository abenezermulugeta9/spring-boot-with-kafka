# Library Events Producer - Project Instructions

## Project Overview
**Library Events Producer** is a Spring Boot application (v3.5.11) designed to produce library events to Apache Kafka. This is a learning/tutorial project focused on Kafka integration with Spring.

**Key Details:**
- Java 17 (toolchain enforced)
- Spring Boot 3.5.11
- Spring Kafka integration
- RESTful API with validation
- Package: `com.learnkafka.library_events_producer`
- Build tool: Gradle

## Build & Compilation

### Build the Project
```bash
# Navigate to project root
cd D:\Personal Projects\Java\spring-boot-with-kafka\library-events-producer

# Build using Gradle wrapper
./gradlew build

# For Windows:
gradlew.bat build
```

### Clean Build
```bash
./gradlew clean build
```

### Run Tests
```bash
./gradlew test
```

## Running the Application

### Start the Application
```bash
./gradlew bootRun
```
The application will start on the default Spring Boot port (usually 8080).

### Verify It's Running
Once started, the application should:
- Be accessible at `http://localhost:8080`
- Have Kafka configured (check application.properties for Kafka broker settings)
- Be ready to produce events to Kafka topics

## Project Structure

```
library-events-producer/
├── src/
│   ├── main/
│   │   ├── java/com/learnkafka/library_events_producer/
│   │   │   └── LibraryEventsProducerApplication.java     # Main app class
│   │   └── resources/
│   │       └── application.properties                     # Configuration
│   └── test/                                              # Unit & integration tests
├── build.gradle                                           # Gradle build configuration
├── settings.gradle                                        # Gradle settings
├── gradlew / gradlew.bat                                 # Gradle wrappers
└── HELP.md                                               # Auto-generated help

**Note:** The actual application code is minimal in this project. Controllers, services, models, and Kafka producers should be added in `src/main/java/com/learnkafka/library_events_producer/`.
```

## Dependencies

### Core Dependencies
- **Spring Boot Starter Web**: For REST API endpoints
- **Spring Kafka**: Apache Kafka integration
- **Spring Boot Starter Validation**: Bean validation support
- **Lombok**: Annotation processor for boilerplate reduction

### Test Dependencies
- **Spring Boot Starter Test**: JUnit 5, AssertJ, Mockito, etc.
- **Spring Kafka Test**: Embedded Kafka for testing

### Gradle Plugins
- **Spring Boot Gradle Plugin** (v3.5.11)
- **Dependency Management Plugin** (v1.1.7)

## Development Tasks

### Common Tasks to Implement
1. **Create Domain Models** - Library, Book, LibraryEvent classes with validation
2. **Build REST Controllers** - Endpoints to create/update library events
3. **Implement Kafka Producers** - Send events to Kafka topics
4. **Add Request/Response DTOs** - Validation and serialization
5. **Configure Kafka** - Update `application.properties` with broker settings
6. **Write Integration Tests** - Use embedded Kafka (spring-kafka-test)

### IDE Setup
- **IntelliJ IDEA** (recommended for Spring/Kotlin projects)
  - Import as Gradle project
  - Ensure Java 17 SDK is configured
  - Lombok plugin may be needed

## Important Notes

### Package Naming
- Original package name `com.learnkafka.library-events-producer` is invalid (hyphens not allowed)
- Project uses `com.learnkafka.library_events_producer` instead (underscores)
- **Always use underscores** when creating Java packages in this project

### Configuration
- Default application name: `library-events-producer`
- Kafka properties should be added to `application.properties` (e.g., bootstrap servers, topic names)
- Use Spring profiles for different environments (dev, test, prod)

### Testing Strategy
- Use JUnit Platform (configured via `useJUnitPlatform()`)
- Embedded Kafka available via `spring-kafka-test` for integration tests
- Test classes should be in `src/test/java` with same package structure

## Common Commands Cheat Sheet

```bash
# Build & Test
./gradlew clean build              # Full clean build + tests
./gradlew build --info             # Build with debug info
./gradlew test                      # Run tests only
./gradlew test --tests "ClassName" # Run specific test class

# Development
./gradlew bootRun                  # Run the application
./gradlew compileJava              # Compile only
./gradlew tasks                    # List all available tasks

# Gradle Wrapper (Windows)
gradlew.bat build                  # Build on Windows
gradlew.bat bootRun                # Run on Windows
```

## Troubleshooting

### Java Version Issues
- Verify Java 17 is installed: `java -version`
- Check Gradle is using correct JDK: `./gradlew javaToolchains`

### Build Failures
- Clean and rebuild: `./gradlew clean build`
- Check for compilation errors: `./gradlew compileJava`
- Review Gradle output with more details: `./gradlew build --info`

### Kafka Connection Issues
- Verify Kafka broker is running (if not using embedded Kafka in tests)
- Check `application.properties` for correct `spring.kafka.bootstrap-servers`
- For testing, rely on `spring-kafka-test` embedded Kafka

## Next Steps

1. **Define domain models** for library events (Book, Library, Event classes)
2. **Create REST endpoints** in a new Controller class
3. **Implement Kafka Producer** to send events to topics
4. **Configure Kafka settings** in `application.properties`
5. **Add integration tests** using embedded Kafka
6. **Document API endpoints** (consider Springdoc OpenAPI for Swagger)

## References
- [Spring Kafka Documentation](https://spring.io/projects/spring-kafka)
- [Spring Boot 3.5.11 Docs](https://docs.spring.io/spring-boot/docs/3.5.11/reference/html/)
- [Gradle Documentation](https://docs.gradle.org)
- [Apache Kafka](https://kafka.apache.org/)

---
**Last Updated:** 2026-02-22  
**Java Version:** 17  
**Spring Boot Version:** 3.5.11

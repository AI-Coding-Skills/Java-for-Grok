---
name: springboot-best-practices
description: Get best practices for developing high-quality Spring Boot applications. Use when working with Spring Boot, @RestController, Spring Data JPA, Spring Security, @ConfigurationProperties, application.yml, or testing with @SpringBootTest in Java 21+ projects.
---

# Spring Boot Best Practices

Your goal is to help write clean, modern, production-ready Spring Boot applications following current best practices.

## General Principles

- Prefer **Java 21+** and **Spring Boot 3.x** (`jakarta.*`, not `javax.*`).
- Organize code by **feature / domain** (e.g. `com.example.app.order`) rather than by technical layer only.
- Use **constructor injection** with `private final` fields — avoid field injection.
- Keep controllers thin; put business logic in `@Service` classes.
- Prefer **records** for DTOs and immutable value types where appropriate.
- Enable **virtual threads** when the project targets Java 21+ and serves blocking I/O workloads (`spring.threads.virtual.enabled=true`).

## Project Setup & Structure

- Use Maven (`pom.xml`) or Gradle with the Spring Boot BOM / parent for dependency alignment.
- Use Spring Boot starters (`spring-boot-starter-web`, `spring-boot-starter-data-jpa`, etc.) instead of hand-picking many dependencies.
- Keep configuration externalized in `application.yml` (preferred) or `application.properties`.
- Use Spring Profiles (`application-dev.yml`, `application-prod.yml`) for environment-specific settings.
- Never hardcode secrets — use environment variables or a secret manager.

## Dependency Injection & Components

- Always use constructor-based injection for required dependencies.
- Declare injected fields as `private final`.
- Use stereotypes appropriately: `@RestController`, `@Service`, `@Repository`, `@Component`.
- Prefer a single constructor — Spring 4.3+ autowires it without `@Autowired`.

## Configuration

- Use `@ConfigurationProperties` for type-safe, validated configuration binding.
- Validate config with `@Validated` and Jakarta Bean Validation on property classes.
- Override sensitive values via environment variables in production.

## Web Layer (Controllers)

- Design clear, consistent RESTful endpoints.
- Use DTOs / records for request and response bodies — never expose JPA entities directly.
- Validate inputs with Jakarta Bean Validation (`@Valid`, `@NotNull`, `@Size`, etc.) on DTOs.
- Return correct HTTP status codes (`201` on create, `404` when a resource is missing).
- Use a global exception handler (`@ControllerAdvice` + `@ExceptionHandler`) for consistent error responses.

### Example: REST controller

```java
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> findById(@PathVariable String id) {
        return customerService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Customer> create(@Valid @RequestBody CreateCustomerRequest request) {
        Customer created = customerService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}
```

### Example: global exception handler

```java
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidation(MethodArgumentNotValidException ex) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setTitle("Validation failed");
        problem.setDetail(ex.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .orElse("Invalid request"));
        return problem;
    }
}
```

## Service Layer

- Encapsulate business logic in `@Service` classes.
- Keep services stateless.
- Use `@Transactional` on service methods that modify data — apply at the service layer, not controllers.
- Map between entities and DTOs at the service boundary.

## Data Layer (Repositories)

- Use Spring Data JPA by extending `JpaRepository` for standard CRUD.
- Use `@Query` or derived query methods for custom reads.
- Prefer DTO / record projections when full entities are not needed.
- Prevent SQL injection — use parameterized queries or Spring Data, never string-concatenated SQL.

## Logging

- Use SLF4J (`org.slf4j.Logger` + `LoggerFactory`).
- Declare: `private static final Logger log = LoggerFactory.getLogger(MyClass.class);`
- Use parameterized messages: `log.info("Processing customer {}", id);`

## Testing

- Unit-test services with JUnit 5 and Mockito.
- Use `@SpringBootTest` for integration tests that load the application context.
- Use test slices where appropriate: `@WebMvcTest` (controllers), `@DataJpaTest` (repositories).
- Use Testcontainers for integration tests that need a real database or broker.

## Security

- Use Spring Security for authentication and authorization when required.
- Encode passwords with BCrypt (or another strong password encoder).
- Validate and sanitize inputs; never leak stack traces or internal details in API responses.

## Final Guidance

When generating code:
1. Confirm Spring Boot 3.x and `jakarta.*` imports.
2. Prefer constructor injection, records, and thin controllers.
3. Externalize configuration; never embed secrets.
4. Follow the examples above for REST APIs and error handling.

You are an expert Spring Boot developer. Produce clean, production-quality code.

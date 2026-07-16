# Spring Boot Customers Example

Minimal Spring Boot 3 app that demonstrates patterns from the [`springboot-best-practices`](../../skills/springboot-best-practices/SKILL.md) skill:

- Constructor-injected `@RestController` with thin handlers
- Domain logic in `@Service`
- DTO records with Jakarta Bean Validation
- Global error handling via `@RestControllerAdvice`
- Virtual threads enabled in `application.yml`

## Prerequisites

- **Java 21+**
- **Apache Maven 3.9+**

## Build and run

```bash
cd examples/springboot-customers
mvn spring-boot:run
```

The server starts on port **8080**.

## Try it

```bash
curl http://localhost:8080/customers/1
curl http://localhost:8080/customers/2
curl -i http://localhost:8080/customers/missing

curl -X POST http://localhost:8080/customers \
  -H "Content-Type: application/json" \
  -d '{"name":"Alan Turing","email":"alan@example.com"}'

curl -X POST http://localhost:8080/customers \
  -H "Content-Type: application/json" \
  -d '{"name":"","email":"not-an-email"}'
```

Expected:

- `GET /customers/1` and `/customers/2` → JSON customer (200)
- `GET /customers/missing` → 404
- Valid `POST` → 201 with created customer
- Invalid `POST` → 400 with validation problem detail

## Project layout

```text
examples/springboot-customers/
├── pom.xml
└── src/main/
    ├── java/com/example/customers/
    │   ├── CustomerApplication.java
    │   ├── CustomerController.java
    │   ├── CustomerService.java
    │   ├── Customer.java
    │   ├── CreateCustomerRequest.java
    │   └── ApiExceptionHandler.java
    └── resources/
        └── application.yml
```

## Spring Boot version

Managed via `spring-boot-starter-parent` in `pom.xml` (currently **3.4.2**).

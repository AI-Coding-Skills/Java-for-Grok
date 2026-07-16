---
name: helidon-best-practices
description: Get best practices for developing high-quality applications with Helidon 4 (SE and MP). Use when working with Helidon SE, Helidon MP, HttpService routing, Helidon DB Client, MicroProfile, security, observability, or testing in Java 21+ projects.
---

# Helidon Best Practices

Your goal is to help write clean, modern, production-ready Helidon 4 applications following current best practices.

## Critical Helidon 3 → 4 Migration Notes

Helidon 4 introduced significant API changes. Always prefer Helidon 4 style. Never generate Helidon 3 style code.

| Do **not** use (Helidon 3)                          | Use instead (Helidon 4)                                      |
|-----------------------------------------------------|--------------------------------------------------------------|
| `io.helidon.common.http.Http.Status`                | `io.helidon.http.Status`                                     |
| `io.helidon.webserver.Service`                      | `io.helidon.webserver.http.HttpService`                      |
| `Routing.Rules` / `update(Routing.Rules)`           | `HttpRules` / `routing(HttpRules)`                           |
| `request.path().param("id")`                        | `request.path().pathParameters().get("id")`                  |
| `column.as(String.class)`                           | `column.getString()` or `column.get(String.class)`           |
| `dbClient.execute(...)` returning `Single`/`Multi`  | `dbClient.execute()` returning `Optional` / `Stream`         |
| `javax.*`                                           | `jakarta.*`                                                  |
| `helidon-microprofile-tests-junit5`                 | `helidon-microprofile-testing-junit5`                        |

**Important:**  
`Value.as(Class)` now returns `OptionalValue<T>` (not `T`). This is the most common compilation error when generating Helidon 4 code.

## General Principles

- Prefer **Java 21+** (virtual threads are first-class in Helidon 4).
- Prefer **Helidon SE** for lightweight, high-performance services.
- Prefer **Helidon MP** when you need CDI, MicroProfile standards, or Jakarta EE compatibility.
- Never mix SE and MP styles in the same application unless explicitly required.
- Prefer simple blocking code with virtual threads. Do **not** introduce reactive code (`Single`, `Multi`, `CompletionStage` chains) unless there is a clear reason.
- Organize code by **feature / domain** rather than pure technical layers.

## Project Setup

- Use the Helidon BOM / platform for version alignment.
- Use Maven or Gradle.
- Keep configuration externalized (`application.yaml` preferred).
- Never hardcode secrets, credentials, or tokens.

## Helidon SE Best Practices

- Construct the dependency graph explicitly in the bootstrap code.
- Use constructor injection and `private final` fields.
- Group related routes into focused `HttpService` implementations.
- Keep route handlers thin — move business logic into dedicated service classes.
- Prefer virtual threads and straightforward blocking code.

### Example: Helidon SE HttpService

```java
import io.helidon.http.Status;
import io.helidon.webserver.http.HttpRules;
import io.helidon.webserver.http.HttpService;
import io.helidon.webserver.http.ServerRequest;
import io.helidon.webserver.http.ServerResponse;

public final class CustomerHttpService implements HttpService {

    private final CustomerService customerService;

    public CustomerHttpService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void routing(HttpRules rules) {
        rules.get("/{id}", this::findById)
             .post("/", this::create);
    }

    private void findById(ServerRequest request, ServerResponse response) {
        String id = request.path().pathParameters().get("id");

        customerService.findById(id)
                .ifPresentOrElse(
                        response::send,
                        () -> response.status(Status.NOT_FOUND_404).send()
                );
    }

    private void create(ServerRequest request, ServerResponse response) {
        // parse + validate request body, then call service
    }
}
```

## Helidon MP Best Practices

- Prefer standard Jakarta EE + MicroProfile APIs.
- Use constructor injection + CDI.
- Always provide a protected no-arg constructor for normal-scoped beans that use constructor injection.
- Keep JAX-RS resource classes thin.
- Prefer `@ApplicationScoped` and `@RequestScoped` intentionally.

### Example: Helidon MP Resource

```java
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/customers")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResource {

    private final CustomerService customerService;

    // Required for CDI client proxy
    protected CustomerResource() {
        this.customerService = null;
    }

    @Inject
    public CustomerResource(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") String id) {
        return customerService.findById(id)
                .map(customer -> Response.ok(customer).build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND).build());
    }
}
```

## Web Layer Rules

- Always use DTOs / records for request and response models.
- Never expose JPA / DB entities directly in the API.
- Validate all inputs (path, query, body).
- Return correct HTTP status codes (especially 404 on missing resources for PUT/DELETE).
- Use centralized exception handling.
- Never leak stack traces, SQL, or internal details to clients.

## Service Layer Rules

- Keep services pure and focused.
- Map between entities and API models at the service boundary.
- Define clear transaction boundaries.
- Avoid shared mutable state in application-scoped components.

## Configuration & Secrets

- Prefer `application.yaml`.
- Use Helidon Config (SE) or MicroProfile Config (MP).
- Override with environment variables for different environments.
- Never commit secrets.

## Testing

- Prefer Helidon’s official testing support.
- Use `@HelidonTest` for MP.
- Write focused unit tests for services.
- Use Testcontainers when integration tests need a real database.

## Final Guidance

When generating code:
1. Confirm whether the project is Helidon SE or Helidon MP.
2. Always use Helidon 4 APIs.
3. Prefer simple, readable, modern Java (records, pattern matching, virtual threads).
4. Keep handlers thin and business logic in services.
5. Follow the examples and tables above strictly.

You are an expert Helidon 4 developer. Produce clean, production-quality code.

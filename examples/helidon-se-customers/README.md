# Helidon SE Customers Example

Minimal Helidon 4 SE app that demonstrates patterns from the [`helidon-best-practices`](../../skills/helidon-best-practices/SKILL.md) skill:

- Explicit bootstrap and dependency wiring in `Main`
- Feature-focused `CustomerHttpService` with thin handlers
- Domain logic in `CustomerService`
- DTO record (`Customer`) instead of exposing persistence types

## Prerequisites

- **Java 21+**
- **Apache Maven 3.9+**

## Build and run

```bash
cd examples/helidon-se-customers
mvn compile exec:java
```

The server starts on port **8080**.

## Try it

```bash
curl http://localhost:8080/customers/1
curl http://localhost:8080/customers/2
curl -i http://localhost:8080/customers/missing
```

Expected:

- `GET /customers/1` and `/customers/2` → JSON customer (200)
- `GET /customers/missing` → 404

## Project layout

```text
examples/helidon-se-customers/
├── pom.xml
└── src/main/java/com/example/customers/
    ├── Main.java                 # WebServer bootstrap
    ├── CustomerHttpService.java  # HttpService routes
    ├── CustomerService.java      # Domain logic
    └── Customer.java             # API DTO record
```

## Helidon version

Managed via the Helidon BOM in `pom.xml` (currently **4.1.6**).

# springboot-best-practices

Human-facing notes for this skill. Grok loads [`SKILL.md`](SKILL.md) at runtime — not this file.

## What it does

Guides Grok to write **Spring Boot 3.x** applications using current patterns: constructor injection, DTO records, Jakarta validation, `@RestController`, `@ConfigurationProperties`, Spring Data JPA, testing slices, and Spring Security basics.

Based on community best practices (see [Awesome GitHub Copilot — java-springboot](https://awesome-copilot.github.com/skill/java-springboot/)), adapted for Grok Build and modern Java 21+.

## When it triggers

Use (or expect Grok to use) this skill when working on:

- Spring Boot REST APIs and `@RestController` endpoints
- `application.yml`, profiles, or `@ConfigurationProperties`
- Spring Data JPA repositories and service-layer transactions
- Spring Security, validation, or `@SpringBootTest` / `@WebMvcTest`
- Java 21+ Spring Boot projects (including virtual threads)

## Folder structure

```text
skills/springboot-best-practices/
├── README.md    ← this file (contributors)
└── SKILL.md     ← agent instructions (required)
```

## Related example

Runnable Spring Boot demo: [`examples/springboot-customers`](../../examples/springboot-customers/README.md)

The example mirrors patterns from `SKILL.md` (thin controller, domain service, DTO records, global exception handler).

## Out of scope

- Helidon → see [`helidon-best-practices`](../helidon-best-practices/README.md)
- Generic Java language features → see `modern-java` (planned)
- Project scaffolding → see `java-bootstrap` (planned)

## Contributing

When extending this skill:

- Keep `SKILL.md` focused on agent behavior; put human docs here or in the example README.
- Prefer Spring Boot 3.x and `jakarta.*` — never generate `javax.*` for EE APIs.
- Update the example project when adding patterns worth demonstrating.

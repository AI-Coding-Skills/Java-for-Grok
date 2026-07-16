# helidon-best-practices

Human-facing notes for this skill. Grok loads [`SKILL.md`](SKILL.md) at runtime — not this file.

## What it does

Guides Grok to write **Helidon 4** (SE and MP) code using current APIs and patterns: virtual threads, blocking handlers, `HttpService` routing, Jakarta EE / MicroProfile, DTOs, and Helidon 3 → 4 migration rules.

## When it triggers

Use (or expect Grok to use) this skill when working on:

- Helidon SE or Helidon MP applications
- `HttpService`, `HttpRules`, Helidon DB Client
- MicroProfile, security, observability, or testing in Helidon projects
- Java 21+ services that should follow Helidon 4 conventions

## Folder structure

```text
skills/helidon-best-practices/
├── README.md    ← this file (contributors)
└── SKILL.md     ← agent instructions (required)
```

Optional additions later: `references/` for long-form docs linked from `SKILL.md`.

## Related example

Runnable Helidon SE demo: [`examples/helidon-se-customers`](../../examples/helidon-se-customers/README.md)

The example mirrors patterns from `SKILL.md` (thin `HttpService`, domain service, DTO records, explicit bootstrap).

## Out of scope

- Spring Boot → see [`springboot-best-practices`](../springboot-best-practices/README.md)
- Generic Java language features → see `modern-java` (planned)
- Project scaffolding → see `java-bootstrap` (planned)

## Contributing

When extending this skill:

- Keep `SKILL.md` focused on agent behavior; put human docs here or in the example README.
- Prefer Helidon 4 blocking style — avoid Helidon 3 reactive APIs (`Single`, `Multi`, `.await()`).
- Update the example project if you add new patterns worth demonstrating.

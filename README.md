# Java-for-Grok

**Community-maintained Java application development skills for Grok Build**

This repository provides high-quality skills for building modern Java applications with Grok Build, focusing on:

- **Helidon** (SE & MP)
- **Spring Boot**
- Modern Java (21+)
- Project bootstrapping & scaffolding
- Testing, observability, and best practices

> **Disclaimer**: This is a community project. It is **not** affiliated with or endorsed by Oracle, the Helidon project, Spring, or SpaceXAI / xAI.

## Available Skills

| Skill | Description | Status |
|-------|-------------|--------|
| `helidon-best-practices` | Best practices for Helidon 4 (SE + MP) | ✅ Ready |
| `springboot-best-practices` | Best practices for modern Spring Boot | ✅ Ready |
| `java-bootstrap` | Scaffold clean Java / Helidon / Spring Boot projects | 🔜 Coming soon |
| `modern-java` | Records, virtual threads, pattern matching, etc. | 🔜 Planned |

## Installation (Grok Build)

Once this plugin is accepted into the official marketplace, you will be able to install it with:

```bash
grok plugin install java
```

Or install locally from a clone of this repository:

```bash
grok plugin validate .
grok plugin install . --trust
```

Or manually by adding a remote source pointing to this repository (with a pinned commit SHA).

## Testing

### 1. Validate and install the plugin

From the repository root:

```bash
grok plugin validate .
grok plugin install . --trust
grok plugin list
grok inspect --json
```

Confirm `helidon-best-practices` and `springboot-best-practices` appear under the `java` plugin in `grok inspect` output.

### 2. Run the examples

- Helidon SE: [examples/helidon-se-customers/README.md](examples/helidon-se-customers/README.md)
- Spring Boot: [examples/springboot-customers/README.md](examples/springboot-customers/README.md)

### 3. Exercise the skills in Grok

Open Grok in a Java project and ask framework-specific questions.

**Helidon 4:**

- “Add a Helidon SE `HttpService` for customers with GET `/{id}`”
- “Migrate this handler from Helidon 3 APIs to Helidon 4”

Grok should prefer Helidon 4 APIs (`io.helidon.http.Status`, `HttpRules`, `pathParameters()`, `jakarta.*`).

Skill notes: [skills/helidon-best-practices/README.md](skills/helidon-best-practices/README.md)

**Spring Boot 3:**

- “Add a `@RestController` for customers with validation and proper HTTP status codes”
- “Add a `@RestControllerAdvice` for consistent validation errors”

Grok should prefer constructor injection, DTO records, `jakarta.*` validation, and `@Service` for business logic.

Skill notes: [skills/springboot-best-practices/README.md](skills/springboot-best-practices/README.md)

## Plugin Structure

```text
Java-for-Grok/
├── README.md
├── LICENSE
├── plugin.json
├── examples/
│   ├── helidon-se-customers/        # Runnable Helidon SE demo
│   └── springboot-customers/        # Runnable Spring Boot demo
├── skills/
│   ├── helidon-best-practices/
│   │   ├── README.md
│   │   └── SKILL.md
│   ├── springboot-best-practices/
│   │   ├── README.md
│   │   └── SKILL.md
│   ├── java-bootstrap/              (coming soon)
│   └── ...
└── .grok-plugin/
    └── plugin.json
```

## Contributing

Contributions are welcome!

- Open issues for new skill ideas
- Submit PRs with new skills or improvements
- Keep skills focused, practical, and high quality

Please follow the existing style of the Helidon skill when adding new ones.

## License

Apache License 2.0

---

**Maintained by the community**  
Organization: [AI-Coding-Skills](https://github.com/AI-Coding-Skills)

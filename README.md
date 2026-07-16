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
| `springboot-best-practices` | Best practices for modern Spring Boot | 🔜 Coming soon |
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

Confirm `helidon-best-practices` appears under the `java` plugin in `grok inspect` output.

### 2. Run the Helidon SE example

See [examples/helidon-se-customers/README.md](examples/helidon-se-customers/README.md) for build and curl steps.

### 3. Exercise the skill in Grok

Open Grok in a Java/Helidon project and ask Helidon 4 questions, for example:

- “Add a Helidon SE `HttpService` for customers with GET `/{id}`”
- “Migrate this handler from Helidon 3 APIs to Helidon 4”

Grok should prefer Helidon 4 APIs (`io.helidon.http.Status`, `HttpRules`, `pathParameters()`, `jakarta.*`).

Skill-specific notes: [skills/helidon-best-practices/README.md](skills/helidon-best-practices/README.md)

## Plugin Structure

```text
Java-for-Grok/
├── README.md
├── LICENSE
├── plugin.json
├── examples/
│   └── helidon-se-customers/        # Runnable Helidon SE demo
├── skills/
│   ├── helidon-best-practices/
│   │   ├── README.md
│   │   └── SKILL.md
│   ├── springboot-best-practices/   (coming soon)
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

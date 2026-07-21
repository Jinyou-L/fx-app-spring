# Spring Boot notes

- `@Configuration`: marks the class as a source of Spring bean definitions.
- `@EnableAutoConfiguration`: configures sensible beans from the classpath and properties.
- `@ComponentScan`: finds components below the application class package.

Property precedence is defaults, `application.properties`, environment variables, then command-line arguments; the last value wins, so a CLI `--server.port=8081` overrides `server.port=8080`.

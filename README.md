# Spring Boot Starter K8S Template

## What is this?

This is a template repository for kicking off a cloud native spring boot java microservice.

## What makes this cloud native?

It's solely focused on deployment to kubernetes, the primary build artifacts of the repository are a OCI image and a helm chart. The helm chart is dynamically generated using jkube and it's associated gradle plugin.

## What opinions have applied to this?

- Gradle
- Spring Boot
- Spring Flux
- Spring R2DBC (Reactive Database)
- Netty
- JKube (Kubernetes Deployment and Development Tools)
- Liquibase

## How do I run locally?

Use `make run`.

## How do I run locally outside of a container?

If you wish to run directly in your IDE:

1. Run `docker-compose up -d` to start the postgres database server.
2. Run `./gradlew update bootRun`, `update` runs the migrations and `bootRun` launches the spring boot application

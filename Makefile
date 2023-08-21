.PHONY: jar
jar:
	./gradlew bootJar

.PHONY: image
image: jar
	docker buildx build . -t ghcr.io/bryopsida/spring-boot-starter-k8s:local

run:
	docker run -p 8080:8080 -p 8081:8081 ghcr.io/bryopsida/spring-boot-starter-k8s:local
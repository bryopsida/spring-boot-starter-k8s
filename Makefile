HELM_NAMESPACE ?= spring-boot-starter-k8s

.PHONY: jar
jar:
	./gradlew bootJar

.PHONY: image
image: jar
	docker buildx build . -t ghcr.io/bryopsida/spring-boot-starter-k8s:local

run:
	docker run -p 8080:8080 -p 8081:8081 ghcr.io/bryopsida/spring-boot-starter-k8s:local

create-namespace:
	kubectl create namespace $(HELM_NAMESPACE)

build-chart:
	./gradlew k8sResource k8sHelm

build-dependencies:
	helm dependency build ./build/jkube/helm/spring-boot-starter-k8s/kubernetes/

deploy: build-chart build-dependencies
	helm --namespace=$(HELM_NAMESPACE) upgrade --install spring-boot-starter-k8s ./build/jkube/helm/spring-boot-starter-k8s/kubernetes/ --set image_tag=local

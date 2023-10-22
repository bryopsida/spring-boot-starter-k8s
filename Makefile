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

template: build-chart build-dependencies
	helm --namespace=$(HELM_NAMESPACE) template ./build/jkube/helm/spring-boot-starter-k8s/kubernetes/ --output-dir ./build/template-render-out

kics: template
	docker run -t -v ./build/template-render-out:/path checkmarx/kics scan -p /path --fail-on=high,medium \
		--exclude-paths=/path/spring-boot-starter-k8s/charts/postgres \
		--exclude-paths=/path/spring-boot-starter-k8s/templates/db-migration-secret.yaml \
		--exclude-queries=611ab018-c4aa-4ba2-b0f6-a448337509a6,aee3c7d2-a811-4201-90c7-11c028be9a46,7c81d34c-8e5a-402b-9798-9f442630e678,8b36775e-183d-4d46-b0f7-96a6f34a723f,4a20ebac-1060-4c81-95d1-1f7f620e983b,48a5beba-e4c0-4584-a2aa-e6894e4cf424,b9c83569-459b-4110-8f79-6305aa33cb37,e84eaf4d-2f45-47b2-abe8-e581b06deb66
clean:
	mvn clean

compile:
	mvn compile

run: compile
	mvn spring-boot:run

build:
	mvn package

run-jar: build
	java -jar target/spring-gumball-1.0.jar

# Docker

docker-build: build
	docker build -t spring-gumball .
	docker images

docker-run: docker-build
	docker run --name spring-gumball -td -p 80:8080 spring-gumball	
	docker ps

docker-clean:
	docker stop spring-gumball
	docker rm spring-gumball
	docker rmi spring-gumball

docker-shell:
	docker exec -it spring-gumball bash 

docker-push:
	docker login
	docker build -t $(account)/spring-gumball:latest -t $(account)/spring-gumball:latest .
	docker push $(account)/spring-gumball:latest 

# Jump Box Docker

all: clean

jumpbox:
	docker run --network test --name jumpbox -t -d ubuntu

shell:
	docker exec -it jumpbox bash 

clean:
	docker stop jumpbox
	docker rm jumpbox

jumpbox-create:
	kubectl create -f jumpbox.yaml

jumpbox-get:
	kubectl get pod jumpbox

jumpbox-shell:
	kubectl exec  -it jumpbox -- /bin/bash

jumpbox-delete:
	kubectl delete pod jumpbox

jumpbox-tools:
	apt-get update
	apt-get install curl
	apt-get install iputils-ping

all: clean

clean:
	mvn clean

compile:
	mvn compile

run: compile
	mvn spring-boot:run

build:
	mvn package

run-jar: build
	java -jar target/spring-gumball-3.1.jar


# MySQL DB

mysql:
	docker run --platform=linux/amd64 -d --network gumball --name mysql -td -p 3306:3306 -e MYSQL_ROOT_PASSWORD=cmpe172 mysql:8.0

mysql-shell:
	docker exec -it mysql bash


# Docker

docker-build: build
	docker build --platform=linux/amd64 -t spring-gumball .
	docker images

gumball-network:
	docker network create --driver bridge gumball

docker-run: docker-build
	docker run --platform=linux/amd64 --network gumball -e "MYSQL_HOST=mysql" --name spring-gumball -td -p 8080:8080 spring-gumball

docker-clean:
	docker stop spring-gumball
	docker rm spring-gumball
	docker rmi spring-gumball

docker-shell:
	docker exec -it spring-gumball bash

# Compose

network-ls:
	docker network ls

network-create:
	docker network create --driver bridge $(network)

network-prune:
	docker network prune

compose-up:
	docker-compose up --scale gumball=2 -d

lb-up:
	docker-compose up -d lb

gumball-up:
	docker-compose up -d gumball

mysql-up:
	docker-compose up -d mysql

compose-down:
	docker-compose down

lb-stats:
	echo "user = admin | password = admin"
	open http://localhost:1936

lb-test:
	open http://localhost
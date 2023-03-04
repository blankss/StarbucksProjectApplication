clean:
	mvn clean

compile:
	mvn compile

run: compile
	mvn spring-boot:run

build:
	mvn package

run-jar: build
	java -jar target/spring-gumball-v2-1.0.jar

# Docker

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
	docker build -t $(account)/spring-gumball:v2.0 .
	docker push $(account)/spring-gumball:v2.0
clean:
	mvn clean

compile:
	mvn compile

run: 
	mvn spring-boot:run

build:
	mvn package

run-jar: build
	java -jar target/spring-gumball-1.0.jar

# Docker

docker-build: run-jar
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

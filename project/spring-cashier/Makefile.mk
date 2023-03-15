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
	java -jar target/spring-cashier-1.0.jar

docker-build: 
	docker build -t spring-cashier .
	docker images

docker-clean:
	docker stop spring-cashier
	docker rm spring-cashier

docker-shell:
	docker exec -it starbucks bash 

network-create:
	docker network create --driver bridge starbucks

network-inspect:
	docker network inspect starbucks

docker-run:
	docker run --network starbucks --name spring-cashier -p 90:9090  \
	-e "api_endpoint=http://spring-starbucks-api:8080" -td starbucks-nodejs
			
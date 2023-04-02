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

#mysql need to change to run in the network of starbucks
mysql:
	docker run -d --network spring-payments --name mysql -td -p 3306:3306 -e MYSQL_ROOT_PASSWORD=cmpe172 mysql:8.0

#mysql terminal 
# create database cmpe172;
# create user 'admin'@'%' identified by 'welcome';
# grant all on cmpe172.* to 'admin'@'%';


#docker need to change to run in the network of starbucks

docker-build: build
	docker build -t spring-cashier .
	docker images

docker-run:
	docker run --network spring-payments -e "MYSQL_HOST=mysql" --name spring-cashier -td -p 8080:8080 spring-cashier

docker-clean:
	docker stop spring-cashier
	docker rm spring-cashier

docker-shell:
	docker exec -it starbucks bash 

network-create:
	docker network create --driver bridge starbucks

network-inspect:
	docker network inspect starbucks

# docker-run:
# 	docker run --network starbucks --name spring-cashier -p 90:9090  \
# 	-e "api_endpoint=http://spring-starbucks-api:8080" -td starbucks-nodejs
			
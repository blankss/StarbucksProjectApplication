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

jumpbox:
	docker run --network spring-payments --name jumpbox -t -d ubuntu

#mysql need to change to run in the network of starbucks
mysql:
	docker run -d --network spring-payments --name mysql -td -p 3306:3306 -e MYSQL_ROOT_PASSWORD=cmpe172 mysql:8.0

#docker run -d --network kong-network --name mysql -td -p 3306:3306 -e MYSQL_ROOT_PASSWORD=cmpe172 mysql:8.0

mysql-up:
	docker-compose up -d mysql

#mysql terminal 
# create database cashier;
# create user 'admin'@'%' identified by 'welcome';
# grant all on midterm.* to 'admin'@'%';

#mysql terminal 
# create database cashier;
# create user 'happy'@'%' identified by 'camper';
# grant all on cashier.* to 'happy'@'%';

# Redis DB

redis-local:
	docker run --platform=linux/amd64 --name redis --network spring-payments -td -p 6379:6379 redis

redis-official:
	docker run --platform=linux/amd64 --name redis --network spring-payments -td -p 6379:6379 redis:4.0

redis-shell:
	docker exec -it redis bash 

#redis-cli -h localhost -p 6379
redis-up:
	docker-compose up -d redis

#docker need to change to run in the network of starbucks

docker-build: build
	docker build -t spring-cashier .
# 	docker images

docker-run:
	docker run --network spring-payments -e "MYSQL_HOST=mysql" --name spring-cashier -td -p 8080:8080 spring-cashier

#docker run -d --name starbucks-api --network kong-network -td spring-starbucks-api
#docker run --network kong-network -e "MYSQL_HOST=mysql" --name spring-cashier -td -p 8080:8080 spring-cashier

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
			
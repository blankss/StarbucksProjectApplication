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

kong-reload:
	http :8001/config config=@kong.yaml
	docker exec -it $(id) kong reload

# http localhost:85/ping apikey:2H3fONTa8ugl1IcVS7CjLPnPIS2Hp9dJ

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

# CREATE TABLE SPRING_SESSION (
#   PRIMARY_ID CHAR(36) NOT NULL,
#   SESSION_ID CHAR(36) NOT NULL,
#   CREATION_TIME BIGINT NOT NULL,
#   LAST_ACCESS_TIME BIGINT NOT NULL,
#   MAX_INACTIVE_INTERVAL INT NOT NULL,
#   EXPIRY_TIME BIGINT NOT NULL,
#   PRINCIPAL_NAME VARCHAR(100),
#   CONSTRAINT SPRING_SESSION_PK PRIMARY KEY (PRIMARY_ID)
# ) ENGINE=InnoDB ROW_FORMAT=DYNAMIC;

# CREATE UNIQUE INDEX SPRING_SESSION_IX1 ON SPRING_SESSION (SESSION_ID);
# CREATE INDEX SPRING_SESSION_IX2 ON SPRING_SESSION (EXPIRY_TIME);
# CREATE INDEX SPRING_SESSION_IX3 ON SPRING_SESSION (PRINCIPAL_NAME);

# CREATE TABLE SPRING_SESSION_ATTRIBUTES (
#   SESSION_PRIMARY_ID CHAR(36) NOT NULL,
#   ATTRIBUTE_NAME VARCHAR(200) NOT NULL,
#   ATTRIBUTE_BYTES BLOB NOT NULL,
#   CONSTRAINT SPRING_SESSION_ATTRIBUTES_PK PRIMARY KEY (SESSION_PRIMARY_ID, ATTRIBUTE_NAME),
#   CONSTRAINT SPRING_SESSION_ATTRIBUTES_FK FOREIGN KEY (SESSION_PRIMARY_ID) REFERENCES SPRING_SESSION(PRIMARY_ID) ON DELETE CASCADE
# ) ENGINE=InnoDB ROW_FORMAT=DYNAMIC;
			
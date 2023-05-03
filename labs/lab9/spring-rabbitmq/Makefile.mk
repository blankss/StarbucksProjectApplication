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
	java -jar target/spring-rabbitmq-1.0.jar

rabbitmq:
	docker run -d --hostname my-rabbit --name some-rabbit rabbitmq:3
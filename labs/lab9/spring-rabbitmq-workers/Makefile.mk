all: clean

clean:
	mvn clean

compile:
	mvn compile

run: compile
	mvn spring-boot:run

build: compile
	mvn package

run-jar: build
	java -jar target/spring-rabbitmq-helloworld-1.0.jar

rabbitmq:
	docker run -d --hostname my-rabbit --name some-rabbit rabbitmq:3

send: 
	java -jar target/spring-rabbitmq-1.0.jar \
	--spring.profiles.active=$(env),work-queues,sender

receive: 
	java -jar target/spring-rabbitmq-1.0.jar \
	--spring.profiles.active=$(env),work-queues,receiver
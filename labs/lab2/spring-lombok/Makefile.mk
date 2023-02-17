clean:
	mvn clean

compile:
	mvn compile

run: 
	mvn spring-boot:run

build:
	mvn package

run-jar: build
	java -jar target/spring-lombok-1.0.jar
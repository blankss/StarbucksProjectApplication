default:
	echo "Make Rules: starbucks-network, starbucks-api-run, cashier-nodejs-run, starbucks-app-run"

starbucks-network:
	docker network create --driver bridge starbucks

starbucks-api-run: 
	docker run --network starbucks --name spring-starbucks-api -td -p 8080:8080 paulnguyen/spring-starbucks-api:v3.1	

cashier-spring-run:
	docker run --network starbucks --name spring-cashier -p 90:9090  \
	-e "api_endpoint=http://spring-starbucks-api:8080" -td paulnguyen/starbucks-nodejs:v3.0

cashier-nodejs-run:
	docker run --network starbucks --name starbucks-nodejs -p 90:9090  \
	-e "api_endpoint=http://spring-starbucks-api:8080" -td paulnguyen/starbucks-nodejs:v3.0

starbucks-app-run: 
	java -cp starbucks-app.jar \
		-Dapiurl="http://34.172.21.123:80/api/" \
		-Dapikey="2H3fONTa8ugl1IcVS7CjLPnPIS2Hp9dJ" \
		-Dregister="5012349" \
		starbucks.Main 2>debug.log

# docker build --platform=linux/amd64 -t blankss/spring-starbucks-api:v3.1 .
# docker build --platform=linux/amd64 -t blankss/spring-cashier:latest .
# docker build --platform=linux/amd64 -t blankss/starbucks-worker:latest .

# docker push blankss/spring-starbucks-api:v3.1
# docker push blankss/spring-cashier:latest
# docker push blankss/starbucks-worker:latest


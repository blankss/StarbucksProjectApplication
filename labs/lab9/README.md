# CMPE 172 - Lab #9 Notes

## Lab Notes Discussion:
* Spring Profiles:
  * Basically a set of configuration properties in the code that is activated in different environments depending on what profile is active
  * From the provided code, we can see that Spring Profiles are used for loading the corresponding application properties file from the $(env) variable needed in the make command and the respective @Beans in the Spring java code.
  * This can be used to differentiate which application properties file to use so that we can have multiple application properties for different circumstances such as development, deployment, and etc.
* RabbitMQ:
  * RabbitMQ allows us to asynchronously communicate between systems in a loosely coupled way
  * Asynchronous messages allow for our project/application to not need to wait for messages and processing time, independently running applications can process queues of messages/actions for our project without waiting
  * Using RabbitMQ allows tasks to not be blocked when receiving multiple messages through the usage of a queue system with consumers that consume/accomplish the message sent

## Spring RabbitMQ - Simple Messages
<img width="1440" alt="Screen Shot 2023-05-03 at 12 47 51 PM" src="https://user-images.githubusercontent.com/72158949/236067845-9d7a3600-9d8d-4486-88fc-3406e80d85de.png">
<img width="1440" alt="Screen Shot 2023-05-03 at 12 48 11 PM" src="https://user-images.githubusercontent.com/72158949/236067848-5065704a-d903-4cbd-9eb4-ab26986264a5.png">


## Spring RabbitMQ - Hello World
<img width="1440" alt="Screen Shot 2023-05-03 at 1 56 02 PM" src="https://user-images.githubusercontent.com/72158949/236067793-2ee6ace1-4223-48c8-867a-da8488f2a38e.png">
<img width="1440" alt="Screen Shot 2023-05-03 at 1 57 10 PM" src="https://user-images.githubusercontent.com/72158949/236067811-d2348177-87ef-477a-8d4d-9b6f72f9fd2e.png">
<img width="1440" alt="Screen Shot 2023-05-03 at 2 02 13 PM" src="https://user-images.githubusercontent.com/72158949/236067824-32ef0074-c46d-44cf-8a94-cddad26ce757.png">
<img width="1440" alt="Screen Shot 2023-05-03 at 2 07 14 PM" src="https://user-images.githubusercontent.com/72158949/236067831-ce1db186-cc5d-4354-b1eb-99a21b9b1c52.png">
<img width="1440" alt="Screen Shot 2023-05-03 at 2 09 07 PM" src="https://user-images.githubusercontent.com/72158949/236067832-44df16c3-c6b5-4eb6-8bac-740973f20a73.png">

## Spring RabbitMQ - Workers
### RabbitMQ Consoel and Command Line for Sending
<img width="1440" alt="Screen Shot 2023-05-03 at 2 58 16 PM" src="https://user-images.githubusercontent.com/72158949/236067837-483759d9-0d30-47ab-ab25-3ac641ca631c.png">
### RabbitMQ Console and Command Line for Receiving
<img width="1440" alt="Screen Shot 2023-05-03 at 3 09 53 PM" src="https://user-images.githubusercontent.com/72158949/236067843-6c5efcc6-e1fd-4cb3-9027-8546f97e0870.png">

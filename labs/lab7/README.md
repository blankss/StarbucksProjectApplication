# CMPE 172 - Lab #7 Notes

## Part 1: Sample Payment Screen Output
<img width="1440" alt="Screen Shot 2023-04-24 at 2 23 17 PM" src="https://user-images.githubusercontent.com/72158949/234122969-f8de996d-909b-41b4-94dc-4c06388860d8.png">


## Part 2: Results of a Successful Payment with E2E Payment Details
### Successful Form Submission
<img width="1440" alt="Screen Shot 2023-04-24 at 2 23 25 PM" src="https://user-images.githubusercontent.com/72158949/234123215-118f1de1-6354-41c7-ae07-59a0a07edd82.png">

### Terminal Output of Capturing the Transaction (CyberSource API Results)
<img width="1440" alt="Screen Shot 2023-04-24 at 2 24 21 PM" src="https://user-images.githubusercontent.com/72158949/234123283-e73cc6da-9c1c-4601-85e2-f5f8a93d7db8.png">
<img width="1440" alt="Screen Shot 2023-04-24 at 2 24 29 PM" src="https://user-images.githubusercontent.com/72158949/234123529-8f1d5205-c3cc-4ff1-868a-fa49d2703a6d.png">

### Cybersource EBC Transactions Dashboard with Github Username (blankss)
<img width="1440" alt="Screen Shot 2023-04-24 at 2 27 13 PM" src="https://user-images.githubusercontent.com/72158949/234123566-3500624a-3732-4600-8246-9dadc746ccae.png">
<img width="1440" alt="Screen Shot 2023-04-24 at 2 27 25 PM" src="https://user-images.githubusercontent.com/72158949/234123568-16b93d02-21e7-4866-b90c-9ee5953a7d59.png">
<img width="1440" alt="Screen Shot 2023-04-24 at 2 27 31 PM" src="https://user-images.githubusercontent.com/72158949/234123575-09dc1b2a-359e-4b93-9e4d-299772c711af.png">

## Lab Notes

### Discussion of Lombok, Thymeleaf, and Spring Features
#### CyberSource API
In order for us to use the CyberSource API, we need to put the necessary properties in the application.properties file. We use merchantkeyid, merchantsecretkey, merchantid, and apihost credentials given by the professor to form a connection with the API. This allows us to use the API for our payment processes as seen in the above screenshots.

#### Lombok and Spring Data
For Lombok, I have used this Spring feature to automatically help us generate the Getters, Setters, and Constructors for the class of PaymentsCommands through the usage of the @Data annotation tag. This way, we do not need to manually type out all the basic methods and constructors that the class needs and instead hands off this requirement for Spring to automatically implement. Also, to automatically create the PAYMENTS table in the H2 database automatically, we use the @Entity tag and the @Table(name="Payments") to specify the table and the contents of the table with the instance variables that we have in the class. 

<img width="1440" alt="Screen Shot 2023-04-24 at 2 57 34 PM" src="https://user-images.githubusercontent.com/72158949/234125656-de2c5b6a-e097-4599-8000-edc94c06ba69.png">

#### H2 Database
From the PaymentsCommand.java class, the @Entity and @Table generated a Payments table in the H2 database. Here we can see a successful entry in the Payments table once there is a submission of the form with the correct details. Of course, in order to use the H2 database, we would need to define the necessary properties in application.properties and the necessary dependencies in pom.xml. <br />
<img width="1440" alt="Screen Shot 2023-04-24 at 2 53 47 PM" src="https://user-images.githubusercontent.com/72158949/234125096-45262572-3e43-47a4-b58a-564d7deb2a3b.png">
<img width="1440" alt="Screen Shot 2023-04-24 at 2 53 56 PM" src="https://user-images.githubusercontent.com/72158949/234125100-0293fe7a-112d-4bf1-a84a-2ac1b090ebab.png">

#### Thymeleaf
By using Thymeleaf, we can send the information submitted from the view (the HTML page which is creditcards.html in this case) to the controller to parse the data accordingly. In this case, the th:field="*{someField}" in the HTML tyhmeleaf file signifies that the information passed from the user will be sent to the controller as an object that is signified by th:object="${command}". This simplifies the process of parsing the information and creating the object in the controller.
<img width="1440" alt="Screen Shot 2023-04-24 at 2 57 22 PM" src="https://user-images.githubusercontent.com/72158949/234125584-4c188ca2-25f1-42b3-bde8-5eede62008e7.png">
<img width="1440" alt="Screen Shot 2023-04-24 at 2 57 34 PM" src="https://user-images.githubusercontent.com/72158949/234125586-cbbedb25-65ab-48b7-8175-7978eb4c186b.png">

### Java JSON Using Jackson
For this lab, Jackson is used mostly in the following files in springcybersource:
* AuthResponse.java
* CaptureResponse.java
* Payload.java
* RefundResponse.java
From looking at these files, I can infer that Jackson is being used to generate a response to an action in the creditcard.html form, specifically error and success messages of the object. Jackson is primarily responsible for displaying (serializing and deserializing) the Java object, in this case PaymentsCommands, for when we interact with the form page. ObjectMapper and JsonNode from Jackson are used to accomplish this in all the response files.

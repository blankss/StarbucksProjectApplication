# CMPE 172 - Lab #4 Notes

## Screenshots and Discussion confirming a working Spring Security project

By adding security configurations inside of our WebSecurityConfig.java file, we successfully added a layer of security by only allowing authenticated users to see the secret greeting page of "Hello world!". Without it, any user can access the greeting page by going to localhost:8080/hello. From the assignment page, we know that the SecuriyFilterChain bean takes care of allowing the "/" and "/home" page to be accessible by any user to direct them to the "Welcome" page; however, any other page would require the user to be authenticated in the "/login" page. For simplicity, we set up a single local user with basic credentials for testing purposes in the UserDetailsService bean.

<img width="1440" alt="Screen Shot 2023-03-04 at 12 04 30 PM" src="https://user-images.githubusercontent.com/72158949/222928060-0aecadd5-380a-4e77-932e-b61c047d9487.png">
<img width="1440" alt="Screen Shot 2023-03-04 at 12 04 57 PM" src="https://user-images.githubusercontent.com/72158949/222928063-bff70ffa-c711-4b7a-abbe-7eb1bb5f11fb.png">
<img width="1440" alt="Screen Shot 2023-03-04 at 12 05 11 PM" src="https://user-images.githubusercontent.com/72158949/222928064-6be58a07-e55d-4857-87eb-d78206b63cdd.png">
<br />

## Screenshots of your deployment of Spring Gumball (Version 2) to Docker Host

<img width="1440" alt="Screen Shot 2023-03-04 at 2 30 24 PM" src="https://user-images.githubusercontent.com/72158949/222931739-0a9bac2f-58b6-4fee-bca7-43e352fffb19.png">
<img width="1440" alt="Screen Shot 2023-03-04 at 2 30 55 PM" src="https://user-images.githubusercontent.com/72158949/222931744-8ddc5a29-6c35-4157-a81b-3b644e8e38e9.png">
<img width="1440" alt="Screen Shot 2023-03-04 at 2 40 28 PM" src="https://user-images.githubusercontent.com/72158949/222931804-eb671b65-5239-45ec-800b-b8beffc14229.png">
<br />

## Screenshots of Gumball App (Version 2) Running on Docker Host with your observations and answers to the following question
### 1. Do you see any errors that were observed in Spring Gumball (Version 1)? <br />
With this version, we do not encounter the Whitelabel error with HTTP status code 500 that we have expected for Spring Gumball version 1 as we have removed the session dependent code from the GumballMachineController.java file. Turning the crank and inserting the quarter is working fine.
### 2. Why or Why Not?  Explain the technical details to support your observation. <br />
As mentioned before, the session dependent code from Gumball version 1, specifically in the @PostMapping portion, was removed so we will not encounter the Whitelabel error we have encountered in the previous lab. Instead, this lab compares the hash and the timestamp to validate the user in addtion to storing the state of the machine. Also, since we removed displaying the data of how many gumballs the user have, there is no data to store on the server for the user. If we do not have session dependent code, then we naturally can run our application seamlessly without enabling cookies. We can also see the Server Host/IP chaning in the last two screenshots, which demonstrates that we do not have sessions turned on anymore as the load balancer does not send us back to the same backend server. <br />
The hash is used to ensure that the request from the user is a valid request and the role of the timestamp ensures the thwarting of replay attacks. 1000ms is the maximum length of time for the request to take; malicious users cannot manually use replay attacks by intercepting requests using tools such as Tamper Dev as it would already exceed 1000ms. <br />
<img width="1440" alt="Screen Shot 2023-03-04 at 2 44 15 PM" src="https://user-images.githubusercontent.com/72158949/222932005-8a49fa33-374b-4e81-9128-bbc58da6dd50.png">
<img width="1440" alt="Screen Shot 2023-03-04 at 2 44 59 PM" src="https://user-images.githubusercontent.com/72158949/222932006-836ac4c0-d84c-471a-8e6a-59399c43db09.png">
<img width="1440" alt="Screen Shot 2023-03-04 at 2 45 21 PM" src="https://user-images.githubusercontent.com/72158949/222932010-e4012ab8-ba01-4c59-a0f8-43632682e52c.png">
<img width="1440" alt="Screen Shot 2023-03-04 at 2 45 34 PM" src="https://user-images.githubusercontent.com/72158949/222932011-8a7422e9-f659-4a53-9abd-19fb377edbe6.png">
<br />

## Screenshots of Spring Gumball Replay Attack (Before & After)
If we use the initial Spring Gumball v2.0 program locally, we would be able to demonstrate a replay attack as there were no security measures in place to counter the attack. We have to run the code locally as Spring Gumball v2.0 still uses sessions, which would mean that we would get a Whitelabel error if we do docker-compose up without setting cookies to be true. After rebuilding the application with the modified Spring Gumball v2.0 code, we were successfully able to block the replay attack that used Tamper Dev as the code has specified a timestamp that ensures the received request did not exceed the specified length of time, which was 1000ms in this case. If the request takes longer than the specified length of time, the program wil throw a _GumballServerError()_ which is similar to the one if the hash comparison is false. 
<img width="1440" alt="Screen Shot 2023-03-04 at 3 14 06 PM" src="https://user-images.githubusercontent.com/72158949/222933228-5090eb42-e57b-4b5f-98b8-ad86283d44d3.png">
<img width="1440" alt="Screen Shot 2023-03-04 at 3 15 07 PM" src="https://user-images.githubusercontent.com/72158949/222933230-fa15be12-d566-41a5-b20d-de4dd328215b.png">
<img width="1440" alt="Screen Shot 2023-03-04 at 3 15 37 PM" src="https://user-images.githubusercontent.com/72158949/222933232-68c5ad68-64bd-4a16-9802-84d51a962cbf.png">
<img width="1440" alt="Screen Shot 2023-03-04 at 3 22 58 PM" src="https://user-images.githubusercontent.com/72158949/222933233-32a44d14-23f4-4277-ab5f-ae2fedfb7f38.png">
<img width="1440" alt="Screen Shot 2023-03-04 at 3 26 11 PM" src="https://user-images.githubusercontent.com/72158949/222933236-38ad6e31-a9cc-4781-b7de-d3c129b3488c.png">


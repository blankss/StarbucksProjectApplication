# CMPE 172 - Lab #4 Notes

## Screenshots and Discussion confirming a working Spring Security project

By adding security configurations inside of our WebSecurityConfig.java file, we successfully added a layer of security by only allowing authenticated users to see the secret greeting page of "Hello world!". Without it, any user can access the greeting page by going to localhost:8080/hello. From the assignment page, we know that the SecuriyFilterChain bean takes care of allowing the "/" and "/home" page to be accessible by any user to direct them to the "Welcome" page; however, any other page would require the user to be authenticated in the "/login" page. For simplicity, we set up a single local user with basic credentials for testing purposes in the UserDetailsService bean.

<img width="1440" alt="Screen Shot 2023-03-04 at 12 04 30 PM" src="https://user-images.githubusercontent.com/72158949/222928060-0aecadd5-380a-4e77-932e-b61c047d9487.png">
<img width="1440" alt="Screen Shot 2023-03-04 at 12 04 57 PM" src="https://user-images.githubusercontent.com/72158949/222928063-bff70ffa-c711-4b7a-abbe-7eb1bb5f11fb.png">
<img width="1440" alt="Screen Shot 2023-03-04 at 12 05 11 PM" src="https://user-images.githubusercontent.com/72158949/222928064-6be58a07-e55d-4857-87eb-d78206b63cdd.png">


## Screenshots of your deployment of Spring Gumball (Version 2) to Docker Host

## Screenshots of Gumball App (Version 2) Running on Docker Host with your observations and answers to the following question

## Screenshots of Spring Gumball Replay Attack (Before & After)

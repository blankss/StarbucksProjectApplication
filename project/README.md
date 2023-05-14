# CMPE 172 Project Journal
This section addresses the discussion and questions in the GKE demo assignment. Basically, this section is reserved for the discussion of the final product deployed on GKE.

## Screenshots
### Cashier
<img width="1440" alt="Screen Shot 2023-05-13 at 5 26 21 PM" src="https://github.com/nguyensjsu/cmpe172-blankss/assets/72158949/c59613e1-06af-4b5e-bdc4-e57c30207c88">
<img width="1440" alt="Screen Shot 2023-05-13 at 5 26 41 PM" src="https://github.com/nguyensjsu/cmpe172-blankss/assets/72158949/af50a7c3-214f-4488-82e5-8fc2c03a3e64">
<img width="1440" alt="Screen Shot 2023-05-13 at 5 31 43 PM" src="https://github.com/nguyensjsu/cmpe172-blankss/assets/72158949/ed486ffb-bc90-49e4-a7c3-5679c279919a">
<img width="1440" alt="Screen Shot 2023-05-13 at 5 31 52 PM" src="https://github.com/nguyensjsu/cmpe172-blankss/assets/72158949/e81a80e5-d09d-4290-9413-b3fdeb998066">
<img width="1440" alt="Screen Shot 2023-05-13 at 5 32 09 PM" src="https://github.com/nguyensjsu/cmpe172-blankss/assets/72158949/1bd0301a-5354-402b-969e-d73d2e26afc2">
<img width="1440" alt="Screen Shot 2023-05-13 at 5 33 29 PM" src="https://github.com/nguyensjsu/cmpe172-blankss/assets/72158949/103eb82f-fd8c-4a22-af7d-5e4ef0bc8691">
<img width="1440" alt="Screen Shot 2023-05-13 at 5 33 47 PM" src="https://github.com/nguyensjsu/cmpe172-blankss/assets/72158949/6f70778e-907f-488b-aeae-8ebb1b479d11">
<img width="1440" alt="Screen Shot 2023-05-13 at 5 34 06 PM" src="https://github.com/nguyensjsu/cmpe172-blankss/assets/72158949/6237c972-101d-4510-bf88-99f6ae5a89b8">
<img width="1440" alt="Screen Shot 2023-05-13 at 5 34 32 PM" src="https://github.com/nguyensjsu/cmpe172-blankss/assets/72158949/4dac9ec9-6a93-4bac-9a46-3078144833ff">
<img width="1440" alt="Screen Shot 2023-05-13 at 5 34 54 PM" src="https://github.com/nguyensjsu/cmpe172-blankss/assets/72158949/24415b42-39ba-49fd-baff-38a5df844977">

### Starbucks API
<img width="1440" alt="Screen Shot 2023-05-13 at 5 41 36 PM" src="https://github.com/nguyensjsu/cmpe172-blankss/assets/72158949/3460c70a-5a88-4df3-8813-f73989c2f21a">
<img width="1440" alt="Screen Shot 2023-05-13 at 5 41 55 PM" src="https://github.com/nguyensjsu/cmpe172-blankss/assets/72158949/d4a3ed9a-221c-4745-aef5-92ed6c143a1e">
<img width="1440" alt="Screen Shot 2023-05-13 at 5 42 06 PM" src="https://github.com/nguyensjsu/cmpe172-blankss/assets/72158949/1a289211-acd3-4626-ac88-29a3c7c76ec8">
<img width="1440" alt="Screen Shot 2023-05-13 at 5 42 21 PM" src="https://github.com/nguyensjsu/cmpe172-blankss/assets/72158949/0d414b7c-7d43-41bf-bde1-c503ce78f82f">

### Cloud Deployments
1. Design Notes on GitHub an Architecture Diagram of the overall Deployment.
2. How does your Solution Scale? Can it handle > 1 Million Mobile Devices? Explain. <br />
    My solution scales similarly to horizontal scaling in which multiple instances of each resource can be brought up to deal with an increase in traffic. The resources to scale would be the number of cashiers, api's, and workers. Since these resources are located behind a load balancer, the load balancer would take on the role of splitting up the traffic to be routed to available backends. In this way, the solution should be able to handle > 1 million mobile devices through scaling the resources in the GKE horizontally by adding more deployments/replicas of cashiers, api's, and workers as stated previously. If we disregard the limitations of the current implementation such as starbucks-app only having 1 register, cost, and spring-cashier only having 5 registers with a max of 1 active order per register, the horizontal scaling should be enough to handle a large amount of traffic.

**NOTE:** Upon approval from the professor, students can just refer to the journal section for the earliest fulfillment of the technical requirements without needing to screenshot more if it is already in the journal. These technical requirements can also be seen to be fulfilled on GKE as well during the demo video.

## Technical Requirements
### Cashier's App (25 points)
- Port Node.js App to Spring MVC (required)
  - Refer to journal 5 for completed Spring Cashier
- Web rendering must be done in View Templates
  - Refer to journal 7 for completed Spring Cashier view templates (register, login, etc) 
- Controller must process JSON responses from API and pass to View via Models
  -  Refer to journal 8 for getting JSON responses from API, I have also parsed the information from the JSON and formatted it a bit in the latest version of my controller in cashier
- Output and "Look and Feel" of Web UI must match that of Node.js App
  - Refer to journal 7 for UI of templates 
- Implementation must not just use Rest Client example code (from instructor) as-is
  - Implementation of Cashier's App does not use starbucks-client
- Support Admin Logins for Starbucks Employees (5 points)
    - Refer to journal 7 for correct implementation of registration and login
- Must not store credentials in memory or hard code in source code
    - Refer to journal 7, dynamically create the user on registration
- Should also include New Account Registration and Logout
    - Refer to journal 7 for creation of user during runtime
- Support In Store Order Processing (20 points -- See Diagram Below)
    - Refer to journal 10 for the fulfillment of the UML diagram (placing of the order to payment processing)

### Scalable Cloud Deployment on GCP  (25 points)
- External Load Balancer as Ingress for Cashier's App (10 points)
 <img width="1440" alt="Screen Shot 2023-05-13 at 3 06 18 PM" src="https://github.com/nguyensjsu/cmpe172-blankss/assets/72158949/a6b24dd2-a8de-4bef-9708-18ec039cb380">
 
 <br />
 
- Internal Load Balancer for Starbucks API behind Kong API Gateway (15 points)
<img width="1440" alt="Screen Shot 2023-05-13 at 3 06 38 PM" src="https://github.com/nguyensjsu/cmpe172-blankss/assets/72158949/ebe4e601-567d-4692-a43a-b13fcc20d4e2">

### Implementation Uses Required Cloud Databases (25 points)
- MySQL Database 8.0 (15 points)
    <img width="1440" alt="Screen Shot 2023-05-13 at 3 07 10 PM" src="https://github.com/nguyensjsu/cmpe172-blankss/assets/72158949/8068e5ce-f45c-4496-bf92-6fb55c4ef836">
- Must use Cloud SQL (MySQL Option)
    - Refer to above screenshot of deploying Cloud MySQL
- Update Starbucks API to use JPA with MySQL
- RabbitMQ (10 points)
    <img width="1440" alt="Screen Shot 2023-05-13 at 3 07 44 PM" src="https://github.com/nguyensjsu/cmpe172-blankss/assets/72158949/7e784c71-866d-4e7e-9d7b-01d71d098053">
- Must use GKE RabbitMQ Operator
    - Refer to above screenshot with RabbitMQ in the Workloads using the professor's markdown to deploy
- Extend the Starbucks API to support async order processing (to use RabbitMQ)

### Starbucks API for Mobile App and Store Front (25 points)
- Deployed with Kong API Gateway with API Key Authentication (10 points)
<img width="1440" alt="Screen Shot 2023-05-13 at 5 23 34 PM" src="https://github.com/nguyensjsu/cmpe172-blankss/assets/72158949/69807fd1-a54f-49c3-b033-5ad25558d0ff">

- Implement RabbitMQ Check Order Status for "Drinks Available" (15 Points)
<img width="1440" alt="Screen Shot 2023-05-13 at 5 41 36 PM" src="https://github.com/nguyensjsu/cmpe172-blankss/assets/72158949/9611ae80-d168-4505-ac89-12ec7d8660d0">

- Async Request API to "Make the Drink" once Order has been Paid (i.e. put request into a Queue)
<img width="1440" alt="Screen Shot 2023-05-13 at 5 42 06 PM" src="https://github.com/nguyensjsu/cmpe172-blankss/assets/72158949/7d64d532-15e7-454a-ac8d-0fb5b2a674e8">

- Async Check Order Status API to "Check Status of Drink" in the Starbucks Database
<img width="1440" alt="Screen Shot 2023-05-13 at 5 41 55 PM" src="https://github.com/nguyensjsu/cmpe172-blankss/assets/72158949/38daba92-a7b2-4ffa-a6c3-175e3eaadcc8">
<img width="1440" alt="Screen Shot 2023-05-13 at 5 42 21 PM" src="https://github.com/nguyensjsu/cmpe172-blankss/assets/72158949/e7fade98-4c1d-49b5-afe0-c40bfdfe6eee">

- Will need a Background Worker Job (i.e. Spring Scheduler) to pick up Orders and Make Drinks
<img width="1440" alt="Screen Shot 2023-05-13 at 5 56 14 PM" src="https://github.com/nguyensjsu/cmpe172-blankss/assets/72158949/28c2fc9b-0ed7-474f-972c-6314df5ae9fd">
    - Refer to previous screenshots to see Worker and RabbitMQ change the status of drink by picking up order

- Background Worker should be a "Single Resilient POD" which auto restarts on crashes
  

# CMPE 172 Weekly Journals
This section is the 10 "weekly" journals of the progress I had during every journal entry.

## First Journal Entry - 3/15
### Commit: 
https://github.com/nguyensjsu/cmpe172-blankss/commit/5ddcc1c2f35f7e290504aee578c26247b7ff4452
### Accomplished:
* Created spring-cashier folder with necessary dependencies from Spring
* Created controller for spring-cashier to handle the homepage for the Starbucks cashier app
* Configured starbucks.html folder with Thymeleaf syntax to be able to display the jpg image
  * Read up on articles about Thymleaf and learned that Spring Boot defaults the root to be src/main/resources/static
* Configured Docker and Makefile for spring-cashier
  * I am only testing it locally for now to ensure that it works, but will use Docker later once everything runs smoothly on my local machine

### Tasks to Accomplish:
* Edit controller to be able to handle the button presses
  * Currently, an error is thrown when trying to push any of the buttons as the controller code does not handle these events yet
* Add Spring Security for cashier authentication
* Simple dropdown menu for placing order

### Working homepage for cashier
<img width="1440" alt="Screen Shot 2023-03-15 at 3 34 28 PM" src="https://user-images.githubusercontent.com/72158949/225459150-e4b39d25-2d78-4ce7-aae6-bb4b3203c9ca.png">

### Error page when clicking on any button
<img width="1440" alt="Screen Shot 2023-03-15 at 3 35 17 PM" src="https://user-images.githubusercontent.com/72158949/225459166-b798e834-6691-42e2-99f5-61f722807115.png">

## Second Journal Entry - 3/20
### Commit: 
https://github.com/nguyensjsu/cmpe172-blankss/commit/a9fc0cdaad0ef30f0fcb16df3a2c0bc1179a4c2c
### Accomplished:
* Implemented basic security features as seen in security lab with Spring Security
* Implemented Sign Out button 
* Created MvcConfig file to help in setting the paths of the website
  * EX: "/" maps to "/home", "/starbucks" has a view name of "starbucks"
* Configured SpringCashierController to have @RequeestMapping("/starbucks") instead of "/" since we want the "/" to be the home page for the user to have login options

### Tasks to Accomplish:
* Still need to edit controller to handle button presses
* Dropdown menu for HTML place orders
* Design HTML Thymeleaf pages to look better, currently only using default Spring Security authentication pages and given starbucks.html

### Welcome Page for User
<img width="1440" alt="Screen Shot 2023-03-19 at 3 45 40 PM" src="https://user-images.githubusercontent.com/72158949/226441803-a6e21c31-0ec2-4491-b48c-ca0614f9ba25.png">

### Directs user to sign in using their cashier credentials
<img width="1440" alt="Screen Shot 2023-03-19 at 3 45 52 PM" src="https://user-images.githubusercontent.com/72158949/226441818-63a497cc-3a56-4c02-8552-f38595901e94.png">

### Cashier logged into their application, sign out button added
<img width="1440" alt="Screen Shot 2023-03-19 at 3 46 04 PM" src="https://user-images.githubusercontent.com/72158949/226441821-92e358c3-8199-487f-8ed6-e5527b6258b2.png">

### Clicked on sign out button, signs the cashier out
<img width="1440" alt="Screen Shot 2023-03-19 at 3 46 14 PM" src="https://user-images.githubusercontent.com/72158949/226441827-64eff7db-e864-4c42-aeb0-2a835d4a920d.png">

## Third Journal Entry - 4/2
### Commit: 
https://github.com/nguyensjsu/cmpe172-blankss/commit/0699698bed0d4764d6c8d0f714f6571c07df68d8
### Accomplished:
* Temporarily only have one button press to process the payment for the drink order
* Drink order persists in the order_model table in the cmpe172 table
* Attempted to also persist registration user data to mysql container
  * Encountering various errors, one of the recent one was that the password does not look like BCrypt, which prevents the user to log into the application

### Tasks to Accomplish:
* Design HTML Thymeleaf pages to look better, currently only using default Spring Security authentication pages and given starbucks.html
* Either read up on gumball v3.4 to apply the registration portion or to implement a more rudimentary portion first and then expanding from there

<img width="1440" alt="Screen Shot 2023-04-02 at 4 08 15 PM" src="https://user-images.githubusercontent.com/72158949/229384162-32cc236d-a19e-41fa-88ea-b5d1238e4402.png">
<img width="1440" alt="Screen Shot 2023-04-02 at 4 11 59 PM" src="https://user-images.githubusercontent.com/72158949/229384164-5e04940f-338e-4864-849c-b5025c0416a9.png">

## Fourth Journal Entry - 4/9
### Commit: 
https://github.com/nguyensjsu/cmpe172-blankss/commit/6244a0129c51acf03606af013d9dbac0455e7fb3
### Accomplished:
* Scale back and decided to implement the other functionalities of the buttons (Get, Place, and Clear) first
  * These features are fully functioning -> need to refactor for REST API capabilities since everything is in the PostAction right now
* Orders are being randomly generated currently
* Drink order locations now persist in the database as well, last time the store location was always null

### Tasks to Accomplish:
* Previous week's tasks
* Want to port over the drop-down menu drink options for customizability (right now orders are random)
* Attempt using REST API

## Place an order
<img width="1440" alt="Screen Shot 2023-04-09 at 6 05 51 PM" src="https://user-images.githubusercontent.com/72158949/230806264-07d88b9f-1141-41f2-920d-a724cea4718b.png">

## Attempting to place another order at the same register
<img width="1440" alt="Screen Shot 2023-04-09 at 6 05 43 PM" src="https://user-images.githubusercontent.com/72158949/230806259-ae40b1f0-7660-4c09-88d4-2e5a7112c8e3.png">

## Clear an order
<img width="1440" alt="Screen Shot 2023-04-09 at 6 05 59 PM" src="https://user-images.githubusercontent.com/72158949/230806265-1e93a2db-6bbd-4c31-b3ee-f66614a335fe.png">

## Placing another order at a different location
<img width="1440" alt="Screen Shot 2023-04-09 at 6 06 11 PM" src="https://user-images.githubusercontent.com/72158949/230806268-911c49a8-5b21-4824-a158-150d53aeebbe.png">

## Trying to Get or Clear a nonexistent order
<img width="1440" alt="Screen Shot 2023-04-09 at 6 06 19 PM" src="https://user-images.githubusercontent.com/72158949/230806269-19bf67d4-8c9d-4b61-aaf0-92293aca383d.png">

## MySQL Database Reflection
<img width="1440" alt="Screen Shot 2023-04-09 at 6 06 32 PM" src="https://user-images.githubusercontent.com/72158949/230806271-b3c8313f-e10c-4ce8-aa73-f22b1d633eb6.png">
<img width="1440" alt="Screen Shot 2023-04-09 at 6 06 59 PM" src="https://user-images.githubusercontent.com/72158949/230806274-83d5ba71-7eff-4bd3-ad07-50b5c0a691d4.png">

## Fifth Journal Entry - 4/16
### Commit: 
https://github.com/nguyensjsu/cmpe172-blankss/commit/9d97fa136c62267b9ca4a1cd8a042f308e10b86d
### Accomplished:
* Removed sticky sessions by adding Spring Session and Redis
* Implemented all 3 functionalities (Get, Place, and Clear Order)
  * Place Order now has working drop-down menus for drink, size, milk, and store
  * Get and Clear order will display different messages if there is no active order
* Edit Makefile for mysql and redis
* Added docker compose file

### Tasks to Accomplish:
* Add registration of new user
* Redesign UI for login
* Attempt client side portal

## Place an order at "The Dub"
<img width="1440" alt="Screen Shot 2023-04-16 at 3 50 45 PM" src="https://user-images.githubusercontent.com/72158949/232347904-a76c188e-c806-433e-a3cb-cd1aefb36ccd.png">

## Attempt to place another order with an active order
<img width="1440" alt="Screen Shot 2023-04-16 at 3 56 26 PM" src="https://user-images.githubusercontent.com/72158949/232347905-bc7130f3-6521-42e3-a2ef-03f6bb4c87d3.png">

## Get order from "The Dub"
<img width="1440" alt="Screen Shot 2023-04-16 at 3 56 41 PM" src="https://user-images.githubusercontent.com/72158949/232347906-7545c4e2-2fec-4997-9188-69c09938dc6b.png">

## Clear an order
<img width="1440" alt="Screen Shot 2023-04-16 at 3 56 58 PM" src="https://user-images.githubusercontent.com/72158949/232347908-20614f6a-0456-48f6-8c4d-2c18c1bde1d7.png">

## Attempt to clear non-active order
<img width="1440" alt="Screen Shot 2023-04-16 at 3 57 06 PM" src="https://user-images.githubusercontent.com/72158949/232347909-d8e032b1-8397-422d-847a-85bbf30700b3.png">

## Place another order at "Eso"
<img width="1440" alt="Screen Shot 2023-04-16 at 3 58 24 PM" src="https://user-images.githubusercontent.com/72158949/232347911-6d73ffaa-9308-4258-8f5e-d896b6de1f0b.png">

## Place another order at "Deadwood"
<img width="1440" alt="Screen Shot 2023-04-16 at 3 58 54 PM" src="https://user-images.githubusercontent.com/72158949/232347913-0b28f184-7073-46a8-b261-9307a266e140.png">

## MySQL Database contents
<img width="1440" alt="Screen Shot 2023-04-16 at 3 59 18 PM" src="https://user-images.githubusercontent.com/72158949/232347914-3d5095a8-450c-40b4-b9e8-928c63d81c2b.png">

## Load Balancer displaying two backend servers with no sticky sessions
<img width="1440" alt="Screen Shot 2023-04-16 at 3 59 44 PM" src="https://user-images.githubusercontent.com/72158949/232347915-e2b66f29-d347-428e-b817-2f4370a696fd.png">

## Sixth Journal Entry - 4/17
### Commit: 
https://github.com/nguyensjsu/cmpe172-blankss/commit/1cc063faa3c608200d93f10911e6310dedd00a96
### Accomplished:
* Added registration html page
* Basic authorization files added
* Registration functionality added
  * Password is hashed with bcrypt
  * User data persists in MySQL database

### Tasks to Accomplish:
* Error when after registration, cannot login
  * Routes are unclear right now, correct credentials do not log the user in and instead redirects to some other URL
* Inconsistent routing
  * Routing after registration should route to the pretty html page, not the page after registration screenshot

## New Home Page
<img width="1440" alt="Screen Shot 2023-04-17 at 4 39 28 PM" src="https://user-images.githubusercontent.com/72158949/232634926-a454270b-9aa9-433a-82f9-1a3f095ec2b0.png">

## Registration Page
<img width="1440" alt="Screen Shot 2023-04-17 at 4 39 39 PM" src="https://user-images.githubusercontent.com/72158949/232634970-05b064bf-236e-4f02-abe6-9402c35fd7e7.png">

## Page after registration
<img width="1440" alt="Screen Shot 2023-04-17 at 4 40 00 PM" src="https://user-images.githubusercontent.com/72158949/232635001-6338f4db-13cc-4919-9550-6ca57d894b5c.png">

## MySQL Database after registration
<img width="1440" alt="Screen Shot 2023-04-17 at 4 40 18 PM" src="https://user-images.githubusercontent.com/72158949/232635028-f00ad8f2-da40-4926-b6c8-c6cda975e7ff.png">

## Page after sign in
<img width="1440" alt="Screen Shot 2023-04-17 at 4 40 38 PM" src="https://user-images.githubusercontent.com/72158949/232635057-6ec12faf-87ae-4ec5-91f7-bf14e23e2d32.png">

## Seventh Journal Entry - 4/19
### Commit: 
https://github.com/nguyensjsu/cmpe172-blankss/commit/a5e849861a69a6c1c78d4d95104094647fa1e8ce
### Accomplished:
* Completed registration of a new user for starbucks cashier
  * Password is hashed in database
  * Inputted password is hashed and compared to the one in the database
* Cashier persists in the MySQL database with information saved with the input
* Used Spring gumball v3.4 login.css to make the login and register page look better
  * Edited css file to fit registration credentials needed 
* Fixed routing issue

### Tasks to Accomplish:
* Experiment with REST API
* Perhaps use own HTML/CSS to customize to fit own
* Implement the next lab's (lab 7) payment portion into application
* Look into starting storefront portion

## Default home page
<img width="1440" alt="Screen Shot 2023-04-19 at 3 03 03 PM" src="https://user-images.githubusercontent.com/72158949/233210949-d906cb7d-d4c7-43e4-ba79-c896509b9877.png">

## Designed login/register page taken from gumball v3.4
<img width="1440" alt="Screen Shot 2023-04-19 at 3 03 17 PM" src="https://user-images.githubusercontent.com/72158949/233210978-d0e6d9c8-1494-4c2a-aa4d-1d48a27ccf75.png">

## Sign up with credentials
<img width="1440" alt="Screen Shot 2023-04-19 at 3 03 41 PM" src="https://user-images.githubusercontent.com/72158949/233211000-a44fac64-3a22-4f9b-8bb5-be1bdb6643e7.png">

## Redirect to login portion
<img width="1440" alt="Screen Shot 2023-04-19 at 3 05 53 PM" src="https://user-images.githubusercontent.com/72158949/233211019-87c3300e-db0a-4881-861e-d2252c526817.png">

## Log in with created credentials
<img width="1440" alt="Screen Shot 2023-04-19 at 3 06 18 PM" src="https://user-images.githubusercontent.com/72158949/233211046-9ef15e5f-d559-4096-bd14-cdb4ec71f9ec.png">

## MySQL database of users
<img width="1440" alt="Screen Shot 2023-04-19 at 3 06 40 PM" src="https://user-images.githubusercontent.com/72158949/233211070-3673bc15-7c04-4dcc-b58d-59128c557ab7.png">

## Logged in page at starbucks
<img width="1440" alt="Screen Shot 2023-04-19 at 3 06 50 PM" src="https://user-images.githubusercontent.com/72158949/233211092-138d99e7-68a1-41f1-9b0e-406ef21f75a1.png">

## Placed an order
<img width="1440" alt="Screen Shot 2023-04-19 at 3 07 31 PM" src="https://user-images.githubusercontent.com/72158949/233211108-788aad96-91d9-4956-a33c-fcab0a89d17a.png">

## MySQL order_model
<img width="1440" alt="Screen Shot 2023-04-19 at 3 07 23 PM" src="https://user-images.githubusercontent.com/72158949/233211125-2f525a71-1b11-450a-ae01-879b03a1c4aa.png">

## Eighth Journal Entry - 5/1
### Commit: 
https://github.com/nguyensjsu/cmpe172-blankss/commit/ad64bd7fad02529b75149cc493745d7b57df9573
### Accomplished:
* Used starbucks-api and kong gateway for the application instead of previous way
* Implemented Get Order, Place Order, and Clear Order functionalities with kong and starbucks-api
  * The format of the message from Get Order and Place Order are returned from the API call, which is unformatted currently
* Removed MySQL from directly saving the order, API call now does that

### Tasks to Accomplish:
* Fix/change the error reporting from throwing HTTP error to redirect or new message
  * Currently, getting a nonexistent order, placing another order, or clearing a nonexistent order crashes the application due to throwing the HTTP error
  * Thinking of either redirect or just returning the message that I had from previous iterations
* Add logout functionality
* Transition to compose stack, right now everything is separated since it does not work if in the stack
  * After fixing, can transition to GKE

## Docker Containers Running
<img width="1440" alt="Screen Shot 2023-05-01 at 2 42 59 PM" src="https://user-images.githubusercontent.com/72158949/235539206-0bedc563-05d8-4cc3-b47d-3bc3efbc373e.png">

## Place Order with API Call
<img width="1440" alt="Screen Shot 2023-05-01 at 2 43 42 PM" src="https://user-images.githubusercontent.com/72158949/235539217-f93f1dd9-2576-4083-a0d5-c87a830460f1.png">

## Get Order with API Call
<img width="1440" alt="Screen Shot 2023-05-01 at 2 43 50 PM" src="https://user-images.githubusercontent.com/72158949/235539223-7ca3ddd0-4344-43fc-8909-ef0abee40a94.png">

## Clear Order with API Call
<img width="1440" alt="Screen Shot 2023-05-01 at 2 44 02 PM" src="https://user-images.githubusercontent.com/72158949/235539226-12b02e43-3c6b-478e-849f-5ee86c830a80.png">

## Get Nonexistent Order with API Call
<img width="1440" alt="Screen Shot 2023-05-01 at 2 44 11 PM" src="https://user-images.githubusercontent.com/72158949/235539233-9455a9ef-3cdd-41ae-ab14-b7f9d1b8cb8b.png">

## Ninth Journal Entry - 5/6
### Commit: 
https://github.com/nguyensjsu/cmpe172-blankss/commit/34bd805eb3af20f4b8f5882a64b9d225ea6456d4
### Accomplished:
* Load balanced both spring cashier and starbucks api
* Removed redis and used jdbc since we would need to do this on GKE to circumvent problems according to the professor
* Configured StarbucksOrderRepository to have a method of findStarbucksOrderByRegister to find active orders in a register
* Edited StarbucksOrderController and StarbucksService to use the method in the previous bulletpoint to find the active order in the database instead of the HashMap
* Used try-catch block in SpringCashierController to catch HTTP errors thrown by service and order controller to not cause a Whitelabel Error and instead display a message
* Added logout link in starbucks

### Tasks to Accomplish:
* Logout currently displays 404, need to look into why Spring Security is not generating the logout page
* Implement RabbitMQ for async 
* Eventually deploy to GKE

## Command Line and Starbucks Mobile App Running
<img width="1435" alt="Screen Shot 2023-05-06 at 4 13 37 PM" src="https://user-images.githubusercontent.com/72158949/236650197-308d60e2-3fe5-47c3-81eb-ed279f19ea0e.png">

## Docker Desktop with Compose Stack
<img width="1435" alt="Screen Shot 2023-05-06 at 4 14 58 PM" src="https://user-images.githubusercontent.com/72158949/236650198-2c1dc730-50d6-4296-bd07-3585b11161c0.png">

## Logged in and Placed Order through Kong API 
<img width="1435" alt="Screen Shot 2023-05-06 at 4 15 36 PM" src="https://user-images.githubusercontent.com/72158949/236650199-591cd4ee-70b6-4f80-b25b-4eb236289994.png">

## Getting Order through Kong API
<img width="1435" alt="Screen Shot 2023-05-06 at 4 15 53 PM" src="https://user-images.githubusercontent.com/72158949/236650200-67e70a6b-36b5-48db-b5ff-c2f4ba993b14.png">

## Paid for Order with Card
<img width="1435" alt="Screen Shot 2023-05-06 at 4 16 54 PM" src="https://user-images.githubusercontent.com/72158949/236650202-80363c1e-3fc2-45e4-811f-374f65634991.png">

## Clear Order
<img width="1435" alt="Screen Shot 2023-05-06 at 4 17 44 PM" src="https://user-images.githubusercontent.com/72158949/236650203-722a0775-f966-446c-8a63-e347c2c98239.png">

## Get Order with no Active Order
<img width="1435" alt="Screen Shot 2023-05-06 at 4 17 52 PM" src="https://user-images.githubusercontent.com/72158949/236650205-37d42b1b-2473-4367-af4f-72459ffff12e.png">

## MySQL Database after Clearing
<img width="1435" alt="Screen Shot 2023-05-06 at 4 18 01 PM" src="https://user-images.githubusercontent.com/72158949/236650206-9f9d5a84-4f0b-4322-a775-fd02858525db.png">

## Tenth Journal Entry - 5/10
### Commit:
https://github.com/nguyensjsu/cmpe172-blankss/commit/b1775a58d5152a728574ddf729051eab8d5f0731
### Accomplished:
* Fixed logout routing
* Implemented RabbitMQ
  * Two workers so two consumers
  * Thread sleep 60000 simulates busy work to delay the status from being changed to "Currently making drink..." to "FULFILLED" so that we can see the change in the database 
* Configured most of the yaml files for deployment on GKE
  * Based yaml files off of professor's yaml files in lab8 and spring-gumball v3.5
* Added StarbucksDrink model
  * Fulfills requirement of querying status of the drink (not order) after being paid 
  * Added necessary MySQL components to drink to make it work in the database (repository, controller, etc.)
* Added StarbucksWorker to process the order
  * Made RabbitMQ queue named "starbucks"
### Remaining Tasks Before Demo:
* Deploy on GKE
* Change HTML title page to reflect starbucks and not gumball

## Starbucks Cashier Page has logout functionality
<img width="1440" alt="Screen Shot 2023-05-10 at 12 12 08 PM" src="https://github.com/nguyensjsu/cmpe172-blankss/assets/72158949/c027a83a-ddcd-48eb-9f59-13fb4f1336f2">
<img width="1440" alt="Screen Shot 2023-05-10 at 12 12 19 PM" src="https://github.com/nguyensjsu/cmpe172-blankss/assets/72158949/475d6b3a-8c67-4ccf-bfcb-0c1ee6f20f10">

## Making New Cards into the database
<img width="1440" alt="Screen Shot 2023-05-10 at 12 13 50 PM" src="https://github.com/nguyensjsu/cmpe172-blankss/assets/72158949/a7f72f65-30b2-4134-a5ae-c4c1efba40e6">

## Paid with card deactivates order and transitions it to the drink table for processing from worker
<img width="1440" alt="Screen Shot 2023-05-10 at 12 16 05 PM" src="https://github.com/nguyensjsu/cmpe172-blankss/assets/72158949/fe4dae74-37be-4d95-9b01-d19392fe876a">

## Waiting for worker to fulfill drink
<img width="1440" alt="Screen Shot 2023-05-10 at 12 16 30 PM" src="https://github.com/nguyensjsu/cmpe172-blankss/assets/72158949/7fd3d5cf-a857-4a71-95ea-82cf27db8ad3">

## RabbitMQ Console of the queue
<img width="1440" alt="Screen Shot 2023-05-10 at 12 16 36 PM" src="https://github.com/nguyensjsu/cmpe172-blankss/assets/72158949/2df4cf45-534e-4a86-9083-66447607c714">

## Extra screenshot for status of drinks
<img width="1440" alt="Screen Shot 2023-05-10 at 12 16 53 PM" src="https://github.com/nguyensjsu/cmpe172-blankss/assets/72158949/c45071de-5441-43ce-a64e-4ee8fae284e6">



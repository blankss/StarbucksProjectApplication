# CMPE 172 Project Journal

## First Journal Entry - 3/15
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
### Accomplished:

### Tasks to Accomplish:

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



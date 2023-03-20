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

### Directs useer to sign in using their cashier credentials
<img width="1440" alt="Screen Shot 2023-03-19 at 3 45 52 PM" src="https://user-images.githubusercontent.com/72158949/226441818-63a497cc-3a56-4c02-8552-f38595901e94.png">

### Cashier logged into their application, sign out button added
<img width="1440" alt="Screen Shot 2023-03-19 at 3 46 04 PM" src="https://user-images.githubusercontent.com/72158949/226441821-92e358c3-8199-487f-8ed6-e5527b6258b2.png">

### Clicked on sign out button, signs the cashier out
<img width="1440" alt="Screen Shot 2023-03-19 at 3 46 14 PM" src="https://user-images.githubusercontent.com/72158949/226441827-64eff7db-e864-4c42-aeb0-2a835d4a920d.png">

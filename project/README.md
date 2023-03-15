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

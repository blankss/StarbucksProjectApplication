# CMPE 172 - Lab #6 Notes

## Part 1 - Spring JDBC and Spring MySQL
### Spring JDBC
### Discussion: 
* For spring-jdbc, we have defined a run function in the ```SpringJdbcApplication.java``` file that executes SQL commands with the ```jdbcTemplace.execute(...)``` statements
* With the statements, we hardcoded these statements to insert customer entries and then querying the information successfully
* JdbcTemplate allows us to execute SQL commands in our code with various functions
<img width="1440" alt="Screen Shot 2023-03-20 at 2 31 33 PM" src="https://user-images.githubusercontent.com/72158949/226471290-03245cb9-1f3a-4a03-9981-51338e3ee8dc.png">

### Spring MySQL
### Notes:
* Decided to use Postman to test the two HTTP endpoints of the application 
* Postman is easy to use and can be used to generate the same/similar curl command or other language as well
### Discussion:
* In order to use MySQL with our application, we need to create and configure our ```application.properties``` file to allow for MySQL and JPA
* We use an ```@Entity``` tag in our model object (User in this case) to make Hibernate generate a table of this class
* Then, we create the ```UserRepository.java``` file that will be auto implemented by Spring that facilitates CRUD operations
* In our ```MainController.java``` file, we need to define some paths that we can access as HTTP endpoints
  * We define a ```@GetMapping``` and ```@PostMapping``` that defines our two HTTP endpoints
  * ```@GetMapping(path="/all")```: this endpoint gets al the users that are in our database as defined by the ```userRepository.findAll()``` statement
  * ```@PostMapping(path="/add")```: this endpoint adds a user to our database as defined by the ```userRepository.save(n)``` where n is the new user

### Inputting MySQL Commands for the database
<img width="1440" alt="Screen Shot 2023-03-27 at 12 18 52 PM" src="https://user-images.githubusercontent.com/72158949/228045650-a9fca8b6-3749-4587-a0da-6caae59b83c3.png">

### Using a POST request to put new information into the database via Postman
<img width="1440" alt="Screen Shot 2023-03-27 at 12 12 23 PM" src="https://user-images.githubusercontent.com/72158949/228045623-ffbb7063-618a-4465-8e8d-1fd099e7627f.png">

### Using a GET request to get all the users via Postman
<img width="1440" alt="Screen Shot 2023-03-27 at 12 13 22 PM" src="https://user-images.githubusercontent.com/72158949/228045643-17beeb5f-fc9c-4842-95ef-a33cb963335d.png">

## Part 2 - Deployment of Spring Gumball Version 3 to Local Docker
<img width="1440" alt="Screen Shot 2023-03-29 at 11 47 22 AM" src="https://user-images.githubusercontent.com/72158949/228640060-0b8c69e2-bb4f-4d29-958f-e90db3154407.png">


## Part 3 - Running Spring Gumball Version 3 showing Web Page (with Hash) and HTML Source of the Page showing hidden fields
### Notes:
* This section also includes screenshots of JPA/DB changes in Spring Gumball Version 3

### HTML Page with Hash, Serial Number, Server/Host IP, and Model Number
<img width="1440" alt="Screen Shot 2023-03-29 at 11 47 48 AM" src="https://user-images.githubusercontent.com/72158949/228640224-89036b4e-0c7e-4c1b-b284-ef86ae8e96fe.png">
<img width="1440" alt="Screen Shot 2023-03-29 at 11 47 57 AM" src="https://user-images.githubusercontent.com/72158949/228640236-1fb61ecf-190a-4358-aaac-331b086d7bd9.png">

### HTML Source Page with Hidden Fields of state, timestamp, and hash
<img width="1440" alt="Screen Shot 2023-03-29 at 11 48 28 AM" src="https://user-images.githubusercontent.com/72158949/228640244-521560c3-cb75-4a37-883b-77b9f54ad0cf.png">

### Discussion:
* In order for us to automatically convert an object into a table, we can use the ```@Entity``` tag to our model file to ensure that it automatically translates this entity into a table in the database named gumball_model.
  * We can further customize this by using the ```@Table``` tag to define the columns of our table in the database
* To use MySQL, we need to create and configure the ```applications.properties``` file with the necessary configurations 
* We need to modify our ```GumballMachineController.java``` to actually update the gumball inventory in our database by adding:
  ```
  found.setCountGumballs(Integer.valueOf(count.intValue() - 1)) ;
  mysql.save(found) ;
  ```
  which saves our decrement to the mysql database.
* As discussed in class, this application right now does not work as we have CSRF enabled, which interferes with our POST requests. This can easily be addressed by simply disabling CSRF in our ```WebSecurityConfig.java``` file.
* New files added such as ```GumballModelRepository.java``` and ```GumballQueryRepository.java``` assist in database CRUD operations 
* As seen below, our database and application works as once we insert a quarter and turn the crank, we get a decrement of one gumball
  * In this case, since we used docker-compose to have 2 instances of gumball running, we can purchase and decrement the gumball inventory in 2 different instances
* When we use docker-compose up to scale it to 2 instances, we encounter a login error that does not log the user in even with the correct credentials
  * This is solved by enabling sticky sessions
  * I had to set sticky sessions to be true since I used gumball-v3.1, which had security enabled and it did not allow the user to log in until it is true

### MySQL Screenshot Proof of Deployment and decrease of gumball inventory when purchased
<img width="1440" alt="Screen Shot 2023-04-06 at 8 18 47 PM" src="https://user-images.githubusercontent.com/72158949/230534231-e04628df-0d32-4f80-b039-4a9e1bdc5bf3.png">
<img width="1440" alt="Screen Shot 2023-04-06 at 8 18 03 PM" src="https://user-images.githubusercontent.com/72158949/230534162-8a44f8a1-63a8-4152-a351-a0bd87732866.png">
<img width="1440" alt="Screen Shot 2023-04-06 at 8 17 36 PM" src="https://user-images.githubusercontent.com/72158949/230534117-7f7dd1b4-d7bd-480f-80f1-14e59abd1e71.png">




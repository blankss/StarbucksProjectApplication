# CMPE 172 - Lab #3 Notes

## Screenshots of deployment to Docker via Docker Compose

Since we have specified for 2 instances of our gumball application in the docker-compose up command, we can see that there are two IP addresses and 2 seperate Server Host names when we open the application twice. <br />

### Gumball-1
<img width="1440" alt="Screen Shot 2023-02-24 at 11 13 44 AM" src="https://user-images.githubusercontent.com/72158949/221272432-c6eb0553-b4ad-4169-bb39-9e0ecbd3f88f.png"> <br />
<img width="1440" alt="Screen Shot 2023-02-24 at 11 13 55 AM" src="https://user-images.githubusercontent.com/72158949/221272442-782d8ae6-1956-40f4-8edc-80e998a86979.png">


### Gumball-2
<img width="1440" alt="Screen Shot 2023-02-24 at 11 12 41 AM" src="https://user-images.githubusercontent.com/72158949/221272402-a1a4e3e8-b8d7-41e6-b171-60b196b95bb4.png"> <br />

<img width="1440" alt="Screen Shot 2023-02-24 at 11 13 02 AM" src="https://user-images.githubusercontent.com/72158949/221272410-dc78f9d4-d794-4d49-9315-607303f5567c.png">

## Screenshots of Gumball App Running with your observations and answers to the following questions (on HA Proxy Load Balancer)

### When you load the page multiple times what do you see on the Gumball Home Pages for Server Host/IP?
When we load the page multiple times, we can see that each page we load up is the other Server Host/IP, which most likely means that the load balancer is using a Round Robin algorithm to distribute incoming requests. Since we have two instances, the alternation would go from the Server Host/IP: 3e9630783bd6/172.18.0.3 to the Server Host/IP: aeb1f8532524/172.18.0.2. As demonstrated in the screenshots, each time we bring up localhost:80, we hit the next instance on docker. I have loaded the application 6 times and each time I see that the Server Host/IP alternates between 2 possibilities. Of course, these pairings would change each time we docker compose down and then docker compose up.

<img width="1440" alt="Screen Shot 2023-02-24 at 11 32 03 AM" src="https://user-images.githubusercontent.com/72158949/221274207-d83c230f-6de5-4efb-9b81-9c65746e389d.png">
<img width="1440" alt="Screen Shot 2023-02-24 at 11 32 10 AM" src="https://user-images.githubusercontent.com/72158949/221274214-680fdf57-d2bd-4a20-97fb-67d6d7d1ca97.png">

### Can you explain where that IP value comes from?
Using the screenshots from the previous question, we can see that the IP value comes from how many instances of our application we are running. Since we are running two instances on docker, we know that there will be 2 unique Server Host/IP values. In this case, we have gumball-1 having the Server Host/IP value of: 3e9630783bd6/172.18.0.3 and gumball-2 having the Server Host/IP value of: aeb1f8532524/172.18.0.2. If we take a look at the gumball.html page, we see the line:
```
<pre>Server Host/IP:  <span th:text="${server}" /></pre>
```
which means that the values are coming from the server. Following this, we can take a look at _GumballMachineController.java_:
```
try { 
            InetAddress ip = InetAddress.getLocalHost() ;
            server_ip = ip.getHostAddress() ;
            host_name = ip.getHostName() ;
  
        } catch (Exception e) { }
model.addAttribute( "server",  host_name + "/" + server_ip ) ;
```
which makes it seem that the IP values originate from the local host. All in all, the IP values come from each separate docker container, in this case, we have 2 so we have 2 unique values. The screenshots for the IP addresses can be referred to above. <br />

### Now, try to add some quarters and purchase some gumballs. Do you see the inventory depleting?  Are there any errors?
If we, at this point, try to add some quarters and/or purchase some gumballs, we will get a **Whitelabel Error Page**, which makes it so we cannot even see the inventory anymore. When we take a look at the load balancer page, we can see what happens once we generate the error by pressing insert quarter or presesing turn crank. In the screenshots of the HA Proxy Load Balancer, we see that the HTTP 5XX response goes from 15 (before generating the error) to 16 (after generating the error). This is because we generated an unexpected error of status 500 when we try to add some quarters and/or purhcase some gumballs. Additionally, we can observe a _java.lang.NullPointerException_ in our docker dashboard on line number 69 of our program _GumballMachineController.java_.

<img width="1440" alt="Screen Shot 2023-02-24 at 12 15 43 PM" src="https://user-images.githubusercontent.com/72158949/221287396-e5feb4a1-f884-4e85-b36b-765bcc426ade.png">
<img width="1440" alt="Screen Shot 2023-02-24 at 12 16 18 PM" src="https://user-images.githubusercontent.com/72158949/221287401-c1bcacc0-98b3-444d-8943-61b5114ad1b2.png">
<img width="1440" alt="Screen Shot 2023-02-24 at 12 40 58 PM" src="https://user-images.githubusercontent.com/72158949/221287407-0eae3fe5-35ee-4b14-bdd9-2f9155189799.png">

### Is there a setting that can avoid the error?  Why does it work (or Why not).
When we take a look at the _docker-compose.yaml_ file and look at the COOKIES_ENABLED option, we see that it is set to false. However, in the code that we were given, the code is written to be used in tandem with sessions. Since sticky sessions are made possible with the help of cookies, setting the cookies option to be false would logically generate errors in the code as the code expects the application to have cookies enabled. To fix this issue, we can simply set COOKIES_ENABLED to be true by changing the .yaml file to:
```
COOKIES_ENABLED: "true"
```
With this, the code in the _postAction_ method should work as we have a session now. Before, inserting a quarter or turning the crank would not work as the line:
```
HttpSession session = request.getSession();
GumballMachine gm = (GumballMachine) session.getAttribute("gumball");
```
would not make sense as we do not have sticky sesssions. If we do not have a session, then it would mean that the GumballMachine object would not be in the session table; hence, the object gm would be null and causes our application to crash if we try to use the object in the method calls of _insertQuarter()_ and _turnCrank()_. Once we have cookies enabled, we will be routed to the correct backend that stores our data.

<img width="1440" alt="Screen Shot 2023-02-24 at 1 58 52 PM" src="https://user-images.githubusercontent.com/72158949/221301475-e2e56f44-8a1c-43ef-a825-d252a0cd2fdb.png">
<img width="1440" alt="Screen Shot 2023-02-24 at 1 59 01 PM" src="https://user-images.githubusercontent.com/72158949/221301479-79d0b4b4-f9ea-44dc-8ff0-957a082501da.png">
<img width="1440" alt="Screen Shot 2023-02-24 at 1 59 27 PM" src="https://user-images.githubusercontent.com/72158949/221301482-d26b60eb-464a-4bf2-b5fe-9a1ec8165112.png">
<img width="1440" alt="Screen Shot 2023-02-24 at 1 59 52 PM" src="https://user-images.githubusercontent.com/72158949/221301488-a24f0ae1-1a7b-49eb-96f6-6fb5a80af23f.png">

Once we have set COOKIES_ENABLED to true, we can see that even though we opened another tab and accessed localhost:80, the load balancer knows to send us back to the instance that we have accessed last time. The application also works as expected now with the inventory decreasing from inserting a quarter and turning the crank.

## Screenshots for some Testing via Jumpbox

### Individual Containers 
This only works if the individual containers are actually up and running as we are using the terminal inside of the container. A better way to test whether a container is running would be to use a jump box.
<img width="1440" alt="Screen Shot 2023-02-24 at 4 56 15 PM" src="https://user-images.githubusercontent.com/72158949/221327454-e3027ed0-a956-4d43-bef8-f3f913f1cbcb.png">
<img width="1440" alt="Screen Shot 2023-02-24 at 4 56 42 PM" src="https://user-images.githubusercontent.com/72158949/221327457-a4ea1f0d-1950-4148-b087-d37aa2e9d222.png">

### Jumpbox
With a jumpbox, we have a separate container that we can access and use its terminal to ping other containers to see if they are running or not. When we curl/ping gumball-1 and gumball-2, the HTML content is returned, which means that the container is up and healthy.
<img width="1440" alt="Screen Shot 2023-02-24 at 5 01 09 PM" src="https://user-images.githubusercontent.com/72158949/221327461-ad6b2d6f-0b7a-43f4-8de1-ec1adf211208.png">
<img width="1440" alt="Screen Shot 2023-02-24 at 5 01 24 PM" src="https://user-images.githubusercontent.com/72158949/221327464-83041f50-26f6-4411-8e8c-84fece868055.png">

### Localhost
Outside of docker containers, we can also curl/ping from the outside environment such as our localhost or local machine. This requires us to do:
```
curl localhost:80
```
instead of port 8080 since port 80 is the only open port we can use to access the web application outside of docker that is managed by the load balancer. However, this may not be the best way for testing as the load balancer will forward us to a working container, which does not allow us to know if a specific container is working or not.
<img width="1440" alt="Screen Shot 2023-02-24 at 5 03 06 PM" src="https://user-images.githubusercontent.com/72158949/221327465-95db21df-0ccd-4ec0-8a10-0232472c2332.png">
<img width="1440" alt="Screen Shot 2023-02-24 at 5 03 15 PM" src="https://user-images.githubusercontent.com/72158949/221327466-57bc8730-7c30-437b-ad9b-9c9da8cba515.png">



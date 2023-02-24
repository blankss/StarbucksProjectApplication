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
When we load the page multiple times, we can see that each page we load up is the other Server Host/IP, which most likely means that the load balancer is using a Round Robin algorithm to distribute incoming requests. Since we have two instances, the alternation would go from the Server Host/IP: HERE to the Server Host/IP: HERE. As demonstrated in the screenshots, each time we bring up localhost:80, we hit the next instance on docker.

<img width="1440" alt="Screen Shot 2023-02-24 at 11 32 03 AM" src="https://user-images.githubusercontent.com/72158949/221274207-d83c230f-6de5-4efb-9b81-9c65746e389d.png">
<img width="1440" alt="Screen Shot 2023-02-24 at 11 32 10 AM" src="https://user-images.githubusercontent.com/72158949/221274214-680fdf57-d2bd-4a20-97fb-67d6d7d1ca97.png">
<img width="1440" alt="Screen Shot 2023-02-24 at 11 32 16 AM" src="https://user-images.githubusercontent.com/72158949/221274220-8f7d0a88-c997-4dab-a66b-52231f65948c.png">
<img width="1440" alt="Screen Shot 2023-02-24 at 11 32 23 AM" src="https://user-images.githubusercontent.com/72158949/221274225-a1817789-7213-474b-be86-109dc8e77cbc.png">
<img width="1440" alt="Screen Shot 2023-02-24 at 11 32 29 AM" src="https://user-images.githubusercontent.com/72158949/221274231-57a0c114-2b64-4c53-ac8c-4e146485e74a.png">
<img width="1440" alt="Screen Shot 2023-02-24 at 11 32 37 AM" src="https://user-images.githubusercontent.com/72158949/221274235-f2b205b3-d812-4a92-85c7-431afe94db20.png">

### Can you explain where that IP value comes from?
Using the screenshots from the previous question, we can see that the IP value comes from how many instances of our application we are running. Since we are running two instances on docker, we know that there will be 2 unique Server Host/IP values. In this case, we have gumball-1 having the Server Host/IP value of: HERE and gumball-2 having the Server Host/IP value of: HERE. If we take a look at the gumball.html page, we see the line:
```
HERE
```
which means that the values are coming from the server. Following this, we can take a look at _GumballMachineController.java_:
```
HERE
```
which makes it seem that the IP values originate from our local host. <br />
<img width="1440" alt="Screen Shot 2023-02-24 at 11 32 03 AM" src="https://user-images.githubusercontent.com/72158949/221274207-d83c230f-6de5-4efb-9b81-9c65746e389d.png">
<img width="1440" alt="Screen Shot 2023-02-24 at 11 32 10 AM" src="https://user-images.githubusercontent.com/72158949/221274214-680fdf57-d2bd-4a20-97fb-67d6d7d1ca97.png">

### Now, try to add some quarters and purchase some gumballs. Do you see the inventory depleting?  Are there any errors?
If we, at this point, try to add some quarters and/or purchase some gumballs, we will get a **Whitelabel Error Page**, which makes it so we cannot even see the inventory anymore. When we take a look at the load balancer page, we can see what happens once we generate the error by pressing insert quarter or presesing turn crank. In the screenshots of the HA Proxy Load Balancer, we see that the HTTP 5XX response goes from 15 (before generating the error) to 16 (after generating the error). This is because we generated an unexpected error of status 500 when we try to add some quarters and/or purhcase some gumballs.

### Is there a setting that can avoid the error?  Why does it work (or Why not).
When we take a look at the _docker-compose.yaml_ file and looking at the COOKIES_ENABLED option, we see that it is set to false. However, in the code that we were given, the code is written to be used in tandem with sessions. Since sticky sessions are made possible with the help of cookies, setting the cookies option to be false would logically generate errors in the code as the code expects the application to have cookies enabled. To fix this issue, we can simply set COOKIES_ENABLED to be true by changing the .yaml file to:
```
COOKIES_ENABLED: "true"
```
With this, the code in @PostMapping should work as we have a session now. Before, inserting a quarter or turning the crank would not work as the line:
```
HttpSession session = request.getSession();
GumballMachine gm = (GumballMachine) session.getAttribute("gumball");
```
would not make sense as we do not have sticky sesssions. Once we have cookies enabled, we will be routed to the correct backend that stores our data.

## Screenshots for some Testing via Jumpbox

#### Ping the endpoints (curl get) from LB and also for each node behind LB on localhost.

#### For Docker:
- curl localhost (local machine/laptop) to Docker's LB port (port 80)
- on each container (bash shell), curl localhost on port 8080

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

#### When you load the page multiple times what do you see on the Gumball Home Pages for Server Host/IP?
<img width="1440" alt="Screen Shot 2023-02-24 at 11 32 03 AM" src="https://user-images.githubusercontent.com/72158949/221274207-d83c230f-6de5-4efb-9b81-9c65746e389d.png">
<img width="1440" alt="Screen Shot 2023-02-24 at 11 32 10 AM" src="https://user-images.githubusercontent.com/72158949/221274214-680fdf57-d2bd-4a20-97fb-67d6d7d1ca97.png">
<img width="1440" alt="Screen Shot 2023-02-24 at 11 32 16 AM" src="https://user-images.githubusercontent.com/72158949/221274220-8f7d0a88-c997-4dab-a66b-52231f65948c.png">
<img width="1440" alt="Screen Shot 2023-02-24 at 11 32 23 AM" src="https://user-images.githubusercontent.com/72158949/221274225-a1817789-7213-474b-be86-109dc8e77cbc.png">
<img width="1440" alt="Screen Shot 2023-02-24 at 11 32 29 AM" src="https://user-images.githubusercontent.com/72158949/221274231-57a0c114-2b64-4c53-ac8c-4e146485e74a.png">
<img width="1440" alt="Screen Shot 2023-02-24 at 11 32 37 AM" src="https://user-images.githubusercontent.com/72158949/221274235-f2b205b3-d812-4a92-85c7-431afe94db20.png">


#### Can you explain where that IP value comes from?

#### Now, try to add some quarters and purchase some gumballs. Do you see the inventory depleting?  Are there any errors?

#### Is there a setting that can avoid the error?  Why does it work (or Why not).

## Screenshots for some Testing via Jumpbox

#### Ping the endpoints (curl get) from LB and also for each node behind LB on localhost.

#### For Docker:
- curl localhost (local machine/laptop) to Docker's LB port (port 80)
- on each container (bash shell), curl localhost on port 8080

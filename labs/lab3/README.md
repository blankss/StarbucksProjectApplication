# CMPE 172 - Lab #3 Notes

## Screenshots of deployment to Docker via Docker Compose

Since we have specified for 2 instances of our gumball application in the docker-compose up command, we can see that there are two IP addresses and 2 seperate Server Host names when we open the application twice. <br />

#### Gumball-1

#### Gumball-2

## Screenshots of Gumball App Running with your observations and answers to the following questions (on HA Proxy Load Balancer)

#### When you load the page multiple times what do you see on the Gumball Home Pages for Server Host/IP?

#### Can you explain where that IP value comes from?

#### Now, try to add some quarters and purchase some gumballs. Do you see the inventory depleting?  Are there any errors?

#### Is there a setting that can avoid the error?  Why does it work (or Why not).

## Screenshots for some Testing via Jumpbox

#### Ping the endpoints (curl get) from LB and also for each node behind LB on localhost.

#### For Docker:
- curl localhost (local machine/laptop) to Docker's LB port (port 80)
- on each container (bash shell), curl localhost on port 8080

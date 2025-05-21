# Irrigation System
This system enables the sensor to pick the plot of land which is due for irrigation based on their pre-configured time slot (MORNING, NOON, EVENING).

The below seed data must be prepared either by provided db script (seed.sql) or via calling the seedData postman Collection. 
Import in postman and run via "postman runner" all curls.

![seedData.png](seedData.png)

# Getting Started
Clone the project on local. 
Open in IntelliJ

Note - enable annotation processing for seamless lombok performance

### Reference Documentation
For further reference, please consider the following sections:

The swagger portal is added, refer snapshot

portal url hosted at - http://localhost:7071/swagger-ui.html
![img_1.png](swagger.png)



* 
* Swagger Json Documentation is hosted at 
* [swagger json document](http://localhost:7071/v2/api-docs)

### UML
The following guides illustrate how to the classes are modelled:

* refer src/main/resources/diagrams/irrigation-Uml-diagram.png

Email Service:
For sending email to notify the irrigation administrator.

### Database setup
To run dockerized Mysql Postgresql Instance on 5432 port, with below username, password & database name
* make sure docker daemon is running
* open terminal & run below command

docker run --name my-postgres \
-e POSTGRES_USER=myuser \
-e POSTGRES_PASSWORD=mypassword \
-e POSTGRES_DB=mydatabase \
-p 5432:5432 \
-d postgres


To create the required Plot table

CREATE TABLE plot (
id int,
water_amount VARCHAR(10),
length INT,
is_irrigated BOOLEAN,
breadth INT,
time_slot VARCHAR(20)
);
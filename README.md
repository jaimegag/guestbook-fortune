# guestbook-fortune

This repository contains a sample composition of microservices integrated with Spring Cloud's Config server, Eureka Service Registry and Hystrix Circuit Breaker.
All apps/microservices are Spring Boot apps and can run standalone by running the demo-config, demo-eureka and demo-hystrix-dashbboard apps.
Alternatively the demo-fortune-service, demo-fortune-ui and spring-boot-guestbook can be deployed in Cloud Foundry and bound to Spring Cloud Services.
The GuestBook app requires persistence and uses an embedded H2 datastore when running standalon or a MySQL datastore if a MySQL service is bound to the app (Cloud Foundry only)

### To create services in Cloud Foundry use the create_services.sh

### Here's some handy information about the microservices included:
  

CONFIGURE config_repo in CONFIG SERVER
- https://github.com/jaimegag/config-repo
  

ACCESS FORTUNE SERVICE ENDPOINT
- https://fortune-service-feny.[DOMAIN]/fortunes
- https://fortune-service-feny.[DOMAIN]/random
- https://fortune-service-feny.[DOMAIN]/remote-fortune
  

ACCESS FORTUNE UI
- https://fortune-ui-feny.[DOMAIN]/
  

ACCESS GUESTBOOK
- https://guestbook-feny.[DOMAIN]/
  

ARCHITECTURE
```
UI App -> Service Registry <- GuestBook App -----------------
  |                            |                            |
  |......> Via Hystrix <.......|                            |
  |                            |                            |
  ---------------------Fortunes App -> Service Registry     |
			|                                   |
			----> MySQL DB <---------------------
			|
			----> Config Server
```

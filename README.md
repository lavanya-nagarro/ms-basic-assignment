Spring Boot Microservices Project

This project demonstrates a Microservices Architecture using Spring Boot and Docker.
It includes service discovery, centralized configuration, API gateway routing, and inter-service communication.


Service Registry run on port 8761	Eureka Server for service discovery
Config Server run on port 	8088	Centralized configuration management
Customer Service run on port 	8080	Handles customer-related operations
Account Service	 run on port 8081	Handles account-related operations
API Gateway run on port 	8060	Entry point for all client requests

1️ Build the Services

Run the following command in each service:

mvn clean install

2️ Build Docker Images
docker build -t service-registry .
docker build -t config-server .
docker build -t customer-service .
docker build -t account-service .
docker build -t api-gateway .

3️ Run Using Docker Compose
docker-compose up

Access Services
Service	URL
Eureka Dashboard	http://localhost:8761
API Gateway	http://localhost:8060
Customer Service	http://localhost:8080
Account Service	http://localhost:8081

🔗 Inter-Service Communication

Services communicate using Rest Client.

Example:

Customer Service → Account Service

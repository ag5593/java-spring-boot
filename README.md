Total 4 microservices --

Deploy services per below sequence -

1 Service-discovery  = This is Eureka server for service discovery
2 General-service    = Front Service with Zuul gateway to redirect to other services like project and portfolio, also to authorize every request using JWT
3 project-service    = Background microservice
4 portfolio-service  = Background microservice


URLs 
--------------
http://localhost:8761/   Eureka Service URL to check which all services are registered and it's status
http://localhost:8080/h2-console/   In Memory DB for token generation using user's role and implementation of method level security using JWT


Examples - 
	To Get the token 
	GET   http://{{host}}/token/ag5593
	
	After that include token in other services as "Authorization" header and use "Bearer <token_value>" as header value.
	
	GET http://{{host}}/rest/portfolio/report/get 
	
	GET http://{{host}}/rest/project/hub/get


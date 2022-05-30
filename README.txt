******** Assignment Readme *********

- 4 seperate projects in single repo
	- auth (User Management)
	- todo (ToDo Management)
	- group (Group Management)
	- gateway (API to use todo and group)
	
- All projects have docker-compose.yaml

** auth **

- auth-gateway module is a Spring Boot application and runs on localhost:8080
- auth-management module is responsible for authentication, authorization and user management. CRUD operations for users and roles.
- PostgreSql stores user information, auth-management connects. (Postgres may run with docker-compose)
- Also PgAdmin can be used, container info in docker-compose. Its URL "localhost:8888/login"
- PgAdmin sign-in info:
	* skucuk@nurd.com
		* a1s2d3


** todo**

- todo-gateway module is a Spring Boot application that runs on localhost:8080.
- todo-management module does CRUD operations on todo database.
- MongoDb is used at this module, it may be setup by using docker-compose.
- Todo database connection string is "mongodb://admin:admin@localhost:27017/".
- Swagger is ready at localhost:8080/swagger-ui.html so there is no Postman collection.
- In my opinion, todo-gateway is private, it will be inner application at cloud system. So, its endpoints do not wait authorization token.


** group **

- group-gateway module is a Spring Boot application that runs on localhost:8081.
- group-management does CRUD operations on group database.
- MongoDb is used at this module, it may be setup by using docker-compose.
- Todo database connection string is "mongodb://admin:admin@localhost:27018/".
- Swagger is ready at localhost:8081/swagger-ui.html so there is no Postman collection.
- In my opinion, todo-gateway is private, it will be inner application at cloud system. So, its endpoints do not wait authorization token.


** gateway **

- gateway is a Spring Boot application that runs on localhost:8888.
- It has endpoints that can be used by front-end.
- JWT Bearer token is must, so firstly, you have to sign-in with Postman request and get token. 
- In my opinion, this application may be public on cloud system. 
- This application send requests to endpoints at todo-gateway and group-gateway.
- Swagger is ready at localhost:8888/swagger-ui.html so there is no Postman collection.
- Authorize button at Swagger UI is used to set Bearer token.

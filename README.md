# Balance Service
This is a RESTful service that manages user balances and transaction history. It allows you to add new users, increase or decrease their balance, and retrieve the current balance.

Technologies Used
Java
Spring Boot
Spring Data JPA
Hibernate
H2 Database
Spring Security
Prerequisites
Java 8 or higher
Maven
Getting Started

### Clone the repository:
```shell
git clone https://github.com/alkochekov/balance-service
```
### Build the project:
```shell
cd balance-service
mvn clean install
```
### Build the project:
```shell
mvn spring-boot:run
```
### API Endpoints
Add New User
Request:

Method: POST
URL: http://localhost:8080/balance/addUser
Parameters:
user (String, required) - The username of the new user.
### Example:
```bash
curl -X POST -d "user=johndoe" http://localhost:8080/balance/addUser
```

### Response:
```sql
HTTP/1.1 200 OK
User added successfully
```
Increase Balance
Request:

Method: POST
URL: http://localhost:8080/balance/increaseBalance
Parameters:
user (String, required) - The username of the user.
amount (double, required) - The amount to increase the balance by.
### Example:
```bash
curl -X POST -d "user=johndoe&amount=100.0" http://localhost:8080/balance/increaseBalance
```

### Response:
```sql
HTTP/1.1 200 OK
Balance increased successfully
```
Decrease Balance
Request:

* Method: POST
* URL: http://localhost:8080/balance/decreaseBalance
*Parameters:
* user (String, required) - The username of the user.
* amount (double, required) - The amount to decrease the balance by.

### Example:
```bash
curl -X POST -d "user=johndoe&amount=50.0" http://localhost:8080/balance/decreaseBalance
```

### Response:
```sql
HTTP/1.1 200 OK
Balance decreased successfully
```

Get Balance
Request:

* Method: GET
* URL: http://localhost:8080/balance/getBalance
* Parameters:
* user (String, required) - The username of the user

### Example:
```bash
curl http://localhost:8080/balance/getBalance?user=johndoe
```

### Response:
```sql
HTTP/1.1 200 OK
150.0
```

### Security
This service implements basic authentication using Spring Security. There are two predefined users:

Operator:

* Username: operator
* Password: operator123
* Roles: OPERATOR
Admin:

* Username: admin
* Password: admin123
* Roles: ADMIN
The Operator user can access the following endpoints:

* /balance/addUser
* /balance/increaseBalance
* /balance/decreaseBalance

The Admin user has access to all the endpoints.

To authenticate, include the following header in your requests:
```makefile
Authorization: Basic <base64-encoded-credentials>
```
Replace <base64-encoded-credentials> with the Base64 encoding of <username>:<password>. For example, to authenticate as the Operator user:
```makefile
Authorization: Basic b3BlcmF0b3I6b3BlcmF0b3IxMjM=
```

### Database
The service uses an in-memory H2 database. 
The database is created automatically when the application starts, and the necessary tables are generated based on the entity classes.


### Configuration

The application can be configured using the following properties in the application.properties file:

* spring.datasource.url - The JDBC URL of the database.
* spring.datasource.username - The database username.
* spring.datasource.password - The database password.
* nspring.jpa.hibernate.ddl-auto - Hibernate DDL mode for database schema creation.
* spring.jpa.properties.hibernate.dialect - Hibernate dialect for the chosen database.
* Please refer to the Spring Boot documentation for more details on configuring the application.

### Testing
Unit tests can be found in the BalanceServiceTest class. The tests use MockWebServer and Mockito to simulate HTTP requests and mock dependencies.

# Student Management System 

A simple and practical Student Management System built using Java and Spring Boot to manage student records through REST APIs.

I developed this project to strengthen my backend development skills and gain hands-on experience with Spring Boot, REST API development, database integration, and real-world application structure.

The application allows users to add, view, update, delete, and search student records while maintaining data accuracy through validation and exception handling.

##  Features

- Create Student:** Register new students with their name and email.
- Get Student Details:** Retrieve student information using their unique ID.
- Update Student:** Modify existing student records.
- Delete Student:** Remove student records when they are no longer needed.
- Search Students:** Search student records using name-based filtering.
- Pagination:** Retrieve student records in smaller pages instead of loading all records at once.
- Sorting:** Sort student records by fields such as ID, name, and email in ascending or descending order.
- Input Validation:** Validate required fields and email formats using Jakarta Bean Validation.
- Duplicate Email Prevention:** Prevent registration of students with an already registered email address.
- Global Exception Handling:** Handle application errors centrally and return meaningful JSON error responses.
- DTO Implementation:** Use request and response DTOs to separate API data from database entities.

##  Technologies Used

| Technology | Purpose |
| Java 21 | Programming language |
| Spring Boot | Backend application development |
| Spring Web | REST API development |
| Spring Data JPA | Database operations |
| Hibernate | Object-relational mapping |
| MySQL | Relational database |
| Maven | Dependency management and build |
| Postman | API testing |
| Eclipse IDE | Development environment |

##  Project Structure

src/main/java/com/vinay/studentmanagement/
│
├── controller/
│   └── StudentController.java
│
├── service/
│   └── StudentService.java
│
├── repository/
│   └── StudentRepository.java
│
├── model/
│   └── Student.java
│
├── dto/
│   ├── StudentRequestDTO.java
│   ├── StudentResponseDTO.java
│   ├── StudentPageResponse.java
│   └── ErrorResponse.java
│
└── exception/
    ├── StudentNotFoundException.java
    └── GlobalExceptionHandler.java

##  Application Flow

1. The client sends an HTTP request to the REST API.
2. The Controller receives the request and handles input validation.
3. The Service layer processes the business logic.
4. The Repository layer communicates with MySQL using Spring Data JPA.
5. The database performs the required operation.
6. The application returns a structured JSON response to the client.

## API Operations

| HTTP Method | Operation | Description |
| POST | Add Student | Register a new student |
| GET | Get Students | Retrieve student records |
| GET | Get Student by ID | Retrieve a specific student |
| PUT | Update Student | Update existing student details |
| DELETE | Delete Student | Delete a student record |
| GET | Search Students | Search, sort and paginate student records |

##  How to Run the Project

### Prerequisites

- JDK 21
- MySQL Server
- Eclipse IDE or another Java IDE
- Maven
- Postman

### 1. Clone the repository

git clone YOUR_GITHUB_REPOSITORY_URL

### 2. Create the database

Open MySQL Workbench and execute:

CREATE DATABASE student_management;

### 3. Configure database properties

Open src/main/resources/application.properties and configure:

properties
spring.application.name=StudentManagement

spring.datasource.url=jdbc:mysql://localhost:3306/student_management
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

Replace YOUR_PASSWORD with your MySQL password.

### 4. Run the application

Open the project in Eclipse and run the main Spring Boot application class.

The application will start on the default port 8080, unless configured otherwise.

### 5. Test APIs using Postman

Send HTTP requests to the application endpoints and verify the JSON responses.

##  What I Learned

Working on this project helped me understand how a Spring Boot backend application works in practice.

Some of the key concepts I explored include:

- Building REST APIs using Spring Boot.
- Understanding Controller, Service and Repository layers.
- Connecting Spring Boot applications with MySQL.
- Working with Spring Data JPA and Hibernate.
- Implementing pagination, sorting and search functionality.
- Using DTOs for request and response handling.
- Applying input validation and custom exception handling.
- Maintaining database integrity using unique constraints.
- Testing REST APIs using Postman.

##  Future Improvements

I plan to improve this project by adding:

- Spring Security and JWT-based authentication.
- Swagger/OpenAPI documentation.
- Unit and integration testing.
- Role-based access control.
- A frontend interface for easier interaction.

##  Developer

**Vinay Kandrapu**

Aspiring Java Backend Developer | Java | Spring Boot | SQL | REST APIs

[LinkedIn](https://www.linkedin.com/in/kandrapu-vinay-043899259/)

---

If you find this project useful, feel free to explore the repository!

# 📂 Source Code

Thư mục này chứa mã nguồn của ứng dụng Todo List.

## 📁 Cấu Trúc Thư Mục

```
📦 src
 ┣ 📂 main
 ┃ ┣ 📂 java
 ┃ ┃ ┣ 📂 com
 ┃ ┃ ┃ ┣ 📂 example
 ┃ ┃ ┃ ┃ ┣ 📂 demo1
 ┃ ┃ ┃ ┃ ┃ ┣ 📂 controller
 ┃ ┃ ┃ ┃ ┃ ┣ 📂 model
 ┃ ┃ ┃ ┃ ┃ ┣ 📂 repository
 ┃ ┃ ┃ ┃ ┃ ┗ 📂 service
 ┃ ┃ ┃ ┃ ┃   ┣ 📂 impl
 ┃ ┃ ┃ ┃ ┃   ┣ 📜 TodoService.java
 ┃ ┃ ┃ ┃ ┃   ┗ 📜 UserService.java
 ┃ ┃ ┃ ┃ ┃
 ┃ ┃ ┃ ┃ ┃ ┣ 📜 TodoController.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜 ErrorHandlerController.java
 ┃ ┃ ┃ ┃ ┃
 ┃ ┃ ┃ ┃ ┃ ┣ 📂 model
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂 entity
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜 Todo.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜 User.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂 dto
 ┃ ┃ ┃ ┃ ┃ ┃   ┣ 📜 TodoDTO.java
 ┃ ┃ ┃ ┃ ┃ ┃   ┗ 📜 UserDTO.java
 ┃ ┃ ┃ ┃ ┃ ┃
 ┃ ┃ ┃ ┃ ┃ ┣ 📜 TodoRepository.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜 UserRepository.java
 ┃ ┃ ┃ ┃ ┃
 ┃ ┃ ┃ ┃ ┃ ┣ 📂 service
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜 TodoServiceImpl.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜 UserServiceImpl.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜 README.md
 ┃ ┃ ┃ ┃ ┃
 ┃ ┃ ┃ ┃ ┃ ┣ 📂 repository
 ┃ ┃ ┃ ┃ ┃ ┗ 📜 README.md
 ┃ ┃ ┃ ┃ ┃
 ┃ ┃ ┃ ┃ ┃ ┣ 📂 model
 ┃ ┃ ┃ ┃ ┃ ┗ 📜 README.md
 ┃ ┃ ┃ ┃ ┃
 ┃ ┃ ┃ ┃ ┃ ┣ 📂 controller
 ┃ ┃ ┃ ┃ ┃ ┗ 📜 README.md
 ┃ ┃ ┃ ┃ ┃
 ┃ ┃ ┃ ┃ ┗ 📜 README.md
 ┃ ┃ ┃ ┗ 📜 README.md
 ┃ ┃ ┗ 📜 README.md
 ┃ ┗ 📜 README.md
 ┗ 📜 README.md
```

## 📝 Mô Tả Chi Tiết

### 📂 main/java
- Thư mục chứa mã nguồn Java của ứng dụng
- Tuân thủ cấu trúc package chuẩn của Java
- Chứa các thành phần chính của ứng dụng

### 📂 controller
- Chứa các REST controllers
- Xử lý HTTP requests
- Validate input data
- Xử lý responses và exceptions

### 📂 model
- Chứa các entity và DTO classes
- Định nghĩa cấu trúc dữ liệu
- Xử lý validation và mapping

### 📂 repository
- Chứa các repository interfaces
- Tương tác với database
- Định nghĩa các phương thức truy vấn

### 📂 service
- Chứa các service classes
- Xử lý logic nghiệp vụ
- Kết hợp dữ liệu từ nhiều repository

## 🔧 Công Nghệ Sử Dụng

- Java 17
- Spring Boot
- Spring Data JPA
- Spring MVC
- Lombok
- JUnit 5

## 📋 Quy Tắc Chung

1. Tuân thủ cấu trúc package chuẩn
2. Sử dụng dependency injection
3. Viết code theo clean code principles
4. Comment code đầy đủ
5. Viết unit test cho mỗi component
6. Sử dụng các annotation phù hợp
7. Xử lý exception một cách nhất quán

## 🧪 Testing

- Unit tests cho mỗi layer
- Integration tests cho các API endpoints
- Repository tests với test database
- Service tests với mocked dependencies

## 📚 Tài Liệu Tham Khảo

- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Spring Data JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
- [Spring MVC](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html)
- [JUnit 5](https://junit.org/junit5/docs/current/user-guide/) 
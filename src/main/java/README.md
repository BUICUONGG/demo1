# 📂 Mã Nguồn Java

Thư mục này chứa mã nguồn Java của ứng dụng Todo List.

## 📁 Cấu Trúc Thư Mục

```
📦 src/main/java
 ┣ 📂 com
 ┃ ┣ 📂 example
 ┃ ┃ ┣ 📂 demo1
 ┃ ┃ ┃ ┣ 📂 service
 ┃ ┃ ┃ ┃ ┣ 📜 TodoService.java
 ┃ ┃ ┃ ┃ ┗ 📜 TodoServiceImpl.java
 ┃ ┃ ┃ ┣ 📂 controller
 ┃ ┃ ┃ ┣ 📂 model
 ┃ ┃ ┃ ┣ 📂 repository
 ┃ ┃ ┃ ┗ 📂 config
```

## 📝 Mô Tả Chi Tiết

### 📂 src/main/java
- Thư mục gốc chứa mã nguồn Java
- Tuân thủ cấu trúc package chuẩn của Java
- Chứa các thành phần chính của ứng dụng

### 📂 service
- Chứa các service class xử lý logic nghiệp vụ
- Các service class thường được đánh dấu bằng annotation `@Service`
- Implement các interface service để đảm bảo tính loose coupling
- Chịu trách nhiệm:
  - Xử lý logic nghiệp vụ
  - Kết hợp dữ liệu từ nhiều repository
  - Thực hiện các thao tác phức tạp
  - Validate dữ liệu trước khi lưu

## 🔧 Công Nghệ Sử Dụng

- Java 17 hoặc cao hơn
- Spring Boot
- Spring Data JPA
- JUnit cho unit testing
- Lombok để giảm boilerplate code

## 📋 Quy Tắc Code

1. Tên package phải viết thường
2. Tên class phải viết PascalCase
3. Tên method phải viết camelCase
4. Mỗi class nên có một trách nhiệm duy nhất
5. Code phải được comment đầy đủ
6. Service class phải có interface tương ứng
7. Sử dụng dependency injection thông qua constructor

## 🧪 Testing

- Unit tests được đặt trong thư mục `src/test/java`
- Tên file test phải kết thúc bằng "Test"
- Sử dụng JUnit 5 cho việc viết test
- Mock các dependency trong service test

## 📚 Tài Liệu Tham Khảo

- [Java Documentation](https://docs.oracle.com/javase/17/docs/)
- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Spring Service Layer Best Practices](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans)
- [JUnit 5 Documentation](https://junit.org/junit5/docs/current/user-guide/) 
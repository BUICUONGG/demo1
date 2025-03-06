# 📂 Models

Package này chứa các entity và DTO (Data Transfer Objects) của ứng dụng Todo List.

## 📝 Mô Tả

Models bao gồm các class đại diện cho dữ liệu trong ứng dụng:
- Entity classes: đại diện cho các bảng trong database
- DTO classes: đại diện cho dữ liệu được truyền giữa client và server

## 🔧 Cấu Trúc

```
📂 model
 ┣ 📂 entity
 ┃ ┣ 📜 Todo.java
 ┃ ┗ 📜 User.java
 ┗ 📂 dto
   ┣ 📜 TodoDTO.java
   ┗ 📜 UserDTO.java
```

## 📋 Quy Tắc Code

### Entity Classes
1. Sử dụng annotation `@Entity` và `@Table`
2. Định nghĩa primary key với `@Id`
3. Sử dụng `@GeneratedValue` cho auto-increment
4. Định nghĩa các relationship với `@OneToMany`, `@ManyToOne`, etc.
5. Implement `equals()` và `hashCode()`
6. Sử dụng Lombok để giảm boilerplate code

### DTO Classes
1. Chỉ chứa các trường cần thiết cho API
2. Sử dụng validation annotations
3. Implement `toString()`
4. Sử dụng builder pattern khi cần

## 📚 Ví Dụ Code

### Entity
```java
@Entity
@Table(name = "todos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    private String title;
    
    private String description;
    
    private boolean completed;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
```

### DTO
```java
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoDTO {
    private Long id;
    
    @NotBlank(message = "Title is required")
    private String title;
    
    private String description;
    
    private boolean completed;
}
```

## 🧪 Testing

- Test entity relationships
- Test DTO validation
- Test mapping giữa Entity và DTO
- Test các business rules trong entity

## 📚 Tài Liệu Tham Khảo

- [JPA Entities](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.entity-persistence)
- [Bean Validation](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#validation)
- [Lombok Documentation](https://projectlombok.org/features/all) 
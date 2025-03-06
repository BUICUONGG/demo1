# 📂 Services

Package này chứa các service classes của ứng dụng Todo List.

## 📝 Mô Tả

Services là lớp xử lý logic nghiệp vụ của ứng dụng. Chúng chịu trách nhiệm:
- Xử lý logic nghiệp vụ phức tạp
- Kết hợp dữ liệu từ nhiều repository
- Validate dữ liệu trước khi lưu
- Xử lý các business rules
- Chuyển đổi giữa Entity và DTO

## 🔧 Cấu Trúc

```
📂 service
 ┣ 📂 impl
 ┃ ┣ 📜 TodoServiceImpl.java
 ┃ ┗ 📜 UserServiceImpl.java
 ┣ 📜 TodoService.java
 ┗ 📜 UserService.java
```

## 📋 Quy Tắc Code

1. Interface phải kết thúc bằng "Service"
2. Implementation class phải kết thúc bằng "ServiceImpl"
3. Sử dụng annotation `@Service` cho implementation classes
4. Sử dụng constructor injection cho dependencies
5. Implement các phương thức từ interface
6. Xử lý các exception và chuyển đổi thành custom exceptions
7. Validate dữ liệu trước khi xử lý

## 📚 Ví Dụ Code

### Service Interface
```java
public interface TodoService {
    List<TodoDTO> findAll();
    TodoDTO findById(Long id);
    TodoDTO create(TodoDTO todoDTO);
    TodoDTO update(Long id, TodoDTO todoDTO);
    void delete(Long id);
    List<TodoDTO> findByUserId(Long userId);
}
```

### Service Implementation
```java
@Service
@Slf4j
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
    
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;
    
    @Override
    public List<TodoDTO> findAll() {
        return todoRepository.findAll()
                           .stream()
                           .map(this::convertToDTO)
                           .collect(Collectors.toList());
    }
    
    @Override
    public TodoDTO create(TodoDTO todoDTO) {
        validateTodoDTO(todoDTO);
        Todo todo = convertToEntity(todoDTO);
        return convertToDTO(todoRepository.save(todo));
    }
    
    private void validateTodoDTO(TodoDTO todoDTO) {
        if (todoDTO.getTitle() == null || todoDTO.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
    }
    
    private TodoDTO convertToDTO(Todo todo) {
        return TodoDTO.builder()
                     .id(todo.getId())
                     .title(todo.getTitle())
                     .description(todo.getDescription())
                     .completed(todo.isCompleted())
                     .build();
    }
}
```

## 🧪 Testing

- Test các phương thức service
- Mock các repository dependencies
- Test các trường hợp thành công và thất bại
- Test các business rules
- Test các validation logic
- Test các exception scenarios

## 📚 Tài Liệu Tham Khảo

- [Spring Service Layer](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans)
- [Spring Transaction Management](https://docs.spring.io/spring-framework/docs/current/reference/html/data-access.html#transaction)
- [Spring Validation](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#validation) 
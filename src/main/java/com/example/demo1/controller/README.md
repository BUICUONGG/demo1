# 📂 Controllers

Package này chứa các REST controllers của ứng dụng Todo List.

## 📝 Mô Tả

Controllers là lớp đầu tiên xử lý các HTTP request từ client. Chúng chịu trách nhiệm:
- Định tuyến request đến các service tương ứng
- Validate input data
- Xử lý response trả về cho client
- Xử lý các exception

## 🔧 Cấu Trúc

```
📂 controller
 ┣ 📜 TodoController.java
 ┗ 📜 ErrorHandlerController.java
```

## 📋 Quy Tắc Code

1. Tên class phải kết thúc bằng "Controller"
2. Sử dụng annotation `@RestController` cho REST controllers
3. Sử dụng `@RequestMapping` để định nghĩa base path
4. Sử dụng các HTTP method annotation phù hợp:
   - `@GetMapping` cho READ operations
   - `@PostMapping` cho CREATE operations
   - `@PutMapping` cho UPDATE operations
   - `@DeleteMapping` cho DELETE operations
5. Validate input sử dụng `@Valid` và các validation annotations
6. Trả về ResponseEntity với status code phù hợp
7. Xử lý exception thông qua `@ExceptionHandler`

## 📚 Ví Dụ Code

```java
@RestController
@RequestMapping("/api/todos")
public class TodoController {
    
    private final TodoService todoService;
    
    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }
    
    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos() {
        return ResponseEntity.ok(todoService.findAll());
    }
    
    @PostMapping
    public ResponseEntity<Todo> createTodo(@Valid @RequestBody TodoDTO todoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                           .body(todoService.create(todoDTO));
    }
}
```

## 🧪 Testing

- Test các endpoint với `@WebMvcTest`
- Mock service layer
- Test các trường hợp thành công và thất bại
- Test validation errors
- Test exception handling

## 📚 Tài Liệu Tham Khảo

- [Spring REST Controllers](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-controller)
- [Spring Validation](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#validation)
- [HTTP Status Codes](https://developer.mozilla.org/en-US/docs/Web/HTTP/Status) 
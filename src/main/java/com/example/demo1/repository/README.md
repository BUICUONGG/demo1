# 📂 Repositories

Package này chứa các repository interfaces của ứng dụng Todo List.

## 📝 Mô Tả

Repositories là lớp trung gian giữa service layer và database. Chúng chịu trách nhiệm:
- Định nghĩa các phương thức truy vấn dữ liệu
- Tương tác với database thông qua JPA
- Cung cấp các phương thức CRUD cơ bản

## 🔧 Cấu Trúc

```
📂 repository
 ┣ 📜 TodoRepository.java
 ┗ 📜 UserRepository.java
```

## 📋 Quy Tắc Code

1. Interface phải kết thúc bằng "Repository"
2. Kế thừa từ `JpaRepository` hoặc `CrudRepository`
3. Định nghĩa các custom query methods theo quy tắc đặt tên của Spring Data JPA
4. Sử dụng `@Query` annotation cho các truy vấn phức tạp
5. Sử dụng `@Modifying` cho các truy vấn update/delete
6. Định nghĩa các phương thức tìm kiếm theo đúng quy tắc đặt tên

## 📚 Ví Dụ Code

```java
@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    // Tìm theo title
    List<Todo> findByTitleContaining(String title);
    
    // Tìm các todo chưa hoàn thành
    List<Todo> findByCompletedFalse();
    
    // Custom query với JPQL
    @Query("SELECT t FROM Todo t WHERE t.user.id = :userId AND t.completed = false")
    List<Todo> findUncompletedTodosByUserId(@Param("userId") Long userId);
    
    // Update trạng thái
    @Modifying
    @Query("UPDATE Todo t SET t.completed = :completed WHERE t.id = :id")
    void updateStatus(@Param("id") Long id, @Param("completed") boolean completed);
}
```

## 🧪 Testing

- Test các phương thức CRUD cơ bản
- Test các custom query methods
- Test các trường hợp không tìm thấy dữ liệu
- Test các trường hợp lỗi
- Sử dụng `@DataJpaTest` cho repository tests

## 📚 Tài Liệu Tham Khảo

- [Spring Data JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
- [JPA Query Methods](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods)
- [JPQL Reference](https://docs.oracle.com/javaee/7/tutorial/persistence-querylanguage.htm) 
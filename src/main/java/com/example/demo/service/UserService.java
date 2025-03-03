package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Transactional
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(Long id, User user) {
        User existingUser = getUserById(id);
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        return userRepository.save(existingUser);
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    /**
     * Thêm dữ liệu mẫu khi ứng dụng khởi động
     */
    @PostConstruct
    @Transactional
    public void initializeSampleData() {
        if (userRepository.count() == 0) {  // Kiểm tra xem DB có dữ liệu chưa
            userRepository.save(new User("Nguyễn Văn A", "a@gmail.com"));
            userRepository.save(new User("Trần Thị B", "b@gmail.com"));
            userRepository.save(new User("Lê Văn C", "c@gmail.com"));
        }
    }
}

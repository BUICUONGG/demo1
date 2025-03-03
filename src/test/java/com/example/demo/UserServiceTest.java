package com.example.demo;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User(1L, "John Doe", "john@example.com");
    }

    @Test
    void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(user));
        List<User> users = userService.getAllUsers();
        assertEquals(1, users.size());
    }

    @Test
    void testGetUserById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Optional<User> foundUser = userService.getUserById(1L);
        assertTrue(foundUser.isPresent());
    }

    @Test
    void testCreateUser() {
        when(userRepository.save(user)).thenReturn(user);
        User savedUser = userService.createUser(user);
        assertNotNull(savedUser);
    }

    @Test
    void testDeleteUser() {
        doNothing().when(userRepository).deleteById(1L);
        userService.deleteUser(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetUserById_Fail() {
        // Giả lập repository trả về user không đúng
        when(userRepository.findById(1L)).thenReturn(Optional.of(new User(2L, "Wrong User", "wrong@example.com")));

        Optional<User> foundUser = userService.getUserById(1L);

        // Kiểm tra user có đúng ID không (sẽ bị lỗi)
        assertEquals(1L, foundUser.get().getId(), "Expected ID 1, but got a different ID");
    }




}

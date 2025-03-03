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

    private User user1;
    private User user2;
    private User user3;

    @BeforeEach
    void setUp() {
        user1 = new User(1L, "John Doe", "john@example.com");
        user2 = new User(2L, "Jane Smith", "jane@example.com");
        user3 = new User(3L, "Alice Johnson", "alice@example.com");
    }

    @Test
    void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2, user3));
        List<User> users = userService.getAllUsers();
        assertEquals(3, users.size());
        assertEquals(user1.getName(), users.get(0).getName());
        assertEquals(user2.getName(), users.get(1).getName());
        assertEquals(user3.getName(), users.get(2).getName());
    }

    @Test
    void testGetUserById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        Optional<User> foundUser = userService.getUserById(1L);
        assertTrue(foundUser.isPresent());
        assertEquals(user1.getId(), foundUser.get().getId());
    }

    @Test
    void testCreateUser() {
        when(userRepository.save(user1)).thenReturn(user1);
        User savedUser = userService.createUser(user1);
        assertNotNull(savedUser);
        assertEquals(user1.getName(), savedUser.getName());
    }

    @Test
    void testUpdateUser() {
        User updatedUser = new User(1L, "John Updated", "john.updated@example.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        when(userRepository.save(user1)).thenReturn(updatedUser);

        User result = userService.updateUser(1L, updatedUser);
        assertNotNull(result);
        assertEquals("John Updated", result.getName());
        assertEquals("john.updated@example.com", result.getEmail());
    }

    @Test
    void testDeleteUser() {
        doNothing().when(userRepository).deleteById(1L);
        userService.deleteUser(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetUserById_NotFound() {
        when(userRepository.findById(4L)).thenReturn(Optional.empty());
        Optional<User> foundUser = userService.getUserById(4L);
        assertFalse(foundUser.isPresent());
    }

    @Test
    void testUpdateUser_NotFound() {
        User updatedUser = new User(4L, "Nonexistent User", "nonexistent@example.com");
        when(userRepository.findById(4L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.updateUser(4L, updatedUser);
        });

        assertEquals("User not found", exception.getMessage());
    }

    // Unhappy case: Attempt to create a user with null values
    @Test
    void testCreateUser_NullValues() {
        User nullUser = new User(null, null, null);
        when(userRepository.save(nullUser)).thenThrow(new IllegalArgumentException("User details cannot be null"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.createUser(nullUser);
        });

        assertEquals("User details cannot be null", exception.getMessage());
    }

    // Unhappy case: Attempt to delete a user that does not exist
    @Test
    void testDeleteUser_NotFound() {
        doThrow(new RuntimeException("User not found")).when(userRepository).deleteById(4L);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.deleteUser(4L);
        });

        assertEquals("User not found", exception.getMessage());
    }


}

package com.example.demo;

import com.example.demo.controller.UserController;
import com.example.demo.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;


public class UserTest {
    // Create new User instance with name and email using parameterized constructor
    @Test
    public void test_create_user_with_name_and_email() {
        String name = "John Doe";
        String email = "john@example.com";
        User user = new User(name, email);
        assertNotNull(user);
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
        assertNull(user.getId());
    }

    @Test
    void testUserConstructorWithNullValues() {
        User user = new User(null, null);
        assertNull(user.getName(), "Name should be null");
        assertNull(user.getEmail(), "Email should be null");
    }

    // Create User with null name
    @Test
    public void test_create_user_with_null_name() {
        String email = "test@example.com";
        User user = new User(null, email);
        assertNotNull(user);
        assertNull(user.getName());
        assertEquals(email, user.getEmail());
        assertNull(user.getId());
    }

    @Test
    public void test_create_user_with_null_email() {
        String name = "John Doe";
        User user = new User(name, null);
        assertNotNull(user);
        assertNull(user.getEmail());
        assertEquals(name, user.getName());
        assertNull(user.getId());
    }
}

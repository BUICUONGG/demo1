package com.example.demo;

import com.example.demo.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class UserTest {
    @Test
    public void create_user_with_null_fields() {
        User user = new User();
        user.setName(null);
        user.setEmail(null);

        assertNotNull(user);
        assertNull(user.getName());
        assertNull(user.getEmail());
    }

    @Test
    public void create_user_with_valid_data() {
        String name = "John Doe";
        String email = "john@example.com";

        User user = new User();
        user.setName(name);
        user.setEmail(email);

        assertNotNull(user);
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
    }
}

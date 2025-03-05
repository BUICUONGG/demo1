package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.model.Product;
import org.junit.jupiter.api.Test;

public class ProductTest {

    //happy case
    @Test
    public void test_create_product_with_valid_parameters() {
        // Arrange
        String name = "Test Product";
        String description = "Test Description";
        Double price = 19.99;

        // Act
        Product product = new Product(name, description, price);

        // Assert
        assertEquals(name, product.getName());
        assertEquals(description, product.getDescription());
        assertEquals(price, product.getPrice());
        assertNull(product.getId());
    }

    @Test
    public void test_create_product_using_no_args_constructor_and_setters() {
        // Arrange
        Product product = new Product();
        String name = "Test Product";
        String description = "Test Description";
        Double price = 19.99;

        // Act
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);

        // Assert
        assertEquals(name, product.getName());
        assertEquals(description, product.getDescription());
        assertEquals(price, product.getPrice());
        assertNull(product.getId());
    }


    //ease case
    @Test
    public void test_create_product_with_null_name() {
        // Arrange
        String name = null;
        String description = "Test Description";
        Double price = 19.99;

        // Act
        Product product = new Product(name, description, price);

        // Assert
        assertNull(product.getName());
        assertEquals(description, product.getDescription());
        assertEquals(price, product.getPrice());
        assertNull(product.getId());
    }

    @Test
    public void test_create_product_with_null_description() {
        // Arrange
        String name = "Test Product";
        String description = null;
        Double price = 19.99;

        // Act
        Product product = new Product(name, description, price);

        // Assert
        assertEquals(name, product.getName());
        assertNull(product.getDescription());
        assertEquals(price, product.getPrice());
        assertNull(product.getId());
    }

    // Create a Product with null price
    @Test
    public void test_create_product_with_null_price() {
        // Arrange
        String name = "Test Product";
        String description = "Test Description";
        Double price = null;

        // Act
        Product product = new Product(name, description, price);

        // Assert
        assertEquals(name, product.getName());
        assertEquals(description, product.getDescription());
        assertNull(product.getPrice());
        assertNull(product.getId());
    }

    // Create a Product with negative price
    @Test
    public void test_create_product_with_negative_price() {
        // Arrange
        String name = "Negative Price Product";
        String description = "Product with negative price";
        Double price = -10.0;

        // Act
        Product product = new Product(name, description, price);

        // Assert
        assertEquals(name, product.getName());
        assertEquals(description, product.getDescription());
        assertEquals(price, product.getPrice());
        assertNull(product.getId());
    }

    // Create a Product with extremely long name/description
    @Test
    public void test_create_product_with_extremely_long_name_and_description() {
        // Arrange
        String longName = "A".repeat(1000);
        String longDescription = "B".repeat(2000);
        Double price = 29.99;

        // Act
        Product product = new Product(longName, longDescription, price);

        // Assert
        assertEquals(longName, product.getName());
        assertEquals(longDescription, product.getDescription());
        assertEquals(price, product.getPrice());
        assertNull(product.getId());
    }

    // Create a Product with zero price
    @Test
    public void test_create_product_with_zero_price() {
        // Arrange
        String name = "Zero Price Product";
        String description = "Product with zero price";
        Double price = 0.0;

        // Act
        Product product = new Product(name, description, price);

        // Assert
        assertEquals(name, product.getName());
        assertEquals(description, product.getDescription());
        assertEquals(price, product.getPrice());
        assertNull(product.getId());
    }

    //other
    // Verify Product can be updated after creation
    @Test
    public void test_update_product_after_creation() {
        // Arrange
        Product product = new Product("Initial Name", "Initial Description", 10.0);

        // Act
        product.setName("Updated Name");
        product.setDescription("Updated Description");
        product.setPrice(20.0);

        // Assert
        assertEquals("Updated Name", product.getName());
        assertEquals("Updated Description", product.getDescription());
        assertEquals(20.0, product.getPrice());
    }
}

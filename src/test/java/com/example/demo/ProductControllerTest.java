package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.demo.controller.ProductController;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class ProductControllerTest {

    //Happy Path
    @Test
    public void test_constructor_initializes_with_valid_product_service() {
        // Arrange
        ProductService mockProductService = Mockito.mock(ProductService.class);

        // Act
        ProductController productController = new ProductController(mockProductService);

        // Assert
        assertNotNull(productController);
    }

    @Test
    public void test_constructor_with_mocked_product_service() {
        // Arrange
        ProductService mockProductService = Mockito.mock(ProductService.class);

        // Act
        ProductController productController = new ProductController(mockProductService);

        // Assert
        assertNotNull(productController);
    }

    @Test
    public void test_constructor_with_real_product_service() {
        // Arrange
        ProductRepository mockProductRepository = Mockito.mock(ProductRepository.class);
        ProductService realProductService = new ProductService(mockProductRepository);

        // Act
        ProductController productController = new ProductController(realProductService);

        // Assert
        assertNotNull(productController);
    }

    // Returns HTTP 200 OK status with list of products
    @Test
    public void test_get_all_products_returns_ok_status_with_product_list() {
        // Arrange
        ProductService productService = Mockito.mock(ProductService.class);
        ProductController productController = new ProductController(productService);
        List<Product> expectedProducts = Arrays.asList(
                new Product("Product 1", "Description 1", 10.0),
                new Product("Product 2", "Description 2", 20.0)
        );
        Mockito.when(productService.getAllProducts()).thenReturn(expectedProducts);

        // Act
        ResponseEntity<List<Product>> response = productController.getAllProducts();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedProducts, response.getBody());
        Mockito.verify(productService).getAllProducts();
    }

    // Returns empty list when no products exist
    @Test
    public void test_get_all_products_returns_empty_list_when_no_products_exist() {
        // Arrange
        ProductService productService = Mockito.mock(ProductService.class);
        ProductController productController = new ProductController(productService);
        List<Product> emptyProductList = Collections.emptyList();
        Mockito.when(productService.getAllProducts()).thenReturn(emptyProductList);

        // Act
        ResponseEntity<List<Product>> response = productController.getAllProducts();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(emptyProductList, response.getBody());
        Mockito.verify(productService).getAllProducts();
    }

    //Other
    @Test
    public void test_constructor_throws_exception_when_product_service_is_null() {
        // Arrange
        ProductService nullProductService = null;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new ProductController(nullProductService);
        });
    }

    // Content type of response should be application/json

   //ease case
    @Test
    public void test_constructor_throws_exception_when_null_service_provided() {
        // Arrange & Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new ProductController(null)
        );

        assertEquals("ProductService must not be null", exception.getMessage());
    }


    // Handles large number of products
    @Test
    public void test_get_all_products_handles_large_product_list() {
        // Arrange
        ProductService productService = Mockito.mock(ProductService.class);
        ProductController productController = new ProductController(productService);
        List<Product> largeProductList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            largeProductList.add(new Product("Product " + i, "Description " + i, (double) i));
        }
        Mockito.when(productService.getAllProducts()).thenReturn(largeProductList);

        // Act
        ResponseEntity<List<Product>> response = productController.getAllProducts();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1000, response.getBody().size());
        assertEquals(largeProductList, response.getBody());
        Mockito.verify(productService).getAllProducts();
    }

    // Response when productService returns null instead of empty list
    @Test
    public void test_get_all_products_returns_ok_status_with_null_body() {
        // Arrange
        ProductService productService = Mockito.mock(ProductService.class);
        ProductController productController = new ProductController(productService);
        Mockito.when(productService.getAllProducts()).thenReturn(null);

        // Act
        ResponseEntity<List<Product>> response = productController.getAllProducts();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
        Mockito.verify(productService).getAllProducts();
    }
}

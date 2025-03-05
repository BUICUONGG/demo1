package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductServiceTest {

    //happy case
    // getAllProducts returns all products from repository
    @Test
    public void test_get_all_products_returns_all_products_from_repository() {
        // Arrange
        ProductRepository productRepository = Mockito.mock(ProductRepository.class);
        List<Product> expectedProducts = Arrays.asList(
                new Product(1L, "Product 1", "Description 1", 10.0),
                new Product(2L, "Product 2", "Description 2", 20.0)
        );
        Mockito.when(productRepository.findAll()).thenReturn(expectedProducts);

        ProductService productService = new ProductService(productRepository);

        // Act
        List<Product> actualProducts = productService.getAllProducts();

        // Assert
        assertEquals(expectedProducts, actualProducts);
        Mockito.verify(productRepository).findAll();
    }

    // getProductById returns product when ID exists
    @Test
    public void test_get_product_by_id_returns_product_when_id_exists() {
        // Arrange
        ProductRepository productRepository = Mockito.mock(ProductRepository.class);
        Product expectedProduct = new Product(1L, "Product 1", "Description 1", 10.0);
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(expectedProduct));

        ProductService productService = new ProductService(productRepository);

        // Act
        Product actualProduct = productService.getProductById(1L);

        // Assert
        assertEquals(expectedProduct, actualProduct);
        Mockito.verify(productRepository).findById(1L);
    }

    // createProduct saves and returns new product
    @Test
    public void test_create_product_saves_and_returns_new_product() {
        // Arrange
        ProductRepository productRepository = Mockito.mock(ProductRepository.class);
        Product newProduct = new Product("New Product", "New Description", 30.0);
        Product savedProduct = new Product(1L, "New Product", "New Description", 30.0);
        Mockito.when(productRepository.save(newProduct)).thenReturn(savedProduct);

        ProductService productService = new ProductService(productRepository);

        // Act
        Product result = productService.createProduct(newProduct);

        // Assert
        assertEquals(savedProduct, result);
        Mockito.verify(productRepository).save(newProduct);
    }

    // updateProduct modifies existing product properties
    @Test
    public void test_update_product_modifies_existing_product_properties() {
        // Arrange
        ProductRepository productRepository = Mockito.mock(ProductRepository.class);
        Product existingProduct = new Product(1L, "Old Name", "Old Description", 10.0);
        Product updatedProduct = new Product("New Name", "New Description", 20.0);

        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
        Mockito.when(productRepository.save(existingProduct)).thenReturn(existingProduct);

        ProductService productService = new ProductService(productRepository);

        // Act
        Product result = productService.updateProduct(1L, updatedProduct);

        // Assert
        assertEquals("New Name", result.getName());
        assertEquals("New Description", result.getDescription());
        assertEquals(20.0, result.getPrice());
        Mockito.verify(productRepository).findById(1L);
        Mockito.verify(productRepository).save(existingProduct);
    }

    // deleteProduct removes product by ID
    @Test
    public void test_delete_product_removes_product_by_id() {
        // Arrange
        ProductRepository productRepository = Mockito.mock(ProductRepository.class);
        Long productId = 1L;
        ProductService productService = new ProductService(productRepository);

        // Act
        productService.deleteProduct(productId);

        // Assert
        Mockito.verify(productRepository).deleteById(productId);
    }

    //ease case
    // getProductById throws RuntimeException when product not found
    @Test
    public void test_get_product_by_id_throws_exception_when_product_not_found() {
        // Arrange
        ProductRepository productRepository = Mockito.mock(ProductRepository.class);
        Long nonExistentId = 999L;
        Mockito.when(productRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        ProductService productService = new ProductService(productRepository);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            productService.getProductById(nonExistentId);
        });

        assertEquals("Product not found", exception.getMessage());
        Mockito.verify(productRepository).findById(nonExistentId);
    }

    // updateProduct throws RuntimeException when ID doesn't exist
    @Test
    public void test_update_product_throws_exception_when_id_not_found() {
        // Arrange
        ProductRepository productRepository = Mockito.mock(ProductRepository.class);
        Long nonExistentId = 999L;
        Product productToUpdate = new Product("Updated Name", "Updated Description", 99.99);
        Mockito.when(productRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        ProductService productService = new ProductService(productRepository);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            productService.updateProduct(nonExistentId, productToUpdate);
        });
        Mockito.verify(productRepository).findById(nonExistentId);
    }

    // createProduct with null fields
    @Test
    public void test_create_product_with_null_fields_throws_exception() {
        // Arrange
        ProductRepository productRepository = Mockito.mock(ProductRepository.class);
        ProductService productService = new ProductService(productRepository);
        Product productWithNullFields = new Product(null, null, null);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            productService.createProduct(productWithNullFields);
        });

        assertEquals("Product fields cannot be null", exception.getMessage());
        Mockito.verify(productRepository, Mockito.never()).save(Mockito.any(Product.class));
    }


    // updateProduct with null fields
    @Test
    public void test_update_product_with_null_fields() {
        // Arrange
        ProductRepository productRepository = Mockito.mock(ProductRepository.class);
        Product existingProduct = new Product(1L, "Existing Product", "Existing Description", 30.0);

        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ProductService productService = new ProductService(productRepository);

        // Act
        Product updatedProduct = productService.updateProduct(1L, new Product(null, null, null));

        // Assert
        assertEquals("Existing Product", updatedProduct.getName());  // Giữ nguyên giá trị cũ
        assertEquals("Existing Description", updatedProduct.getDescription()); // Giữ nguyên giá trị cũ
        assertEquals(30.0, updatedProduct.getPrice());  // Giữ nguyên giá trị cũ
        Mockito.verify(productRepository).save(Mockito.any(Product.class));
    }


    // deleteProduct with non-existent ID
    @Test
    public void test_delete_product_with_non_existent_id_throws_exception() {
        // Arrange
        ProductRepository productRepository = Mockito.mock(ProductRepository.class);
        Long nonExistentId = 999L;
        Mockito.doThrow(new RuntimeException("Product not found"))
                .when(productRepository).deleteById(nonExistentId);

        ProductService productService = new ProductService(productRepository);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> productService.deleteProduct(nonExistentId));
        Mockito.verify(productRepository).deleteById(nonExistentId);
    }

    //other

    // test cascade operations if relationships are added later
    @Test
    public void test_create_product_cascades_correctly() {
        // Arrange
        ProductRepository productRepository = Mockito.mock(ProductRepository.class);
        Product product = new Product("New Product", "New Description", 30.0);
        Mockito.when(productRepository.save(product)).thenReturn(product);

        ProductService productService = new ProductService(productRepository);

        // Act
        Product createdProduct = productService.createProduct(product);

        // Assert
        assertEquals(product, createdProduct);
        Mockito.verify(productRepository).save(product);
    }

    // check if repository.save is called with correct parameters
    @Test
    public void test_create_product_calls_save_with_correct_parameters() {
        // Arrange
        ProductRepository productRepository = Mockito.mock(ProductRepository.class);
        Product product = new Product("Product Name", "Product Description", 100.0);
        ProductService productService = new ProductService(productRepository);

        // Act
        productService.createProduct(product);

        // Assert
        Mockito.verify(productRepository).save(product);
    }
}

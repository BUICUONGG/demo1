package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Transactional
    public Product createProduct(Product product) {
        if (product.getName() == null || product.getDescription() == null || product.getPrice() == null) {
            throw new RuntimeException("Product fields cannot be null");
        }
        return productRepository.save(product);
    }


    @Transactional
    public Product updateProduct(Long id, Product product) {
        Product existingProduct = getProductById(id);

        if (product.getName() != null) {
            existingProduct.setName(product.getName());
        }
        if (product.getDescription() != null) {
            existingProduct.setDescription(product.getDescription());
        }
        if (product.getPrice() != null) {
            existingProduct.setPrice(product.getPrice());
        }

        return productRepository.save(existingProduct);
    }


    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

}

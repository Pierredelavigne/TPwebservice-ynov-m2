package com.example.graphqlshop.service;

import com.example.graphqlshop.dto.ProductInput;
import com.example.graphqlshop.entity.Category;
import com.example.graphqlshop.entity.Product;
import com.example.graphqlshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {
    
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    
    public List<Product> findAll() {
        return productRepository.findAll();
    }
    
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found: " + id));
    }
    
    public List<Product> findByCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }
    
    public Product create(ProductInput input) {
        Category category = null;
        if (input.getCategoryId() != null) {
            category = categoryService.findById(input.getCategoryId());
        }
        
        Product product = Product.builder()
                .name(input.getName())
                .description(input.getDescription())
                .price(input.getPrice())
                .stock(input.getStock())
                .category(category)
                .build();
        return productRepository.save(product);
    }
    
    public Product update(Long id, ProductInput input) {
        Product product = findById(id);
        
        if (input.getCategoryId() != null) {
            Category category = categoryService.findById(input.getCategoryId());
            product.setCategory(category);
        }
        
        product.setName(input.getName());
        product.setDescription(input.getDescription());
        product.setPrice(input.getPrice());
        product.setStock(input.getStock());
        return productRepository.save(product);
    }
    
    public Boolean delete(Long id) {
        productRepository.deleteById(id);
        return true;
    }
}

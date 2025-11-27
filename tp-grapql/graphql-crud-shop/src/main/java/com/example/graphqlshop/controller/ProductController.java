package com.example.graphqlshop.controller;

import com.example.graphqlshop.dto.ProductInput;
import com.example.graphqlshop.entity.Category;
import com.example.graphqlshop.entity.Product;
import com.example.graphqlshop.service.CategoryService;
import com.example.graphqlshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {
    
    private final ProductService productService;
    private final CategoryService categoryService;
    
    @QueryMapping
    public List<Product> products() {
        return productService.findAll();
    }
    
    @QueryMapping
    public Product product(@Argument Long id) {
        return productService.findById(id);
    }
    
    @QueryMapping
    public List<Product> productsByCategory(@Argument Long categoryId) {
        return productService.findByCategoryId(categoryId);
    }
    
    @SchemaMapping(typeName = "Product", field = "category")
    public Category getCategory(Product product) {
        if (product.getCategory() == null) return null;
        return categoryService.findById(product.getCategory().getId());
    }
    
    @MutationMapping
    public Product createProduct(@Argument ProductInput input) {
        return productService.create(input);
    }
    
    @MutationMapping
    public Product updateProduct(@Argument Long id, @Argument ProductInput input) {
        return productService.update(id, input);
    }
    
    @MutationMapping
    public Boolean deleteProduct(@Argument Long id) {
        return productService.delete(id);
    }
}

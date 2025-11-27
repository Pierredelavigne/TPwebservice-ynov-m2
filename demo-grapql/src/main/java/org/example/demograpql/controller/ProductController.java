package org.example.demograpql.controller;


import lombok.RequiredArgsConstructor;
import org.example.demograpql.dto.ProductInput;
import org.example.demograpql.entity.Product;
import org.example.demograpql.service.ProductService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @QueryMapping
    public List<Product> products() {
        return productService.findAll();
    }

    @QueryMapping
    public Product product(@Argument Long id) {
        return productService.findById(id);
    }

    @MutationMapping
    public Product createProduct(@Argument ProductInput input){
        return productService.create(input);
    }
    @MutationMapping
    public Product updateProduct(@Argument Long id, @Argument ProductInput input){
        return productService.update(id, input);
    }

    @MutationMapping
    public boolean deleteProduct(@Argument Long id){
        return productService.delete(id);
    }

}

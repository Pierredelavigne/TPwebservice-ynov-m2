package com.example.graphqlshop.controller;

import com.example.graphqlshop.dto.CategoryInput;
import com.example.graphqlshop.entity.Category;
import com.example.graphqlshop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CategoryController {
    
    private final CategoryService categoryService;
    
    @QueryMapping
    public List<Category> categories() {
        return categoryService.findAll();
    }
    
    @QueryMapping
    public Category category(@Argument Long id) {
        return categoryService.findById(id);
    }
    
    @MutationMapping
    public Category createCategory(@Argument CategoryInput input) {
        return categoryService.create(input);
    }
    
    @MutationMapping
    public Category updateCategory(@Argument Long id, @Argument CategoryInput input) {
        return categoryService.update(id, input);
    }
    
    @MutationMapping
    public Boolean deleteCategory(@Argument Long id) {
        return categoryService.delete(id);
    }
}

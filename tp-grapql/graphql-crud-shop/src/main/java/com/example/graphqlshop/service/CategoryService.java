package com.example.graphqlshop.service;

import com.example.graphqlshop.dto.CategoryInput;
import com.example.graphqlshop.entity.Category;
import com.example.graphqlshop.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {
    
    private final CategoryRepository categoryRepository;
    
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
    
    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found: " + id));
    }
    
    public Category create(CategoryInput input) {
        Category category = Category.builder()
                .name(input.getName())
                .description(input.getDescription())
                .build();
        return categoryRepository.save(category);
    }
    
    public Category update(Long id, CategoryInput input) {
        Category category = findById(id);
        category.setName(input.getName());
        category.setDescription(input.getDescription());
        return categoryRepository.save(category);
    }
    
    public Boolean delete(Long id) {
        categoryRepository.deleteById(id);
        return true;
    }
}

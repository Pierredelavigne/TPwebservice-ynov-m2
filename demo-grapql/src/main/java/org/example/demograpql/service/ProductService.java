package org.example.demograpql.service;


import lombok.RequiredArgsConstructor;
import org.example.demograpql.dto.ProductInput;
import org.example.demograpql.entity.Product;
import org.example.demograpql.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {


    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found: " + id));
    }

    public Product create(ProductInput input) {
        Product product = Product.builder()
                .name(input.getName())
                .description(input.getDescription())
                .price(input.getPrice())
                .quantity(input.getQuantity())
                .build();
        return productRepository.save(product);
    }

    public Product update(Long id, ProductInput input) {
        Product product = findById(id);
        product.setName(input.getName());
        product.setDescription(input.getDescription());
        product.setPrice(input.getPrice());
        product.setQuantity(input.getQuantity());
        return productRepository.save(product);
    }

    public Boolean delete(Long id) {
        productRepository.deleteById(id);
        return true;
    }


}

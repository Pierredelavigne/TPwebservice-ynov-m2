package com.example.graphqlshop.service;

import com.example.graphqlshop.dto.ReviewInput;
import com.example.graphqlshop.entity.Product;
import com.example.graphqlshop.entity.Review;
import com.example.graphqlshop.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {
    
    private final ReviewRepository reviewRepository;
    private final ProductService productService;
    
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }
    
    public Review findById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found: " + id));
    }
    
    public List<Review> findByProductId(Long productId) {
        return reviewRepository.findByProductId(productId);
    }
    
    public Review create(ReviewInput input) {
        Product product = productService.findById(input.getProductId());
        
        Review review = Review.builder()
                .author(input.getAuthor())
                .comment(input.getComment())
                .rating(input.getRating())
                .product(product)
                .build();
        return reviewRepository.save(review);
    }
    
    public Review update(Long id, ReviewInput input) {
        Review review = findById(id);
        
        if (input.getProductId() != null) {
            Product product = productService.findById(input.getProductId());
            review.setProduct(product);
        }
        
        review.setAuthor(input.getAuthor());
        review.setComment(input.getComment());
        review.setRating(input.getRating());
        return reviewRepository.save(review);
    }
    
    public Boolean delete(Long id) {
        reviewRepository.deleteById(id);
        return true;
    }
}

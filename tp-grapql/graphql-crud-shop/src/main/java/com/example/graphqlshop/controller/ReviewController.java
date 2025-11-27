package com.example.graphqlshop.controller;

import com.example.graphqlshop.dto.ReviewInput;
import com.example.graphqlshop.entity.Product;
import com.example.graphqlshop.entity.Review;
import com.example.graphqlshop.service.ProductService;
import com.example.graphqlshop.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReviewController {
    
    private final ReviewService reviewService;
    private final ProductService productService;
    
    @QueryMapping
    public List<Review> reviews() {
        return reviewService.findAll();
    }
    
    @QueryMapping
    public Review review(@Argument Long id) {
        return reviewService.findById(id);
    }
    
    @QueryMapping
    public List<Review> reviewsByProduct(@Argument Long productId) {
        return reviewService.findByProductId(productId);
    }
    
    @SchemaMapping(typeName = "Review", field = "product")
    public Product getProduct(Review review) {
        return productService.findById(review.getProduct().getId());
    }
    
    @SchemaMapping(typeName = "Product", field = "reviews")
    public List<Review> getReviews(Product product) {
        return reviewService.findByProductId(product.getId());
    }
    
    @MutationMapping
    public Review createReview(@Argument ReviewInput input) {
        return reviewService.create(input);
    }
    
    @MutationMapping
    public Review updateReview(@Argument Long id, @Argument ReviewInput input) {
        return reviewService.update(id, input);
    }
    
    @MutationMapping
    public Boolean deleteReview(@Argument Long id) {
        return reviewService.delete(id);
    }
}

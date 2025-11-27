package com.example.graphqlshop.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewInput {
    private String author;
    private String comment;
    private Integer rating;
    private Long productId;
}

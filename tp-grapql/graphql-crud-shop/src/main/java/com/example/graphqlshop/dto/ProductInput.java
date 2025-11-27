package com.example.graphqlshop.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInput {
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private Long categoryId;
}

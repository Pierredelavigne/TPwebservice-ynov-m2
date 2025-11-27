package com.example.graphqlshop.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryInput {
    private String name;
    private String description;
}

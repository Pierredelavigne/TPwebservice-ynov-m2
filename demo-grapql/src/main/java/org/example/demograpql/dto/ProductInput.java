package org.example.demograpql.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInput {

    private String name;
    private String description;
    private double price;
    private Integer quantity;

}

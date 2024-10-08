package dev.jaffer.productService.dtos;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {


    private String title;
    private String description;
    private double price;
    private String image;
    private String category;

}

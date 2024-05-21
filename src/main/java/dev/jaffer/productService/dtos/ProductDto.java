package dev.jaffer.productService.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDto {

    private long id;
    private String title;
    private String description;
    private double price;
    private String image;
    private String category;

}

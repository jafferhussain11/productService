package dev.jaffer.productService.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FakeStoreProductDto {

    private long id;
    private String title;
    private String description;
    private double price;
    private String image;
    private String category;
}

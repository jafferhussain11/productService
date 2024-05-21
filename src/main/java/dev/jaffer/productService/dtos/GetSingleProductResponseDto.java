package dev.jaffer.productService.dtos;

import dev.jaffer.productService.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetSingleProductResponseDto {

    private Product product;

}

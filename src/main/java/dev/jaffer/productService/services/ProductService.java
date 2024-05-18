package dev.jaffer.productService.services;

import dev.jaffer.productService.dtos.ProductDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ProductService {
    @GetMapping("/products")
    List<ProductDto> getAllProducts();

    @GetMapping("/products/{productId}")
    String getProductById(@PathVariable("productId") Long productId);

    @PostMapping("/products")
    String addProduct(@RequestBody ProductDto productDto);

    String updateProduct(long id);

    String deleteProduct(long id);
}

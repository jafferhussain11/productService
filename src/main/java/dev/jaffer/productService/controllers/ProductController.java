package dev.jaffer.productService.controllers;

import dev.jaffer.productService.dtos.ProductDto;
import dev.jaffer.productService.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/products")
    public List <ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{productId}")
    public String getProductById(@PathVariable ("productId") Long productId) {
        return "Product with id: " + productId;
    }

    @PostMapping("/products")
    public String addProduct(@RequestBody ProductDto productDto) { //whatever is sent in the body of the request will be mapped to the productDto object
        return "Product added" + productDto;
    }

    public String updateProduct(long id) {
        return "Product with id: " + id + " updated";
    }

    public String deleteProduct(long id) {
        return "Product with id: " + id + " deleted";
    }
}

package dev.jaffer.productService.controllers;

import dev.jaffer.productService.dtos.AddNewProductDto;
import dev.jaffer.productService.dtos.FakeStoreProductDto;
import dev.jaffer.productService.dtos.GetSingleProductResponseDto;
import dev.jaffer.productService.dtos.ProductDto;
import dev.jaffer.productService.models.Category;
import dev.jaffer.productService.models.Product;
import dev.jaffer.productService.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/products")
    public List <Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable ("productId") Long productId) {
        Product product = productService.getProductById(productId);
        GetSingleProductResponseDto responseDto = new GetSingleProductResponseDto();
        responseDto.setProduct(product);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/products")
    public ResponseEntity<Product> addProduct(@RequestBody FakeStoreProductDto productDto) { //whatever is sent in the body of the request will be mapped to the productDto object

        Product product = productService.addProduct(productDto);
        AddNewProductDto responseDto = new AddNewProductDto();
        responseDto.setProduct(product);
        return ResponseEntity.ok(product);

    }

    @PatchMapping("/products/{productId}")
    public Product updateProduct(@PathVariable("productId") long id, @RequestBody ProductDto productDto) {

        Product product = new Product();
        product.setId(id);
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setCategory(new Category());
        product.getCategory().setName(productDto.getCategory());
        product.setImageUrl(productDto.getImage());

        return  productService.updateProduct(id, product);
    }




    public String deleteProduct(long id) {
        return "Product with id: " + id + " deleted";
    }
}

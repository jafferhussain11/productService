package dev.jaffer.productService.controllers;

import dev.jaffer.productService.dtos.*;
import dev.jaffer.productService.exceptions.NotFoundExceptions;
import dev.jaffer.productService.models.Category;
import dev.jaffer.productService.models.Product;
import dev.jaffer.productService.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    private ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Make only admins able to fetch all products
    @GetMapping("/products")
    public ResponseEntity<Page<ProductDto>> getAllProducts(@RequestParam("pageNumber") int pageNumber,
                                                           @RequestParam("pageSize") int pageSize) throws NotFoundExceptions {

        Page<Product> pageOfProducts = productService.getAllProducts(pageNumber, pageSize);

        //thisfuction is used to convert a page of products to a page of productDto
        Page<ProductDto> pageOfProductDto = pageOfProducts.map(product -> convertProductToProductDto(product));

        // Page<ProductDto> pageOfProductDto = pageOfProducts.map(product -> convertProductToProductDto(product));
        return ResponseEntity.ok(pageOfProductDto);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable ("productId") Long productId) throws NotFoundExceptions {

        Optional<Product> productOptional = productService.getProductById(productId);
        if(productOptional.isEmpty()) {
            throw new NotFoundExceptions("Product not found");
        }

        ProductDto productDto = convertProductToProductDto(productOptional.get());
        return ResponseEntity.ok(productDto);

    }

    @PostMapping("/addProduct")
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto) { //whatever is sent in the body of the request will be mapped to the productDto object

        Product product = productService.addProduct(convertProductDtoToProduct(productDto));
        ////////////checkk
//        AddNewProductDto responseDto = new AddNewProductDto();
//        responseDto.setProduct(product);

        ProductDto responseDto = convertProductToProductDto(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);

    }

    @PatchMapping("/products/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("productId") long id, @RequestBody ProductDto productDto) throws NotFoundExceptions {

        Product product = convertProductDtoToProduct(productDto);
        Product updatedProduct = productService.updateProduct(id, product);
        if(updatedProduct == null) {
            throw new NotFoundExceptions("Product not found");
        }
        return ResponseEntity.ok(convertProductToProductDto(updatedProduct));
    }

//////complete this
    public String deleteProduct(long id) {
        return "Product with id: " + id + " deleted";
    }


    private static Product convertProductDtoToProduct(ProductDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setCategory(new Category()); //
        product.getCategory().setName(productDto.getCategory());
        product.setImageUrl(productDto.getImage());
        return product;
    }

    private static ProductDto convertProductToProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setTitle(product.getTitle());
        productDto.setPrice(product.getPrice());
        productDto.setDescription(product.getDescription());
        productDto.setCategory(product.getCategory().getName());
        productDto.setImage(product.getImageUrl());
        return productDto;
    }




}

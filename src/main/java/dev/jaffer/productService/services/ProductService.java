package dev.jaffer.productService.services;

import dev.jaffer.productService.dtos.FakeStoreProductDto;
import dev.jaffer.productService.dtos.ProductDto;
import dev.jaffer.productService.models.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();


    Product getProductById(Long productId);


    Product addProduct(FakeStoreProductDto productDto );

    Product updateProduct(long id,Product product);

    boolean deleteProduct(long id);
}

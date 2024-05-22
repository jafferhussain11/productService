package dev.jaffer.productService.services;

import dev.jaffer.productService.clients.fakeStoreApi.FakeStoreProductDto;
import dev.jaffer.productService.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getAllProducts();


    Optional<Product> getProductById(Long productId);


    Product addProduct(FakeStoreProductDto productDto );

    Product updateProduct(long id,Product product);

    boolean deleteProduct(long id);
}

package dev.jaffer.productService.services;

import dev.jaffer.productService.clients.fakeStoreApi.FakeStoreProductDto;
import dev.jaffer.productService.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Page<Product> getAllProducts(int pageNumber,int pageSize);


    Optional<Product> getProductById(Long productId);


    Product addProduct(Product product );

    Product updateProduct(long id,Product product);

    boolean deleteProduct(long id);
}

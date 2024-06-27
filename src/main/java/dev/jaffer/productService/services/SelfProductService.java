package dev.jaffer.productService.services;

import dev.jaffer.productService.clients.fakeStoreApi.FakeStoreProductDto;
import dev.jaffer.productService.exceptions.NotFoundExceptions;
import dev.jaffer.productService.models.Product;
import dev.jaffer.productService.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("selfProductService")
@Primary
public class SelfProductService implements ProductService{

    ProductRepository productRepository;

    public SelfProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public List<Product> getAllProducts() {
       return productRepository.findAll();
        //return new ArrayList<>();
    }

    @Override
    public Optional<Product> getProductById(Long productId) throws NotFoundExceptions{

        Product product = productRepository.findByIdIs(productId);
        if(product == null) {
            throw new NotFoundExceptions("Product not found");
        }
        return Optional.of(product);
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(long id, Product product) {

        Product productToUpdate = productRepository.findByIdIs(id);
        if (productToUpdate == null) {
            /////////////////chk thiss
            return null;
        }

        productToUpdate.setTitle(product.getTitle());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setImageUrl(product.getImageUrl());
        productToUpdate.setCategory(product.getCategory());

        return productRepository.save(productToUpdate);
    }

    @Override
    public boolean deleteProduct(long id) {

        ////gotta do this
        return false;
    }

}


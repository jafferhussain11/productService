package dev.jaffer.productService.services;

import dev.jaffer.productService.clients.fakeStoreApi.FakeStoreProductDto;
import dev.jaffer.productService.exceptions.NotFoundExceptions;
import dev.jaffer.productService.models.Product;
import dev.jaffer.productService.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Page<Product> getAllProducts(int pageNumber, int pageSize) {
        //pageRequest is an implementation of Pageable interface and has various constructors .of
       return productRepository.findAll(PageRequest.of(pageNumber,pageSize));

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


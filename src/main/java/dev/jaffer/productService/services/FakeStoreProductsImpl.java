package dev.jaffer.productService.services;

import dev.jaffer.productService.dtos.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FakeStoreProductsImpl implements ProductService {

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public List<ProductDto> getAllProducts() {
        return restTemplate.exchange(
                "https://fakestoreapi.com/products",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ProductDto>>() {}
        ).getBody();
    }

    @Override
    public String getProductById(Long productId) {
        return null;
    }

    @Override
    public String addProduct(ProductDto productDto) {
        return null;
    }

    @Override
    public String updateProduct(long id) {
        return null;
    }

    @Override
    public String deleteProduct(long id) {
        return null;
    }
}

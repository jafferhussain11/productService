package dev.jaffer.productService.clients.fakeStoreApi;

import dev.jaffer.productService.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class FakeStoreClient {

    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }
    public List<FakeStoreProductDto> getAllProducts(){
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto[]> listResponse = restTemplate.getForEntity("https://fakestoreapi.com/products",
                FakeStoreProductDto[].class);
        return Arrays.asList(listResponse.getBody());
    }


    Optional<FakeStoreProductDto> getProductById(Long productId){
        return null;
    }


    Product addProduct(FakeStoreProductDto productDto ){
        return null;
    }

    Product updateProduct(long id,Product product){
        return null;
    }

    boolean deleteProduct(long id){
        return false;
    }


}

package dev.jaffer.productService.services;

import dev.jaffer.productService.dtos.FakeStoreProductDto;
import dev.jaffer.productService.dtos.ProductDto;
import dev.jaffer.productService.models.Category;
import dev.jaffer.productService.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductsImpl implements ProductService {


    private RestTemplateBuilder restTemplateBuilder;

    private <T> ResponseEntity<T> requestForEntity(String url,HttpMethod httpMethod, @Nullable Object request,
                                               Class<T> responseType, Object... uriVariables) throws RestClientException {

        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImage());
        Category category = new Category();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        return product;
    }



    public FakeStoreProductsImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }




    @Override
    public List<Product> getAllProducts() {


          RestTemplate restTemplate = restTemplateBuilder.build();

          ResponseEntity<FakeStoreProductDto[]> listResponse = restTemplate.getForEntity("https://fakestoreapi.com/products",
                  FakeStoreProductDto[].class);

          List<Product> products = new ArrayList<>();

          for(FakeStoreProductDto productDto : listResponse.getBody()) {
              Product product = convertFakeStoreProductDtoToProduct(productDto);
              products.add(product);
          }

          return products;

    }

    @Override
    public Product getProductById(Long productId) {

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity("https://fakestoreapi.com/products/{productId}",
                FakeStoreProductDto.class,
                productId);

        FakeStoreProductDto productDto = response.getBody();

        Product product = convertFakeStoreProductDtoToProduct(productDto);

        return product;

    }

    @Override
    public Product addProduct(FakeStoreProductDto productDto) {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(
                "https://fakestoreapi.com/products",
                productDto,
                FakeStoreProductDto.class);

        FakeStoreProductDto productDtoResponse = response.getBody();

        return convertFakeStoreProductDtoToProduct(productDtoResponse);
    }

    @Override
    public Product updateProduct(long id, Product product) { //this product comes from FE then we change it to FakeStoreProductDto and send it to the API

        RestTemplate restTemplate = restTemplateBuilder.build();

        FakeStoreProductDto productDto = new FakeStoreProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImage(product.getImageUrl());
        productDto.setCategory(product.getCategory().getName());

        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = requestForEntity("https://fakestoreapi.com/products/{id}",
                HttpMethod.PATCH,
                productDto,
                FakeStoreProductDto.class,
                id);

        return convertFakeStoreProductDtoToProduct(fakeStoreProductDtoResponseEntity.getBody());
    }

    @Override
    public boolean deleteProduct(long id) {
        return false;
    }
}

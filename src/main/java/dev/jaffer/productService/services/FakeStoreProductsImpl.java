package dev.jaffer.productService.services;

import dev.jaffer.productService.clients.fakeStoreApi.FakeStoreClient;
import dev.jaffer.productService.clients.fakeStoreApi.FakeStoreProductDto;
import dev.jaffer.productService.models.Category;
import dev.jaffer.productService.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
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
import java.util.Optional;

@Service
public class FakeStoreProductsImpl implements ProductService {


    private RestTemplateBuilder restTemplateBuilder;

    private FakeStoreClient fakeStoreClient;

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



    public FakeStoreProductsImpl(RestTemplateBuilder restTemplateBuilder, FakeStoreClient fakeStoreClient) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreClient = fakeStoreClient;
    }




    @Override
    public List<Product> getAllProducts() {


          List<FakeStoreProductDto> listResponse = fakeStoreClient.getAllProducts();

          List<Product> products = new ArrayList<>();

          for(FakeStoreProductDto productDto : listResponse) {
              Product product = convertFakeStoreProductDtoToProduct(productDto);
              products.add(product);
          }

          return products;

    }

    @Override
    public Optional<Product> getProductById(Long productId) {

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity("https://fakestoreapi.com/products/{productId}",
                FakeStoreProductDto.class,
                productId);


        FakeStoreProductDto productDto = response.getBody();
        if (productDto == null) {
            return Optional.empty();
        }
        Product product = convertFakeStoreProductDtoToProduct(productDto);

        return Optional.of(product);

    }

    @Override
    public Product addProduct(Product product) {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(
                "https://fakestoreapi.com/products",
                product,
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
                HttpMethod.PUT,
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

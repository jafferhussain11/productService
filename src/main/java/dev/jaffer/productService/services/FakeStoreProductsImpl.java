package dev.jaffer.productService.services;

import dev.jaffer.productService.clients.fakeStoreApi.FakeStoreClient;
import dev.jaffer.productService.clients.fakeStoreApi.FakeStoreProductDto;
import dev.jaffer.productService.config.RestTemplateConfig;
import dev.jaffer.productService.models.Category;
import dev.jaffer.productService.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
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
//@Primary
public class FakeStoreProductsImpl implements ProductService {

    private RestTemplate restTemplate;

//    private RestTemplateBuilder restTemplateBuilder;

    private FakeStoreClient fakeStoreClient;

    private RedisTemplate<Long, Product> redisTemplate;


    public FakeStoreProductsImpl(RestTemplate restTemplate, FakeStoreClient fakeStoreClient, RedisTemplate<Long, Product> redisTemplate) {

//        this.restTemplateBuilder = restTemplateBuilder;
        this.restTemplate = restTemplate;
        this.fakeStoreClient = fakeStoreClient;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Page<Product> getAllProducts(int pageNumber, int pageSize) {

          List<FakeStoreProductDto> listResponse = fakeStoreClient.getAllProducts();
          List<Product> products = new ArrayList<>();
          for(FakeStoreProductDto productDto : listResponse) {
              Product product = convertFakeStoreProductDtoToProduct(productDto);
              products.add(product);
          }
          Page<Product> pageOfProducts = Page.empty();
            return pageOfProducts;

    }

    @Override
    public Optional<Product> getProductById(Long productId) {

        //check redis
        FakeStoreProductDto redisProduct = (FakeStoreProductDto) redisTemplate.opsForHash().get(productId, "products");
        if (redisProduct != null) {
            return Optional.of(convertFakeStoreProductDtoToProduct(redisProduct));
        }

        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity("https://fakestoreapi.com/products/{productId}",
                FakeStoreProductDto.class,
                productId);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        if (fakeStoreProductDto == null) {
            return Optional.empty();
        }
        //save to redis
        redisTemplate.opsForHash().put(productId, "products", fakeStoreProductDto);
        Product product = convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
        return Optional.of(product);

    }

    @Override
    public Product addProduct(Product product) {

        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(
                "https://fakestoreapi.com/products",
                product,
                FakeStoreProductDto.class);

        FakeStoreProductDto productDtoResponse = response.getBody();
        return convertFakeStoreProductDtoToProduct(productDtoResponse);
    }

    @Override
    public Product updateProduct(long id, Product product) { //this product comes from FE then we change it to FakeStoreProductDto and send it to the API

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

    private <T> ResponseEntity<T> requestForEntity(String url,HttpMethod httpMethod, @Nullable Object request,
                                                   Class<T> responseType, Object... uriVariables) throws RestClientException {

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
}

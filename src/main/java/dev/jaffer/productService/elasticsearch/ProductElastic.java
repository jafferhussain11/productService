package dev.jaffer.productService.elasticsearch;

import dev.jaffer.productService.dtos.ProductDto;
import dev.jaffer.productService.models.Category;
import dev.jaffer.productService.models.Product;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "product")
public class ProductElastic {

    @Id
    private Long id;
    @Field(name = "title")
    private String title;
    @Field(name = "description")
    private String description;
    @Field(name = "price")
    private double price;
    @Field(name = "category")
    private Category category;
    @Field(name = "imageUrl")
    private String imageUrl;

    public  static ProductElastic toProduct(Product product) {
        ProductElastic productElastic = new ProductElastic();
        productElastic.setId(product.getId());
        productElastic.setTitle(product.getTitle());
        productElastic.setDescription(product.getDescription());
        productElastic.setPrice(product.getPrice());
        productElastic.setImageUrl(product.getImageUrl());
        productElastic.setCategory(product.getCategory());
        return productElastic;
    }



}

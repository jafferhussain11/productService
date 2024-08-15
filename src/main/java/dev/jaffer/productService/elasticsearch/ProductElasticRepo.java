package dev.jaffer.productService.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProductElasticRepo extends ElasticsearchRepository<ProductElastic, Long> {

   public List<ProductElastic> findByTitle(String title);
   public ProductElastic save(ProductElastic productElastic);
}

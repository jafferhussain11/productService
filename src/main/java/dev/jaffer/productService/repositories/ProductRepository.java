package dev.jaffer.productService.repositories;

import dev.jaffer.productService.models.Category;
import dev.jaffer.productService.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product save(Product product);

    Product findByIdIs(Long id);

    List<Product> findAllByCategoryIn(List<Category> categoryList);

    //Product updateById(Long id);


}

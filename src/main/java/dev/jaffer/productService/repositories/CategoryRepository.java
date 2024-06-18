package dev.jaffer.productService.repositories;


import dev.jaffer.productService.models.Category;
import dev.jaffer.productService.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long>{

    Category save(Category category);

    Category findByIdIs(Long id);


    List<Category> findByNameIn(List<String> categories);
}

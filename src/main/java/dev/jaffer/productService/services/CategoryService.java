package dev.jaffer.productService.services;

import dev.jaffer.productService.models.Category;
import dev.jaffer.productService.models.Product;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    @GetMapping("/categories")
    List<Category> getAllCategories();

    List<Product> getProductsByCategory(List<String> categories);

    Category addCategory(Category category);
}

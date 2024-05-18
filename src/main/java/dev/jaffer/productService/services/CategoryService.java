package dev.jaffer.productService.services;

import org.springframework.web.bind.annotation.GetMapping;

public interface CategoryService {
    @GetMapping("/categories")
    String getAllCategories();

    String getProductsByCategory(long id);

    String addCategory();
}

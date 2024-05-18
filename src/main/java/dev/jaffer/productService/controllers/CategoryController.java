package dev.jaffer.productService.controllers;

import dev.jaffer.productService.services.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {

    private CategoryService categoryService;

//    public CategoryController(CategoryService categoryService) {
//        this.categoryService = categoryService;
//    }
    @GetMapping("/categories")
    public String getAllCategories() {
        return "All categories";
    }

    public String getProductsByCategory(long id) {
        return "Category with id: " + id;
    }

    public String addCategory() {
        return "Category added";
    }
}

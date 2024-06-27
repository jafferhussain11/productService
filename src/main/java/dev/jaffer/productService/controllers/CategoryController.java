package dev.jaffer.productService.controllers;

import dev.jaffer.productService.dtos.CategoryDto;
import dev.jaffer.productService.dtos.GetProductsByCategoriesRequestDto;
import dev.jaffer.productService.models.Category;
import dev.jaffer.productService.models.Product;
import dev.jaffer.productService.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {

        List<Category> categories = categoryService.getAllCategories();
        List<CategoryDto> categoryDtos = new ArrayList<>();

        for(Category category : categories) {
            CategoryDto categoryDto = convertCategoryToCategoryDto(category);
            categoryDtos.add(categoryDto);
        }

        return ResponseEntity.ok(categoryDtos);
    }

    @GetMapping("/categories/productsByCategory")
    public ResponseEntity<List<Product>> getProductsByCategory(@RequestBody GetProductsByCategoriesRequestDto requestDto) {
////////change to product dto resp entity
        List<Product> products = categoryService.getProductsByCategory(requestDto.getCategoryNames());
        return ResponseEntity.ok(products);
    }

    @PostMapping("/categories")
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto) {

        Category category = convertCategoryDtoToCategory(categoryDto);
        Category savedCategory = categoryService.addCategory(category);
        CategoryDto savedCategoryDto = convertCategoryToCategoryDto(savedCategory);
        return ResponseEntity.ok(savedCategoryDto);
    }

    private static CategoryDto convertCategoryToCategoryDto(Category category) {
        return new CategoryDto(category.getName(), category.getDescription());
    }

    private static Category convertCategoryDtoToCategory(CategoryDto categoryDto) {

        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        return category;
    }

}

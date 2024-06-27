package dev.jaffer.productService.services;

import dev.jaffer.productService.models.Category;
import dev.jaffer.productService.models.Product;
import dev.jaffer.productService.repositories.CategoryRepository;
import dev.jaffer.productService.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("selfCategoryService")
public class SelfCategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepository;
    ProductRepository productRepository;

    public SelfCategoryServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(List<String> categories) {
        List<Category> categoryList = categoryRepository.findByNameIn(categories);
        List<Product> productList = productRepository.findAllByCategoryIn(categoryList);

        return productList;
    }

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }
}

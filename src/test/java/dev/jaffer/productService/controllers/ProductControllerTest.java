package dev.jaffer.productService.controllers;

import dev.jaffer.productService.models.Product;
import dev.jaffer.productService.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductControllerTest {



        @MockBean
        ProductService productService;  //a mock of the ProductService or double of the ProductService

        @Autowired
        ProductController productController;

        @Test
        void testGetAllProducts() {

//                Product product = new Product();
//                product.setId(1L);
//                product.setTitle("Product 1");
//                product.setDescription("Product 1 description");
//                product.setPrice(100.0);
//
//                when(productService.getAllProducts()).thenReturn(List.of(product));
//                assertEquals(100.0, productController.getAllProducts().get(0).getPrice());


        }


}
package dev.jaffer.productService.services;

import dev.jaffer.productService.exceptions.NotFoundExceptions;
import dev.jaffer.productService.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class SelfProductServiceTest {


    @Autowired
    ProductService productService;

    @MockBean
    ProductRepository productRepository; //will not call the actual repository

    @Test
    void testGetSingleProductThrowsNotFoundException() {

        when(productRepository.findByIdIs(any())).thenReturn(null);

        assertThrows(NotFoundExceptions.class, () -> productService.getProductById(1L));
    }


}
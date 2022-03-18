package com.micoservice.store.product;

import com.micoservice.store.product.entity.Category;
import com.micoservice.store.product.entity.Product;
import com.micoservice.store.product.repository.ProductRepository;
import com.micoservice.store.product.service.ProductService;
import com.micoservice.store.product.service.ProductServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class ProductServiceMockTest {

    @Mock
    private ProductRepository productRepository;

    private ProductService productService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        productService = new ProductServiceImpl(productRepository);
        Product product =  Product.builder()
                .id(1L)
                .name("computer")
                .category(Category.builder().id(1L).build())
                .price(Double.parseDouble("12.5"))
                .stock(Double.parseDouble("5"))
                .build();

        Mockito.when(productRepository.findById(1L))
                .thenReturn(Optional.of(product));
        Mockito.when(productRepository.save(product)).thenReturn(product);
    }

    @Test
    public void whenValidGetID_ThenReturnProduct(){
        Product product = productService.getProduct(1L);
        Assertions.assertThat(product.getName()).isEqualTo("computer");

    }

    @Test
    public void whenValidUpdateStock_ThenReturnNewStock(){
        Product productStock = productService.updateStock(1L,Double.parseDouble("8"));
        Assertions.assertThat(productStock.getStock()).isEqualTo(13);
    }

}

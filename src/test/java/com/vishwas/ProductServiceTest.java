package com.vishwas;

import com.vishwas.entity.Product;
import com.vishwas.repo.ProductRepo;
import com.vishwas.service.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
 class ProductServiceTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    ProductRepo productRepo;
    @Test
    void testSave(){

        Product product = Product.builder().id(1L).name("vishwas").category("java").build();
        Mockito.when(productRepo.save(product)).thenReturn(product);
        var savedProduct = productService.save(product);
        Assertions.assertEquals(product.getId(),savedProduct.getId());

    }
    @Test
    void testfindById(){

        var expectedId=2L;
        Product product = Product.builder().id(2L).name("vishwas").category("java").build();
        Mockito.when(productRepo.findById(2L)).thenReturn(Optional.ofNullable(product));
        var product1 = productService.getProduct(expectedId);
        Assertions.assertEquals(expectedId,product1.getId());
    }
    @Test
    void testUpdate(){
            Long productId = 3L;

            Product existingProduct = Product.builder()
                    .id(productId)
                    .name("oldName")
                    .category("oldCategory")
                    .build();

            Product updatedInput = Product.builder()
                    .id(productId)
                    .name("newName")
                    .category("newCategory")
                    .build();

            Product savedProduct = Product.builder()
                    .id(productId)
                    .name("newName")
                    .category("newCategory")
                    .build();

            // Mock findById() to return existing product
            Mockito.when(productRepo.findById(productId)).thenReturn(Optional.of(existingProduct));

            // Mock save() to return the updated product
            Mockito.when(productRepo.save(existingProduct)).thenReturn(savedProduct);

            // Call service method
            Product result = productService.updateProduct(updatedInput);

            // Assertions
            Assertions.assertEquals("newName", result.getName());
            Assertions.assertEquals("newCategory", result.getCategory());

            // Verify repository interactions
            Mockito.verify(productRepo, Mockito.times(1)).findById(productId);
            Mockito.verify(productRepo, Mockito.times(1)).save(existingProduct);
        }


    @Test
    void testFindAll(){

        Product p1 = Product.builder().id(1L).name("Laptop").category("Electronics").build();
        Product p2 = Product.builder().id(2L).name("Phone").category("Electronics").build();
        var listOfProducts = List.of(p1, p2);
        Mockito.when(productRepo.findAll()).thenReturn(listOfProducts);
        var allProducts = productService.getAll();
        Assertions.assertEquals(allProducts.size(),listOfProducts.size());
        Mockito.verify(productRepo, Mockito.times(1)).findAll();
    }
    @Test
    void testdelete(){
        Long productId = 10L;

        // Mock that product exists
        Mockito.when(productRepo.existsById(productId)).thenReturn(true);

        // Call the service method
        productService.delete(productId);

        // Verify deleteById was called
        Mockito.verify(productRepo, Mockito.times(1)).existsById(productId);
        Mockito.verify(productRepo, Mockito.times(1)).deleteById(productId);
    }
    @Test
    public void testDelete_WhenProductDoesNotExist() {
        Long id = 1L;

        Mockito.when(productRepo.existsById(id)).thenReturn(false);

        productService.delete(id);

        // verify deleteById never called
        Mockito.verify(productRepo, Mockito.never()).deleteById(id);
    }


}

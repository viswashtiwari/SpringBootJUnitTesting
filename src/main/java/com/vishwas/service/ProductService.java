package com.vishwas.service;

import com.vishwas.entity.Product;

import java.util.List;

public interface ProductService {
    Product save(Product product);
    Product getProduct(Long id);
    Product updateProduct(Product product);
    List<Product> getAll();
    void delete(Long id);

}

package com.vishwas.service;

import com.vishwas.entity.Product;
import com.vishwas.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepo productRepo;

    @Override
    public Product save(Product product) {
       return productRepo.save(product);
    }

    @Override
    public Product getProduct(Long id) {
        return productRepo.findById(id).orElseThrow(()->new RuntimeException());
    }

    @Override
    public Product updateProduct(Product product) {
        var existingProduct = productRepo.findById(product.getId()).orElseThrow(() -> new RuntimeException());
        existingProduct.setName(product.getName());
        existingProduct.setCategory(product.getCategory());
        return productRepo.save(existingProduct);
    }

    @Override
    public List<Product> getAll() {
        return productRepo.findAll();
    }

    @Override
    public void delete(Long id) {
    if (productRepo.existsById(id)){
        productRepo.deleteById(id);
    }
    }
}

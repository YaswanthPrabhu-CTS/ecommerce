package com.project.ecommerceweb.Service;

import com.project.ecommerceweb.Entity.Product;
import com.project.ecommerceweb.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private ProductRepository productRepository;
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getById(Long id){
       return productRepository.findById(id).orElseThrow(()-> new RuntimeException("no product found"));
    }
}

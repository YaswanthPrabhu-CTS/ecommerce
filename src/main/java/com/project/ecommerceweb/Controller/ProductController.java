package com.project.ecommerceweb.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.project.ecommerceweb.Entity.Product;
import com.project.ecommerceweb.Repository.ProductRepository;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository repo;

    @PostMapping
    public Product add(@RequestBody Product p) {
        return repo.save(p);
    }

    @GetMapping
    public List<Product> getAll() {
        return repo.findAll();
    }
}
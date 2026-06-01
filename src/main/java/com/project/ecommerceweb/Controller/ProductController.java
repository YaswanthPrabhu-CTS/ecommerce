package com.project.ecommerceweb.Controller;

import com.project.ecommerceweb.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.project.ecommerceweb.Entity.Product;
import com.project.ecommerceweb.Repository.ProductRepository;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {


    private ProductRepository repo;

    private ProductService productService;

    @Autowired
    public ProductController(ProductRepository repo, ProductService productService) {
        this.repo = repo;
        this.productService = productService;
    }

    @PostMapping
    public Product add(@RequestBody Product p) {
        return repo.save(p);
    }

    @GetMapping
    public List<Product> getAll() {
        return repo.findAll();
    }

    @GetMapping("/getProduct/{id}")
    public Product getProductById(@PathVariable Long id){
        return productService.getById(id);
    }
}
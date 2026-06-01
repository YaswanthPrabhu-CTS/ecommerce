package com.project.ecommerceweb.Controller;
import com.project.ecommerceweb.Dto.ProductRequest;
import com.project.ecommerceweb.Entity.Product;
import com.project.ecommerceweb.Service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.project.ecommerceweb.Service.ProductService;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

//    @PostMapping
//    public Product addProduct(
//            @RequestHeader("Authorization") String authorization,
//            @Valid @RequestBody ProductRequest request
//    ) {
//        UserEntity user = authService.requireUser(authorization);
//        authService.requireAdmin(user);
//        return productService.addProduct(request);
//    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

//    @PutMapping("/{id}")
//    public Product updateProduct(
//            @RequestHeader("Authorization") String authorization,
//            @PathVariable Long id,
//            @Valid @RequestBody ProductRequest request
//    ) {
//        UserEntity user = authService.requireUser(authorization);
//        authService.requireAdmin(user);
//        return productService.updateProduct(id, request);
//    }

//    @DeleteMapping("/{id}")
//    public String deleteProduct(@RequestHeader("Authorization") String authorization, @PathVariable Long id) {
//        UserEntity user = authService.requireUser(authorization);
//        authService.requireAdmin(user);
//        productService.deleteProduct(id);
//        return "Product deleted";
//    }
}


package com.project.ecommerceweb.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.project.ecommerceweb.Entity.Cart;
import com.project.ecommerceweb.Repository.CartRepository;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartRepository repo;

    @PostMapping("/add")
    public Cart add(@RequestBody Cart item) {
        return repo.save(item);
    }

    @GetMapping("/{userId}")
    public List<Cart> getCart(@PathVariable Long userId) {
        return repo.findByUserId(userId);
    }
}
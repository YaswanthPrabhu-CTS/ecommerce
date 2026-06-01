package com.project.ecommerceweb.Controller;

import com.project.ecommerceweb.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.project.ecommerceweb.Entity.Cart;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {


    private CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public Cart add(@RequestBody Cart item) {
        return cartService.add(item);
    }

    @GetMapping("/{userId}")
    public List<Cart> getCart(@PathVariable Long userId) {

        return cartService.getCart(userId);
    }
}
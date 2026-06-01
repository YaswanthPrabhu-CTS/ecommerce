package com.project.ecommerceweb.Service;

import com.project.ecommerceweb.Entity.Cart;
import com.project.ecommerceweb.Repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public Cart add(Cart item){
        return cartRepository.save(item);
    }

    public List<Cart> getCart(Long userId){
        return cartRepository.findByUserId(userId);
    }

}

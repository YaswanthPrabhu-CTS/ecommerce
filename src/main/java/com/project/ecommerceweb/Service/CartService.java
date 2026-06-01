package com.project.ecommerceweb.Service;

import com.project.ecommerceweb.Entity.Cart;
import com.project.ecommerceweb.Entity.User;
import com.project.ecommerceweb.Repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public Cart add(Cart item){
        return cartRepository.save(item);
    }

    public List<Cart> getCart(Long userId){
        return cartRepository.findByUserId(userId);
    }

    public List<Cart> getCartItems(User user) {
        return cartRepository.findByUserId(user.getId());
    }

    public void clearCart(User user) {
        List<Cart> items = cartRepository.findByUserId(user.getId());
        cartRepository.deleteAll(items);
    }

}

package com.project.ecommerceweb.Service;

import com.project.ecommerceweb.Entity.*;
import com.project.ecommerceweb.Exceptions.ApiException;
import com.project.ecommerceweb.Repository.OrderRepository;
import com.project.ecommerceweb.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CartService cartService;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, CartService cartService) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.cartService = cartService;
    }

    @Transactional
    public Orders placeOrder(User user) {
        List<Cart> cartItems = cartService.getCartItems(user);
        if (cartItems.isEmpty()) {
            throw new ApiException("Cart is empty");
        }

        Orders order = new Orders();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PLACED);

        List<OrderItem> items = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (Cart cartItem : cartItems) {
            Product product = cartItem.getProduct();
            if (product.getStock() < cartItem.getQuantity()) {
                throw new ApiException("Insufficient stock for product: " + product.getName());
            }

            product.setStock(product.getStock() - cartItem.getQuantity());
            productRepository.save(product);

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getTotalPrice());

            items.add(orderItem);
            total = total.add(cartItem.getTotalPrice());
        }

        order.setItems(items);
        order.setTotalAmount(total);

        Orders saved = orderRepository.save(order);
        cartService.clearCart(user);
        return saved;
    }
}

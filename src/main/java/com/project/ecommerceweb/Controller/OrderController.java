package com.project.ecommerceweb.Controller;

import com.project.ecommerceweb.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.project.ecommerceweb.Entity.Order;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderRepository repo;

    @PostMapping("/place")
    public Order placeOrder(@RequestBody Order order) {
        order.setStatus("PLACED");
        order.setOrderDate(LocalDateTime.now());
        return repo.save(order);
    }
}

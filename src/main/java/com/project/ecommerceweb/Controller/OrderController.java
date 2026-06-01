package com.project.ecommerceweb.Controller;

import com.project.ecommerceweb.Entity.User;
import com.project.ecommerceweb.Service.AuthService;
import com.project.ecommerceweb.Repository.OrderRepository;
import com.project.ecommerceweb.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.project.ecommerceweb.Entity.Orders;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderRepository repo;

    @Autowired
    private AuthService authService;

    @Autowired
    private OrderService orderService;

//    @PostMapping("/place")
//    public Orders placeOrder(@RequestBody Orders order) {
//        order.setStatus("PLACED");
//        order.setOrderDate(LocalDateTime.now());
//        return repo.save(order);
//    }
    @PostMapping("/place")
    public Orders placeOrder(@RequestBody Orders order) {
        order.setStatus("PLACED");
        order.setOrderDate(LocalDateTime.now());
        return repo.save(order);
    }
}

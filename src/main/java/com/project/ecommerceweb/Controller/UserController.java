package com.project.ecommerceweb.Controller;

import org.springframework.web.bind.annotation.*;
import com.project.ecommerceweb.Entity.User;
import com.project.ecommerceweb.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return service.register(user);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return service.getUser(id);
    }

}

package com.project.ecommerceweb.Controller;

import com.project.ecommerceweb.Dto.AuthResponse;
import com.project.ecommerceweb.Dto.LoginRequest;
import com.project.ecommerceweb.Service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import com.project.ecommerceweb.Entity.User;
import com.project.ecommerceweb.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    public UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request){
        return userService.login(request);
    }

    //its used for address fetching in frontend api while placing the order
    @GetMapping("/profile")
    public User getProfile(@RequestHeader("Authorization") String authorization){
        User user= authService.requireUser(authorization);
        return  userService.getProfile(user);
    }



//    @GetMapping("/{id}")
//    public User getUser(@PathVariable Long id) {
//        return service.getUser(id);
//    }
}

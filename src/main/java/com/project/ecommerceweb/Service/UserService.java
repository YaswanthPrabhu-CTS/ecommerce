package com.project.ecommerceweb.Service;

import com.project.ecommerceweb.Dto.AuthResponse;
import com.project.ecommerceweb.Entity.User;
import com.project.ecommerceweb.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AuthService authService;

    public UserService(UserRepository userRepository, AuthService authService) {
        this.userRepository = userRepository;
        this.authService = authService;
    }

    public AuthResponse register(User user) {
        userRepository.findByEmail(user.getEmail()).ifPresent(u->{
           throw  new RuntimeException("email already exists");
        });
        User user1= new User();
        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        user1.setPhone(user.getPhone());
        user1.setAddress(user.getAddress());
        user1.setRole("USER");

        User savedUser= userRepository.save(user1);
        String token= authService.createToken(savedUser);
        return new AuthResponse(savedUser.getId(),savedUser.getName(),savedUser.getRole(),token);
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow();
    }
}

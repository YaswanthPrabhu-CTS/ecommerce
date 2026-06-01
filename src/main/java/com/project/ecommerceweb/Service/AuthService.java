package com.project.ecommerceweb.Service;

import com.project.ecommerceweb.Entity.User;
import com.project.ecommerceweb.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final Map<String, Long> tokens = new ConcurrentHashMap<>();

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String createToken(User user) {
        String token = UUID.randomUUID().toString();
        tokens.put(token, user.getId());
        return token;
    }
}

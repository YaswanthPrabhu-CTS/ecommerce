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

    public User requireUser(String authorizationHeader){
        if(authorizationHeader==null || !authorizationHeader.startsWith("Bearer ")){
            throw new RuntimeException("Missing or invalid auth header...");
        }

       String token= authorizationHeader.substring(7);
        Long userId= tokens.get(token);
        if(userId==null){
            throw new RuntimeException("invalid or expired token");
        }

        return userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("user not found"));
    }

    public void requireAdmin(User user){
        if(!user.getRole().equals("ADMIN")){
            throw new RuntimeException("only admins can access!!");
        }
    }
}

package com.project.ecommerceweb.Service;

import com.project.ecommerceweb.Entity.User;
import com.project.ecommerceweb.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;

    public User register(User user) {
        return repo.save(user);
    }

    public User getUser(Long id) {
        return repo.findById(id).orElseThrow();
    }
}

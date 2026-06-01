package com.project.ecommerceweb.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(unique = true)
    @NotBlank(message = "Email is mandatory")
    private String email;
    private String password;
    @NotBlank(message = "Phone number is mandatory")
    private String phone;
    private String address;
    private String role;
}
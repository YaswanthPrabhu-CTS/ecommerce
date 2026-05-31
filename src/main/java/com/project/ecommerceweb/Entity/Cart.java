package com.project.ecommerceweb.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import lombok.Getter;

@Entity
@Getter
public class Cart {
    @Id @GeneratedValue
    private Long id;

    private Long userId;
    private Long productId;
    private int quantity;
}
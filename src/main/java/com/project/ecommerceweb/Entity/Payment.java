package com.project.ecommerceweb.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Payment {
    @Id @GeneratedValue
    private Long id;

    private Long orderId;
    private double amount;
    private String method;
    private String status;
}
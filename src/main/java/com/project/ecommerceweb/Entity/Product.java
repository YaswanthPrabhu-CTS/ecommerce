package com.project.ecommerceweb.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Product {
    @Id @GeneratedValue
    private Long id;

    private String name;
    private String description;
    private double price;
    private int stock;
    private String category;
    private String image;
}

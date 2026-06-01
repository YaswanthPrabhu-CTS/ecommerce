package com.project.ecommerceweb.Repository;

import com.project.ecommerceweb.Entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {
}

package com.project.ecommerceweb.Repository;

import com.project.ecommerceweb.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

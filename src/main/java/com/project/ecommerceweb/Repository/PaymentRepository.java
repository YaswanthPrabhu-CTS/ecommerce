package com.project.ecommerceweb.Repository;

import com.project.ecommerceweb.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}

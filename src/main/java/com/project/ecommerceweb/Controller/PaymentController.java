package com.project.ecommerceweb.Controller;

import org.springframework.web.bind.annotation.*;
import com.project.ecommerceweb.Entity.Payment;
import com.project.ecommerceweb.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentRepository repo;

    @PostMapping("/process")
    public Payment pay(@RequestBody Payment p) {
        p.setStatus("SUCCESS");
        return repo.save(p);
    }
}
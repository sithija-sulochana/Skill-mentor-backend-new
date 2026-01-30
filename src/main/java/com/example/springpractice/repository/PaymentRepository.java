package com.example.springpractice.repository;

import com.example.springpractice.entites.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository 
public interface PaymentRepository extends JpaRepository<Payment,Integer> {
}

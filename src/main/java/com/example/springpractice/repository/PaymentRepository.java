package com.example.springpractice.repository;

import com.example.springpractice.entites.Payment;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Cacheable("payments")
public interface PaymentRepository extends JpaRepository<Payment,Integer> {
}

package com.example.springpractice.repository;

import com.example.springpractice.entites.Session;
import jakarta.validation.constraints.NotNull;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.function.Supplier;

@Repository
@Cacheable("Sessions")
public interface SessionRepository extends JpaRepository<Session,Long> {

}

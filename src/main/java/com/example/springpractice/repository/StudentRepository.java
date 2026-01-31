package com.example.springpractice.repository;


import com.example.springpractice.entites.Student;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Cacheable("students")
public interface StudentRepository extends JpaRepository<Student, Integer> {
}


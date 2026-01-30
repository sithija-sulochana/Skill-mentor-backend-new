package com.example.springpractice.repository;

import com.example.springpractice.entites.Subject;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository 
public interface SubjectRepository extends JpaRepository<Subject,Long> {
}

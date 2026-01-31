package com.example.springpractice.repository;

import com.example.springpractice.entites.Subject;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Cacheable("subjects")
public interface SubjectRepository extends JpaRepository<Subject,Long> {
}

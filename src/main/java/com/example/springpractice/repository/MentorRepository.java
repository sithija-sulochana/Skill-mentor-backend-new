package com.example.springpractice.repository;

import com.example.springpractice.entites.Mentor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
@Cacheable("mentors")
public interface MentorRepository extends JpaRepository<Mentor,Long> {
}

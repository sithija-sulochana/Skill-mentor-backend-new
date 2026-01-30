package com.example.springpractice.repository;

import com.example.springpractice.entites.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository 

public interface CommentRepository extends JpaRepository<Comment,Long> {
}

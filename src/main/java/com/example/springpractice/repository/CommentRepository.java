package com.example.springpractice.repository;

import com.example.springpractice.entites.Comment;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Cacheable("comments")
public interface CommentRepository extends JpaRepository<Comment,Long> {
}

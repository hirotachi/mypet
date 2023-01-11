package com.simplon.mypet.db.repository;

import com.simplon.mypet.db.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
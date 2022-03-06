package com.example.recipes.infrastructure.repositories;

import com.example.recipes.core.entities.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findByCommentIdOrderByDateDesc(Long id);
}

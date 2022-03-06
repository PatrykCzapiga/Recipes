package com.example.recipes.core.services;

import com.example.recipes.core.entities.Comment;
import com.example.recipes.infrastructure.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    public Long saveOrUpdate(Comment comment) {
        comment.setDate(LocalDateTime.now());
        return commentRepository.save(comment).getCommentId();
    }

    public boolean update(Comment comment, UserDetailsImpl userDetails) {
        Comment dbComment = commentRepository.findById(comment.getCommentId()).get();
        if (dbComment.getUser().getUserId() == userDetails.getUserId()) {
            comment.getUser().setUserId(userDetails.getUserId());
            dbComment.setDate(LocalDateTime.now());
            commentRepository.save(dbComment);
            return true;
        }
        return false;
    }

    public List<Comment> findById(Long id) {
        return commentRepository.findByCommentIdOrderByDateDesc(id);
    }

    public boolean delete(Long commentId, UserDetailsImpl userDetails) {
        Comment dbComment = commentRepository.findById(commentId).get();
        if (dbComment.getUser().getUserId() == userDetails.getUserId()) {
            commentRepository.deleteById(commentId);
            return true;
        }
        return false;
    }

}

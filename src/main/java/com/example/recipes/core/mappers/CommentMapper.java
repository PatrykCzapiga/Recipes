package com.example.recipes.core.mappers;

import com.example.recipes.core.dtos.CommentDto;
import com.example.recipes.core.entities.Comment;
import com.example.recipes.core.entities.Recipe;
import com.example.recipes.core.entities.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CommentMapper {

    public static Comment toComment(CommentDto commentDto, long userId, long recipeId) {
        return new Comment(commentDto.getCommentId(), commentDto.getCommentText(), LocalDateTime.now(), new User(userId, null, null, null, null, null), new Recipe (recipeId,null, null, null, null, null, null, null, null));
    }
}

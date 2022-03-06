package com.example.recipes.api.controllers;

import com.example.recipes.api.dtos.IdResponseDto;
import com.example.recipes.core.dtos.CommentDto;
import com.example.recipes.core.mappers.CommentMapper;
import com.example.recipes.core.services.CommentService;
import com.example.recipes.core.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
public class CommentController {

        @Autowired
        CommentService commentService;

        @Autowired
        CommentMapper commentMapper;

        @PostMapping(value = "/api/{id}/addComment", produces = MediaType.APPLICATION_JSON_VALUE)
        public IdResponseDto postRecipe(@AuthenticationPrincipal UserDetailsImpl userDetails, @Valid @RequestBody CommentDto commentDto, @PathVariable long id) {
                if (userDetails != null) {
                        return new IdResponseDto(commentService.saveOrUpdate(commentMapper.toComment(commentDto, userDetails.getUserId(), id)));
                }
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
}

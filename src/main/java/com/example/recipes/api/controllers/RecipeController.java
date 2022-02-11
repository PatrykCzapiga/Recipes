package com.example.recipes.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.example.recipes.api.dtos.IdResponseDto;
import com.example.recipes.api.dtos.RecipeResponseDto;
import com.example.recipes.core.dtos.RecipeDto;
import com.example.recipes.core.mappers.RecipeMapper;
import com.example.recipes.core.services.RecipeService;
import com.example.recipes.core.services.UserDetailsImpl;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class RecipeController {
    @Autowired
    RecipeService recipeService;
    @Autowired
    RecipeMapper recipeMapper;

    @PostMapping(value = "/api/recipe/new", produces = MediaType.APPLICATION_JSON_VALUE)
    public IdResponseDto postRecipe(@AuthenticationPrincipal UserDetailsImpl userDetails, @Valid @RequestBody RecipeDto recipeDto) {
        if (userDetails != null) {
            return new IdResponseDto(recipeService.saveOrUpdate(RecipeMapper.toRecipe(recipeDto, userDetails.getUserId())));
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/api/recipe/{id}")
    public void updateRecipe(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable(value = "id") Long id, @Valid @RequestBody RecipeDto recipeDto) {
        if (userDetails != null) {
            try {
                recipeDto.setId(id);
                if (recipeService.update(RecipeMapper.toRecipe(recipeDto, 0), userDetails)) {
                    throw new ResponseStatusException(HttpStatus.NO_CONTENT);
                } else {
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN);
                }
            } catch (NoSuchElementException exception) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/api/recipe/{id}")
    public RecipeResponseDto getRecipe(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id) {
        if (userDetails != null) {
            try {
                return RecipeMapper.toRecipeResponseDto(recipeService.getRecipeById(id));
            } catch (NoSuchElementException exception) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/api/recipe/search")
    @ResponseBody
    public List<RecipeResponseDto> search(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam(name = "category", required = false) String category, @RequestParam(name = "name", required = false) String name) {
        if (userDetails != null) {
            List<RecipeResponseDto> list = new ArrayList<>();
            list.clear();
            if (category != null && name == null) {
                recipeService.searchByCategory(category).forEach(x -> list.add(recipeMapper.toRecipeResponseDto(x)));
            } else if (name != null && category == null) {
                recipeService.searchByName(name).forEach(x -> list.add(recipeMapper.toRecipeResponseDto(x)));
            } else {
                System.out.println(category + " " + name);
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            return list;
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping("/api/recipe/{id}")
    public void deleteRecipe(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id) {
        if (userDetails != null) {
            try {
                if (recipeService.delete(id, userDetails)) {
                    throw new ResponseStatusException(HttpStatus.NO_CONTENT);
                } else {
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN);
                }
            } catch (NoSuchElementException exception) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            } catch (EmptyResultDataAccessException exception) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }

}
package com.example.recipes.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.recipes.core.entities.Recipe;
import com.example.recipes.core.mappers.RecipeMapper;
import com.example.recipes.infrastructure.repositories.RecipeRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RecipeService {

    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    RecipeMapper recipeMapper;

    public Recipe getRecipeById(Long id) {
        return recipeRepository.findById(id).get();
    }

    public Long saveOrUpdate(Recipe recipe) {
        recipe.setDate(LocalDateTime.now());
        return recipeRepository.save(recipe).getId();
    }

    public boolean update(Recipe recipe, UserDetailsImpl userDetails) {
        Recipe dbRecipe = recipeRepository.findById(recipe.getId()).get();
        if (dbRecipe.getUser().getUserId() == userDetails.getUserId()) {
            recipe.getUser().setUserId(userDetails.getUserId());
            dbRecipe.setDate(LocalDateTime.now());
            recipeRepository.save(RecipeMapper.mapToRecipe(dbRecipe, recipe));
            return true;
        }
        return false;
    }

    public List<Recipe> searchByCategory(String category) {
        return recipeRepository.findByCategoryIgnoreCaseOrderByDateDesc(category);
    }

    public List<Recipe> searchByName(String str) {
        return recipeRepository.findByNameContainsIgnoreCaseOrderByDateDesc(str);
    }

    public boolean delete(Long id, UserDetailsImpl userDetails) {
        Recipe dbRecipe = recipeRepository.findById(id).get();
        if (dbRecipe.getUser().getUserId() == userDetails.getUserId()) {
            recipeRepository.deleteById(id);
            return true;
        }
        return false;
    }

}

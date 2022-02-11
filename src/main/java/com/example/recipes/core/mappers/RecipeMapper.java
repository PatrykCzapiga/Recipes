package com.example.recipes.core.mappers;

import com.example.recipes.api.dtos.RecipeResponseDto;
import org.springframework.stereotype.Component;
import com.example.recipes.core.dtos.RecipeDto;
import com.example.recipes.core.entities.Recipe;
import com.example.recipes.core.entities.User;

import java.time.LocalDateTime;

@Component
public class RecipeMapper {

    public static Recipe toRecipe(RecipeDto recipeDto, long userId) {
        return new Recipe(recipeDto.getId(), recipeDto.getName(), recipeDto.getCategory(), LocalDateTime.now(), recipeDto.getDescription(),
                recipeDto.getIngredients(), recipeDto.getDirections(), new User(userId, "", "", ""));
    }

    public static RecipeDto toRecipeDto(Recipe recipe) {
        return new RecipeDto(recipe.getId(), recipe.getName(), recipe.getCategory(), recipe.getDescription(),
                recipe.getIngredients(), recipe.getDirections());
    }

    public static RecipeResponseDto toRecipeResponseDto(Recipe recipe) {
        return new RecipeResponseDto(recipe.getName(), recipe.getCategory(), recipe.getDate(), recipe.getDescription(),
                recipe.getIngredients(), recipe.getDirections());
    }

    public static Recipe mapToRecipe(Recipe recipe, Recipe recipe2) {
        recipe.setId(recipe2.getId());
        recipe.setName(recipe2.getName());
        recipe.setCategory(recipe2.getCategory());
        recipe.setDate(recipe2.getDate());
        recipe.setDescription(recipe2.getDescription());
        recipe.setIngredients(recipe2.getIngredients());
        recipe.setDirections(recipe2.getDirections());
        return recipe;
    }
}

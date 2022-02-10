package com.example.recipes.core.mappers;

import org.springframework.stereotype.Component;
import com.example.recipes.api.dtos.RecipeResponseDto;
import com.example.recipes.core.dtos.RecipeDto;
import com.example.recipes.core.entities.Recipe;
import com.example.recipes.core.entities.User;
import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public class RecipeMapper {

    public static Recipe toRecipe(RecipeDto recipeDto, long userId) {
        StringBuilder sbIngredients = new StringBuilder();
        for(int i = 0; i<recipeDto.getIngredients().length; i++)
        {
            sbIngredients.append(recipeDto.getIngredients()[i]);
            sbIngredients.append(",, ");
        }
        StringBuilder sbDirections = new StringBuilder();
        for(int i = 0; i<recipeDto.getDirections().length; i++)
        {
            sbDirections.append(recipeDto.getDirections()[i]);
            sbDirections.append(",, ");
        }
        return new Recipe(recipeDto.getId(), recipeDto.getName(), recipeDto.getCategory(), LocalDateTime.now() , recipeDto.getDescription(),
                sbIngredients.toString(),sbDirections.toString(), new User(userId, "", "", ""));
    }

    public static RecipeDto toRecipeDto(Recipe recipe) {
        return new RecipeDto(recipe.getId(), recipe.getName(), recipe.getCategory(), recipe.getDescription(),
                recipe.getIngredients().split(",, "), recipe.getDirections().split(",, "));

    }

    public static RecipeResponseDto toRecipeResponseDto(Recipe recipe) {
        return new RecipeResponseDto(recipe.getName(), recipe.getCategory(), recipe.getDate(), recipe.getDescription(),
                Arrays.asList(recipe.getIngredients().split(",, ")), Arrays.asList(recipe.getDirections().split(",, ")));

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

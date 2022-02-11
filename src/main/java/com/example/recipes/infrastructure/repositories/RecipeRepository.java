package com.example.recipes.infrastructure.repositories;

import org.springframework.data.repository.CrudRepository;
import com.example.recipes.core.entities.Recipe;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    List<Recipe> findByCategoryIgnoreCaseOrderByDateDesc(String category);

    List<Recipe> findByNameContainsIgnoreCaseOrderByDateDesc(String str);
}

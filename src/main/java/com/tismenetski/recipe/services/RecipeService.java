package com.tismenetski.recipe.services;

import com.tismenetski.recipe.commands.RecipeCommand;
import com.tismenetski.recipe.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();
    Recipe findById(Long l);
    RecipeCommand saveRecipeCommand(RecipeCommand command);
    RecipeCommand findCommandById(Long l);
    void deleteById(Long idToDelte);
}

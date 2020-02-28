package com.tismenetski.recipe.services;

import com.tismenetski.recipe.commands.IngredientCommand;
import com.tismenetski.recipe.domain.Ingredient;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
    IngredientCommand saveIngredientCommand(IngredientCommand command);
}

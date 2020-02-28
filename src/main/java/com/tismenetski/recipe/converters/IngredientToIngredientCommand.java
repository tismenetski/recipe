package com.tismenetski.recipe.converters;

import com.tismenetski.recipe.commands.IngredientCommand;
import com.tismenetski.recipe.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    private final UnitOfMeasureToUnitOfMeasureCommand uomConverter;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient ingredient) {


            if (ingredient == null) {
                return null;
            }

            IngredientCommand ingredientCommand = new IngredientCommand();
            ingredientCommand.setId(ingredient.getId());
            ingredientCommand.setAmount(ingredient.getAmount());
            ingredientCommand.setDescription(ingredient.getDescription());
            ingredientCommand.setUom(uomConverter.convert(ingredient.getUom()));
            if (ingredient.getRecipe().getId()!=null)
            {
                ingredientCommand.setRecipeId(ingredient.getRecipe().getId());
            }


            return ingredientCommand;


    }
}

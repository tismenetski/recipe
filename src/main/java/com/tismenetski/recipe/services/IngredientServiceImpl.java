package com.tismenetski.recipe.services;

import com.tismenetski.recipe.commands.IngredientCommand;
import com.tismenetski.recipe.converters.IngredientCommandToIngredient;
import com.tismenetski.recipe.converters.IngredientToIngredientCommand;
import com.tismenetski.recipe.domain.Ingredient;
import com.tismenetski.recipe.domain.Recipe;
import com.tismenetski.recipe.repositories.RecipeRepository;
import com.tismenetski.recipe.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class IngredientServiceImpl implements IngredientService {

    private final RecipeRepository recipeRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;


    public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientToIngredientCommand ingredientToIngredientCommand, UnitOfMeasureRepository unitOfMeasureRepository, IngredientCommandToIngredient ingredientCommandToIngredient) {
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId)
    {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId); // Here we extract the recipe via the recipe repository and
        // place it inside an optional recipeOptional

        if (!recipeOptional.isPresent())
        {
            //todo impl error handling
        }

        Recipe recipe=recipeOptional.get();

        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients()
                .stream().filter(ingredient -> ingredient.getId()
                .equals(ingredientId)).map(ingredient -> ingredientToIngredientCommand.convert(ingredient))
                .findFirst();
        if (!ingredientCommandOptional.isPresent())
        {
            //todo impl error handling
        }
        return  ingredientCommandOptional.get();
    }

    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand command)
    {
        Optional<Recipe> recipeOptional= recipeRepository.findById(command.getRecipeId());

        if (!recipeOptional.isPresent())
        {
            //todo Exception handling
        }

        Recipe recipe=recipeOptional.get();


        Optional<Ingredient> ingredientOptional=recipe.getIngredients()
                .stream().filter(ingredient -> ingredient.getId()
                .equals(command.getId()))
                .findFirst();


        if (ingredientOptional.isPresent())
        {
            Ingredient ingredientFound = ingredientOptional.get();
            ingredientFound.setDescription(command.getDescription());
            ingredientFound.setAmount(command.getAmount());
            ingredientFound.setUom(unitOfMeasureRepository.findById(command.getUom().getId()).orElseThrow(() -> new RuntimeException("UOM NOT FOUND")));
        }
        else
        {
            recipe.addIngredient(ingredientCommandToIngredient.convert(command));
        }

        Recipe savedRecipe=recipeRepository.save(recipe);


        return ingredientToIngredientCommand.convert(savedRecipe.getIngredients().stream().filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId())).findFirst().get());

    }
}

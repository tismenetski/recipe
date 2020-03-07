package com.tismenetski.recipe.services;

import com.tismenetski.recipe.commands.RecipeCommand;
import com.tismenetski.recipe.converters.RecipeCommandToRecipe;
import com.tismenetski.recipe.converters.RecipeToRecipeCommand;
import com.tismenetski.recipe.domain.Recipe;
import com.tismenetski.recipe.repositories.RecipeRepository;
import exceptions.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService {

        private final RecipeRepository recipeRepository;
        private final RecipeCommandToRecipe recipeCommandToRecipe;
        private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Set<Recipe> getRecipes() {

        Set<Recipe> recipeSet=new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }

    @Override
    public Recipe findById(Long l)
    {
        Optional<Recipe> recipeOptional= recipeRepository.findById(l);
        if (!recipeOptional.isPresent())
        {
            throw new NotFoundException("Recipe Not Found. For ID value: " +l.toString());
        }

        return recipeOptional.get();
    }

    @Override
    @Transactional
    public RecipeCommand findCommandById(Long l)
    {
        return  recipeToRecipeCommand.convert(findById(l));
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(command);

        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        //log.debug("Saved RecipeId:" + savedRecipe.getId());
        return recipeToRecipeCommand.convert(savedRecipe);
    }

    @Override
    public void deleteById(Long idToDelte)
    {
        recipeRepository.deleteById(idToDelte);
    }
}

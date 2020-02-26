package com.tismenetski.recipe.services;

import com.tismenetski.recipe.commands.RecipeCommand;
import com.tismenetski.recipe.converters.RecipeCommandToRecipe;
import com.tismenetski.recipe.converters.RecipeToRecipeCommand;
import com.tismenetski.recipe.domain.Recipe;
import com.tismenetski.recipe.repositories.RecipeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceIT {


    public static final String NEW_DESCRIPTION="New Description";

    @Autowired
    RecipeService recipeService;

    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    RecipeCommandToRecipe recipeCommandToRecipe;
    @Autowired
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Transactional
    @Test
    public void testSaveOfDescription() throws Exception
    {

        //given
        Iterable<Recipe> recipes=recipeRepository.findAll();
        Recipe testRecipe=recipes.iterator().next();
        RecipeCommand testRecipeCommand = recipeToRecipeCommand.convert(testRecipe);

        //when
        testRecipeCommand.setDescription(NEW_DESCRIPTION);
        RecipeCommand saveRecipeCommand = recipeService.saveRecipeCommand(testRecipeCommand);

        //then
        assertEquals(NEW_DESCRIPTION,saveRecipeCommand.getDescription());
        assertEquals(testRecipe.getId(),saveRecipeCommand.getId());
        assertEquals(testRecipe.getCategories().size(),saveRecipeCommand.getCategories().size());
        assertEquals(testRecipe.getIngredients().size(),testRecipeCommand.getIngredients().size());
    }
}

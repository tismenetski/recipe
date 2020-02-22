package com.tismenetski.recipe.repositories;

import com.tismenetski.recipe.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe,Long>
{

}

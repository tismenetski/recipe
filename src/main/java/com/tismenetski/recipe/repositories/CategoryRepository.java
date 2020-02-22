package com.tismenetski.recipe.repositories;

import com.tismenetski.recipe.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category,Long>
{


}

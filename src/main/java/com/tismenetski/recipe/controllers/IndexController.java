package com.tismenetski.recipe.controllers;

import com.tismenetski.recipe.domain.Category;
import com.tismenetski.recipe.domain.UnitOfMeasure;
import com.tismenetski.recipe.repositories.CategoryRepository;
import com.tismenetski.recipe.repositories.UnitOfMeasureRepository;
import com.tismenetski.recipe.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

private final RecipeService recipeService;


    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"","/","/index"})
    public String getIndexPage(Model model)
    {

        model.addAttribute("recipes",recipeService.getRecipes());
        return "index";
    }
}

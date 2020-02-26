package com.tismenetski.recipe.controllers;


import com.tismenetski.recipe.commands.RecipeCommand;
import com.tismenetski.recipe.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }



    @RequestMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model)
    {
        model.addAttribute("recipe",recipeService.findById(new Long(id)));

        return "recipe/show";
    }

    @RequestMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id,Model model)
    {
        model.addAttribute("recipe",recipeService.findCommandById(Long.valueOf(id)));
        return "recipe/recipeform";
    }

    @RequestMapping("/recipe/new")
    public String newRecipe(Model model)
    {
        model.addAttribute("recipe",new RecipeCommand());
        return "recipe/recipeform";
    }
    @PostMapping
    @RequestMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command)
    {
        RecipeCommand savedCommand=recipeService.saveRecipeCommand(command);

        return "redirect:/recipe/show/"+ savedCommand.getId();
    }

    @GetMapping
    @RequestMapping("recipe/{id}/delete")
    public String deleteById(@PathVariable String id)
    {
       // log.debug("deleting id"+ id);
        recipeService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }
}

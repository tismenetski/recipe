package com.tismenetski.recipe.controllers;


import com.tismenetski.recipe.commands.RecipeCommand;
import com.tismenetski.recipe.services.RecipeService;
import exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Slf4j
@Controller
public class RecipeController {

    private static final String RECIPE_RECIPEFORM_URL="recipe/recipeform";
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }



    @GetMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model)
    {
        model.addAttribute("recipe",recipeService.findById(new Long(id)));

        return "recipe/show";
    }

    @GetMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id,Model model)
    {
        model.addAttribute("recipe",recipeService.findCommandById(Long.valueOf(id)));
        return "recipe/recipeform";
    }

    @GetMapping("recipe/new")
    public String newRecipe(Model model)
    {
        model.addAttribute("recipe",new RecipeCommand());
        return "recipe/recipeform";
    }

    @PostMapping("recipe")
    public String saveOrUpdate(@Valid @ModelAttribute("recipe") RecipeCommand command, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            bindingResult.getAllErrors().forEach(objectError -> log.debug(objectError.toString()));

            return RECIPE_RECIPEFORM_URL;
        }
        RecipeCommand savedCommand=recipeService.saveRecipeCommand(command);

        return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }


    @GetMapping("recipe/{id}/delete")
    public String deleteById(@PathVariable String id)
    {
       // log.debug("deleting id"+ id);
        recipeService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }
    @ResponseStatus(HttpStatus.NOT_FOUND) // used here because when running the code the response status is actually 200 because the page exists,therefore we need to annotate this with the notFound
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception) //Holder for both Model and View in the web MVC framework. Note that these are entirely distinct. This class merely holds both to make it possible for a controller to return both model and view in a single return value.
    {
        log.error("Handling not found exception");
        log.error(exception.getMessage());

        ModelAndView modelAndView=new ModelAndView();

        modelAndView.setViewName("404error");
        modelAndView.addObject("exception",exception);

        return modelAndView;
    }


}

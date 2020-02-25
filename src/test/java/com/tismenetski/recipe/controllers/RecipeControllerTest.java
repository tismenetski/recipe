package com.tismenetski.recipe.controllers;

import com.tismenetski.recipe.domain.Recipe;
import com.tismenetski.recipe.repositories.RecipeRepository;
import com.tismenetski.recipe.services.RecipeService;
import com.tismenetski.recipe.services.RecipeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class RecipeControllerTest {


    @Mock
    RecipeService recipeService;


    RecipeController controller;

    @BeforeEach
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
        controller=new RecipeController(recipeService);
    }

    @Test
    public void testGetRecipe() throws  Exception
    {
        Recipe recipe=new Recipe();
        recipe.setId(1L);

        MockMvc mockMvc= MockMvcBuilders.standaloneSetup(controller).build();

        when(recipeService.findById(anyLong())).thenReturn(recipe);

        mockMvc.perform(get("/recipe/show/1")).andExpect(status().isOk()).andExpect(view().name("recipe/show"));
    }



}
package com.tismenetski.recipe.domain;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.event.annotation.BeforeTestExecution;

import static org.junit.jupiter.api.Assertions.*;


public class CategoryTest {

    public Category category;


    @BeforeEach
    public void setUp()
    {
        category=new Category();
    }

    @Test
    public void getId() throws Exception {

        Long idValue=4L;

        category.setId(idValue);
        assertEquals(idValue,category.getId());
    }

    @Test
   public  void getDescription() throws Exception {
    }

    @Test
   public void getRecipes() throws Exception {
    }
}
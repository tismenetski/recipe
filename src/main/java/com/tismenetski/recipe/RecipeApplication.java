package com.tismenetski.recipe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RecipeApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecipeApplication.class, args);
    }

}




/*
Recipe Repository,crud repository which gives us findById,un RecipeServiceImpl we get an optional and
make sure that the optional is indeed have values(isPresent) and return it,which goes to RecipeController which shows
the id via show.html webpage

*/


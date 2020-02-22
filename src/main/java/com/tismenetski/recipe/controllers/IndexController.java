package com.tismenetski.recipe.controllers;

import com.tismenetski.recipe.domain.Category;
import com.tismenetski.recipe.domain.UnitOfMeasure;
import com.tismenetski.recipe.repositories.CategoryRepository;
import com.tismenetski.recipe.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"","/","/index"})
    public String getIndexPage()
    {
        Optional<Category> categoryOptional=categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasureOptional=unitOfMeasureRepository.findByDescription("Teaspoon");
        System.out.println("Cat Id is:"+ categoryOptional.get().getId());
        System.out.println("UOM Id is:"+ unitOfMeasureOptional.get().getId());
        return "index";
    }
}

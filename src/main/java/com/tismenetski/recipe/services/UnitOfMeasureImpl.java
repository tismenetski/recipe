package com.tismenetski.recipe.services;

import com.tismenetski.recipe.commands.UnitOfMeasureCommand;
import com.tismenetski.recipe.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.tismenetski.recipe.repositories.RecipeRepository;
import com.tismenetski.recipe.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UnitOfMeasureImpl implements UnitOfMeasureService {

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    public UnitOfMeasureImpl(UnitOfMeasureRepository unitOfMeasureRepository, UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
    }

    @Override
    public Set<UnitOfMeasureCommand> listAllUoms()
    {
        return StreamSupport.stream(unitOfMeasureRepository.findAll().spliterator(),false).map(unitOfMeasureToUnitOfMeasureCommand::convert).collect(Collectors.toSet());

    }


}

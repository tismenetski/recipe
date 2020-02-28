package com.tismenetski.recipe.services;

import com.tismenetski.recipe.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {


    Set<UnitOfMeasureCommand> listAllUoms();
}

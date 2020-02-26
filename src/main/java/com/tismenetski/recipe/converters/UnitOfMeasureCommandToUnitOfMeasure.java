package com.tismenetski.recipe.converters;

import com.tismenetski.recipe.commands.UnitOfMeasureCommand;
import com.tismenetski.recipe.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.hibernate.annotations.Synchronize;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;


@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand source)
    {


            if (source==null)
            {
                return null;
            }
            final UnitOfMeasure uom = new UnitOfMeasure();
            uom.setId(source.getId());
            uom.setDescription(source.getDescription());
            return uom;

    }


}

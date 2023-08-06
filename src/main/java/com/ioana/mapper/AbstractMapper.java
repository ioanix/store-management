package com.ioana.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractMapper<Model, DTO> implements Mapper<Model, DTO> {

    @Override
    public List<Model> convertDtosToModels(Collection<DTO> dtos) {

        return dtos.stream()
                .map(this::convertDtoToModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<DTO> convertModelsToDtos(Collection<Model> models) {

        return models.stream()
                .map(this::convertModelToDto)
                .collect(Collectors.toList());
    }
}

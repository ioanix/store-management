package com.ioana.mapper;

import java.util.Collection;
import java.util.List;

public interface Mapper<Model, DTO> {

    Model convertDtoToModel(DTO dto);

    DTO convertModelToDto(Model model);

    List<Model> convertDtosToModels(Collection<DTO> dtos);

    List<DTO> convertModelsToDtos(Collection<Model> models);
}

package org.dinuka.foodcatalogue.util;

import org.dinuka.foodcatalogue.dto.FoodItemDto;
import org.dinuka.foodcatalogue.modal.FoodItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface FoodItemMapper {

    FoodItemMapper INSTANCE = Mappers.getMapper(FoodItemMapper.class);

    FoodItemDto toDto(FoodItem foodItem);
    FoodItem toEntity(FoodItemDto foodItemDto);

    List<FoodItemDto> toDtoList(List<FoodItem> foodItems);
    List<FoodItem> toEntityList(List<FoodItemDto> foodItemDtos);
}

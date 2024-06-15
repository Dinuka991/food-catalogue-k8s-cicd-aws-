package org.dinuka.foodcatalogue.controller;

import org.dinuka.foodcatalogue.dto.FoodItemDto;
import org.dinuka.foodcatalogue.modal.FoodItem;
import org.dinuka.foodcatalogue.service.FoodItemService;
import org.dinuka.foodcatalogue.util.FoodItemMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/user")
public class FoodItemController {

    private final FoodItemService foodItemService;

    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService =  foodItemService;
    }

    @GetMapping("/save")
    ResponseEntity<FoodItemDto> save(@RequestBody FoodItemDto foodItemDto) {
        FoodItem foodItem = FoodItemMapper.INSTANCE.toEntity(foodItemDto);
        return ResponseEntity.ok(FoodItemMapper.INSTANCE.toDto(foodItemService.save(foodItem)));
    }

    @GetMapping("/all")
    ResponseEntity<List<FoodItemDto>> getAllUsers() {
        return ResponseEntity.ok( foodItemService.getAll().stream()
                .map(FoodItemMapper.INSTANCE::toDto).collect(Collectors.toList())) ;
    }

    @GetMapping("/get/{id}")
    ResponseEntity<FoodItemDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(FoodItemMapper.INSTANCE.toDto(foodItemService.getFooodItemById(id)));
    }

}

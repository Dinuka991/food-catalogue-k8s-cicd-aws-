package org.dinuka.foodcatalogue.controller;

import org.dinuka.foodcatalogue.dto.FoodItemDto;
import org.dinuka.foodcatalogue.dto.FoodItemCatalogue;
import org.dinuka.foodcatalogue.modal.FoodItem;
import org.dinuka.foodcatalogue.service.FoodItemService;
import org.dinuka.foodcatalogue.util.FoodItemMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/item")
public class FoodItemController {

    private final FoodItemService foodItemService;

    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    @PostMapping("/save")
    public ResponseEntity<FoodItemDto> addFoodItem(@RequestBody FoodItemDto foodItemDto) {
        FoodItem foodItem = FoodItemMapper.INSTANCE.toEntity(foodItemDto);
        FoodItem savedFoodItem = foodItemService.save(foodItem);
        FoodItemDto savedFoodItemDto = FoodItemMapper.INSTANCE.toDto(savedFoodItem);
        return ResponseEntity.ok(savedFoodItemDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<FoodItemDto>> getAllUsers() {
        List<FoodItemDto> foodItemDtos = foodItemService.getAll().stream()
                .map(FoodItemMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(foodItemDtos);
    }

    @GetMapping("/getFoodDetails/{id}")
    public CompletableFuture<ResponseEntity<FoodItemDto>> getFoodDetails(@PathVariable Long id) {
        return foodItemService.getFoodItemByRestaurantIdAsync(id)
                .thenApply(foodItemDtos -> {
                    if (foodItemDtos.isEmpty()) {
                        return ResponseEntity.notFound().build();
                    }
                    return ResponseEntity.ok(foodItemDtos.get(0));
                });
    }

    @GetMapping("/getFoodCatalogue/{id}")
    public CompletableFuture<ResponseEntity<FoodItemCatalogue>> getFoodCatalogue(@PathVariable Long id) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                FoodItemCatalogue foodItemCatalogue = foodItemService.getFoodCatalogue(id);
                return ResponseEntity.ok(foodItemCatalogue);
            } catch (Exception e) {
                throw new RuntimeException("Error fetching food catalogue", e);
            }
        });
    }
}

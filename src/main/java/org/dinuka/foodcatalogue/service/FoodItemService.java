package org.dinuka.foodcatalogue.service;


import org.dinuka.foodcatalogue.exception.FoodItemNotFoundException;
import org.dinuka.foodcatalogue.modal.FoodItem;
import org.dinuka.foodcatalogue.repository.FoodItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodItemService {

    private final FoodItemRepository foodItemRepository;

    public FoodItemService(FoodItemRepository foodItemRepository) {
        this.foodItemRepository = foodItemRepository;
    }

    public FoodItem save(FoodItem foodItem) {
        return foodItemRepository.save(foodItem);
    }

    public List<FoodItem> getAll() {
        return foodItemRepository.findAll();
    }

    public  FoodItem getFooodItemById(Long id)  {
        return foodItemRepository.findById(id).orElseThrow(() -> new FoodItemNotFoundException("User with id " + id + " not found"));
    }
}
package org.dinuka.foodcatalogue.service;

import org.dinuka.foodcatalogue.dto.FoodItemCatalogue;
import org.dinuka.foodcatalogue.dto.FoodItemDto;
import org.dinuka.foodcatalogue.dto.RestaurantDto;
import org.dinuka.foodcatalogue.exception.FoodItemNotFoundException;
import org.dinuka.foodcatalogue.modal.FoodItem;
import org.dinuka.foodcatalogue.repository.FoodItemRepository;
import org.dinuka.foodcatalogue.util.FoodItemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class FoodItemService {

    private static final Logger logger = LoggerFactory.getLogger(FoodItemService.class);

    private final FoodItemRepository foodItemRepository;
    private final RestTemplate restTemplate;

    public FoodItemService(FoodItemRepository foodItemRepository, RestTemplate restTemplate) {
        this.foodItemRepository = foodItemRepository;
        this.restTemplate = restTemplate;
    }

    @Transactional
    public FoodItem save(@Valid FoodItem foodItem) {
        logger.info("Saving food item: {}", foodItem);
        return foodItemRepository.save(foodItem);
    }

    public List<FoodItem> getAll() {
        logger.info("Fetching all food items");
        return foodItemRepository.findAll();
    }

    public FoodItem getFoodItemById(Long id) {
        logger.info("Fetching food item by id: {}", id);
        return foodItemRepository.findById(id)
                .orElseThrow(() -> new FoodItemNotFoundException("Food item with id " + id + " not found"));
    }

    @Async
    public CompletableFuture<List<FoodItemDto>> getFoodItemByRestaurantIdAsync(Long id) {
        logger.info("Fetching food items by restaurant id: {}", id);
        List<FoodItem> foodItems = foodItemRepository.findByRestaurantId(id);
        return CompletableFuture.completedFuture(FoodItemMapper.INSTANCE.toDtoList(foodItems));
    }

    @Async
    public CompletableFuture<RestaurantDto> getRestaurantByIdAsync(Long id) {
        logger.info("Fetching restaurant by id: {}", id);
        try {
            RestaurantDto restaurantDto = restTemplate.getForObject("http://localhost:8080/api/v1/restaurant/get/" + id, RestaurantDto.class);
            return CompletableFuture.completedFuture(restaurantDto);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            logger.error("Error while fetching restaurant: {}", e.getStatusCode());
            throw new RuntimeException("Error while fetching restaurant: " + e.getStatusCode());
        } catch (ResourceAccessException e) {
            logger.error("Error while fetching restaurant: {}", e.getMessage());
            throw new RuntimeException("Error while fetching restaurant: " + e.getMessage());
        }
    }

    public FoodItemCatalogue getFoodCatalogue(Long id) throws Exception {
        logger.info("Fetching food catalogue for restaurant id: {}", id);
        CompletableFuture<List<FoodItemDto>> foodItemsFuture = getFoodItemByRestaurantIdAsync(id);
        CompletableFuture<RestaurantDto> restaurantFuture = getRestaurantByIdAsync(id);

        CompletableFuture.allOf(foodItemsFuture, restaurantFuture).join();

        FoodItemCatalogue foodItemCatalogue = new FoodItemCatalogue();
        foodItemCatalogue.setFoodItems(foodItemsFuture.get());
        foodItemCatalogue.setRestaurantDto(restaurantFuture.get());

        return foodItemCatalogue;
    }
}

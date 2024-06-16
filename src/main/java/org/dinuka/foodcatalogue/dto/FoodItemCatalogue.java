package org.dinuka.foodcatalogue.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FoodItemCatalogue{
       private  List<FoodItemDto> foodItems;
       private RestaurantDto restaurantDto;

    @Override
    public String toString() {
        return "FoodItemCatalogue{" +
                "foodItems=" + foodItems +
                ", restaurantDto=" + restaurantDto +
                '}';
    }
}

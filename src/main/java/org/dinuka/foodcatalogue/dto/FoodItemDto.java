package org.dinuka.foodcatalogue.dto;

public record FoodItemDto(
         Long id ,
         String name ,
         String description ,
         boolean isVeg ,
         double price ,
         Long restaurantId ,
         Integer quantity
) {
    @Override
    public String toString() {
        return "FoodItemDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isVeg=" + isVeg +
                ", price=" + price +
                ", restaurantId=" + restaurantId +
                ", quantity=" + quantity +
                '}';
    }
}

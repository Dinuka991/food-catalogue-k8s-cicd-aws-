package org.dinuka.foodcatalogue.dto;

public record FoodItemDto(
         Long id,
         String username,
         String email
) {
    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", userName='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

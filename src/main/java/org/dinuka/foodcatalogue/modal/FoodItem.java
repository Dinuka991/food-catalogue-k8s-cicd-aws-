package org.dinuka.foodcatalogue.modal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "food_item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodItem {

    @Id
    private Long id;
     private String name;
    private String description;
    private boolean isVeg;
    private double price;
    private Long restaurantId;
    private  Integer quantity;


}

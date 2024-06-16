package org.dinuka.foodcatalogue.repository;


import org.dinuka.foodcatalogue.modal.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, Long>{

    List<FoodItem> findByRestaurantId(Long id);
}

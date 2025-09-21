package com.smartgrocery.smart_grocery.repository;

import com.smartgrocery.smart_grocery.entity.GroceryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface GroceryItemRepository extends JpaRepository<GroceryItem, Long> {
    List<GroceryItem> findByUserId(Long userId);
    List<GroceryItem> findByUserIdAndIngredientId(Long userId, Long ingredientId);
    List<GroceryItem> findByUserIdAndExpirationDateBefore(Long userId, LocalDate date);
}

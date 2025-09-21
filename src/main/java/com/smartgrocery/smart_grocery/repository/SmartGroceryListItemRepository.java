package com.smartgrocery.smart_grocery.repository;

import com.smartgrocery.smart_grocery.entity.SmartGroceryListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.smartgrocery.smart_grocery.entity.Reason;

import java.util.List;

@Repository
public interface SmartGroceryListItemRepository extends JpaRepository<SmartGroceryListItem, Long> {
    List<SmartGroceryListItem> findByUserId(Long userId);
    List<SmartGroceryListItem> findByUserIdAndReason(Long userId, Reason reason);
}

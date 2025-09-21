package com.smartgrocery.smart_grocery.repository;

import com.smartgrocery.smart_grocery.entity.UserRecipeInteraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.smartgrocery.smart_grocery.entity.InteractionType;


import java.util.List;
import java.util.Optional;

@Repository
public interface UserRecipeInteractionRepository extends JpaRepository<UserRecipeInteraction, Long> {
    List<UserRecipeInteraction> findByUserIdAndType(Long userId, InteractionType type);
    List<UserRecipeInteraction> findByUserId(Long userId);
    Optional<UserRecipeInteraction> findByUserIdAndRecipeId(Long userId, Long recipeId);
}

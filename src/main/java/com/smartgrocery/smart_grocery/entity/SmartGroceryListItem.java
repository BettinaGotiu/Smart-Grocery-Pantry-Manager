package com.smartgrocery.smart_grocery.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "smart_grocery_list_items")
public class SmartGroceryListItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // If linked to something already in pantry
    @ManyToOne
    @JoinColumn(name = "grocery_item_id")
    private GroceryItem groceryItem;

    // Otherwise: free-text name + qty
    private String name;
    private Double quantity;
    private String unit;

    @Enumerated(EnumType.STRING)
    private Reason reason; // expired, missing, manual, recipe-needed

    private String imageUrl; // optional, could fallback from groceryItem.ingredient.imageUrl

    @CreationTimestamp
    private LocalDateTime createdAt;
}

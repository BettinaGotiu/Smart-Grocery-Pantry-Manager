package com.smartgrocery.smart_grocery.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false, name = "password_hash")
    private String passwordHash;

    @OneToOne
    @JoinColumn(name = "profile_photo_id")
    private Photo profilePicture;

    @CreationTimestamp
    private LocalDateTime createdAt;

    // Relationships
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<GroceryItem> groceryItems;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SmartGroceryListItem> groceryListItems;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserRecipeInteraction> recipeInteractions;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Photo getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Photo profilePicture) {
        this.profilePicture = profilePicture;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Set<GroceryItem> getGroceryItems() {
        return groceryItems;
    }

    public void setGroceryItems(Set<GroceryItem> groceryItems) {
        this.groceryItems = groceryItems;
    }

    public Set<SmartGroceryListItem> getGroceryListItems() {
        return groceryListItems;
    }

    public void setGroceryListItems(Set<SmartGroceryListItem> groceryListItems) {
        this.groceryListItems = groceryListItems;
    }

    public Set<UserRecipeInteraction> getRecipeInteractions() {
        return recipeInteractions;
    }

    public void setRecipeInteractions(Set<UserRecipeInteraction> recipeInteractions) {
        this.recipeInteractions = recipeInteractions;
    }
}

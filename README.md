# Smart Grocery & Pantry Manager

## Idea & Scope
Smart Grocery & Pantry Manager is a web application designed to help users efficiently manage their pantry and shopping lists. The app allows users to track groceries, plan recipes based on available ingredients, and maintain a personalized shopping list. The focus is on **reducing food waste** and **simplifying meal planning**.

This initial version provides:
- User management with authentication.
- Pantry management: adding and tracking grocery items.
- Recipe management: storing recipes and ingredients.
- Smart shopping list: items that need to be purchased.

---

## Technical Features

### Backend
- **Spring Boot**: Provides RESTful APIs for all application functionality.
- **Spring Data JPA**: ORM to interact with the PostgreSQL database.
- **Spring Security**: Handles user authentication and password security.
- **Hibernate**: Manages entity relationships and database schema.

### Database
- **PostgreSQL**: Stores all application data.
- Fully normalized database schema in **3NF**:
  - Users
  - Ingredients
  - Grocery Items (user pantry)
  - Recipes
  - Recipe Ingredients
  - User-Recipe Interactions
  - Smart Grocery List Items

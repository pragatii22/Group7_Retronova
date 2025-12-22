package Controller;

import dao.CartDao;
import java.util.List;
import model.CartItem;

/**
 * Controller class for managing Cart operations
 * @author Retronova Team
 */
public class CartController {
    
    private CartDao cartDao;
    private int currentUserId;
    
    /**
     * Constructor with user ID
     * @param userId The current logged-in user's ID
     */
    public CartController(int userId) {
        this.cartDao = new CartDao();
        this.currentUserId = userId;
    }
    
    /**
     * Default constructor (assumes user ID 1 for testing)
     */
    public CartController() {
        this.cartDao = new CartDao();
        this.currentUserId = 1; // Default user for testing
    }
    
    /**
     * Set the current user ID
     * @param userId The user ID to set
     */
    public void setCurrentUserId(int userId) {
        this.currentUserId = userId;
    }
    
    /**
     * Get the current user ID
     * @return The current user ID
     */
    public int getCurrentUserId() {
        return currentUserId;
    }
    
    /**
     * Add an item to the cart
     * @param productId The product ID
     * @param productName The product name
     * @param description The product description
     * @param price The product price
     * @param quantity The quantity to add
     * @param size The size of the product
     * @param color The color of the product
     * @param imagePath The path to the product image
     * @return true if successful, false otherwise
     */
    public boolean addItemToCart(int productId, String productName, String description, 
                                 double price, int quantity, String size, String color, String imagePath) {
        try {
            CartItem item = new CartItem(currentUserId, productId, productName, description, 
                                        price, quantity, size, color, imagePath);
            boolean success = cartDao.addToCart(item);
            
            if (success) {
                System.out.println("Item added to cart successfully: " + productName);
            } else {
                System.err.println("Failed to add item to cart: " + productName);
            }
            
            return success;
        } catch (Exception e) {
            System.err.println("Error in addItemToCart: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Add an item to the cart using CartItem object
     * @param item The cart item to add
     * @return true if successful, false otherwise
     */
    public boolean addItemToCart(CartItem item) {
        try {
            item.setUserId(currentUserId);
            boolean success = cartDao.addToCart(item);
            
            if (success) {
                System.out.println("Item added to cart successfully: " + item.getProductName());
            } else {
                System.err.println("Failed to add item to cart: " + item.getProductName());
            }
            
            return success;
        } catch (Exception e) {
            System.err.println("Error in addItemToCart: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Get all items in the cart for the current user
     * @return List of cart items
     */
    public List<CartItem> getCartItems() {
        try {
            return cartDao.getCartItems(currentUserId);
        } catch (Exception e) {
            System.err.println("Error retrieving cart items: " + e.getMessage());
            e.printStackTrace();
            return new java.util.ArrayList<>();
        }
    }
    
    /**
     * Update the quantity of a cart item
     * @param productId The product ID
     * @param newQuantity The new quantity
     * @return true if successful, false otherwise
     */
    public boolean updateItemQuantity(int productId, int newQuantity) {
        if (newQuantity <= 0) {
            System.err.println("Quantity must be greater than 0");
            return false;
        }
        
        try {
            return cartDao.updateCartItemQuantity(currentUserId, productId, newQuantity, false);
        } catch (Exception e) {
            System.err.println("Error updating item quantity: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Increment the quantity of a cart item
     * @param productId The product ID
     * @param quantityToAdd The quantity to add
     * @return true if successful, false otherwise
     */
    public boolean incrementItemQuantity(int productId, int quantityToAdd) {
        if (quantityToAdd <= 0) {
            System.err.println("Quantity to add must be greater than 0");
            return false;
        }
        
        try {
            return cartDao.updateCartItemQuantity(currentUserId, productId, quantityToAdd, true);
        } catch (Exception e) {
            System.err.println("Error incrementing item quantity: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Remove an item from the cart
     * @param cartItemId The cart item ID to remove
     * @return true if successful, false otherwise
     */
    public boolean removeItemFromCart(int cartItemId) {
        try {
            boolean success = cartDao.removeFromCart(currentUserId, cartItemId);
            
            if (success) {
                System.out.println("Item removed from cart successfully");
            } else {
                System.err.println("Failed to remove item from cart");
            }
            
            return success;
        } catch (Exception e) {
            System.err.println("Error removing item from cart: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Clear all items from the cart
     * @return true if successful, false otherwise
     */
    public boolean clearCart() {
        try {
            boolean success = cartDao.clearCart(currentUserId);
            
            if (success) {
                System.out.println("Cart cleared successfully");
            } else {
                System.err.println("Failed to clear cart");
            }
            
            return success;
        } catch (Exception e) {
            System.err.println("Error clearing cart: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Get the total number of items in the cart
     * @return The count of cart items
     */
    public int getCartItemCount() {
        try {
            return cartDao.getCartItemCount(currentUserId);
        } catch (Exception e) {
            System.err.println("Error getting cart item count: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }
    
    /**
     * Calculate the total price of all items in the cart
     * @return The total price
     */
    public double getCartTotal() {
        try {
            return cartDao.getCartTotal(currentUserId);
        } catch (Exception e) {
            System.err.println("Error calculating cart total: " + e.getMessage());
            e.printStackTrace();
            return 0.0;
        }
    }
    
    /**
     * Check if a product is already in the cart
     * @param productId The product ID
     * @return true if product is in cart, false otherwise
     */
    public boolean isProductInCart(int productId) {
        try {
            return cartDao.isProductInCart(currentUserId, productId);
        } catch (Exception e) {
            System.err.println("Error checking if product in cart: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}

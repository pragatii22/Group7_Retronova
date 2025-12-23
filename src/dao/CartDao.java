package dao;

import database.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.CartItem;

/**
 * Data Access Object for Cart operations
 * @author Retronova Team
 */
public class CartDao {
    
    private MySqlConnection mysql = new MySqlConnection();
    
    /**
     * Get all cart items for a specific user
     * @param userId The ID of the user
     * @return List of cart items
     */
    public List<CartItem> getCartItems(int userId) {
        List<CartItem> items = new ArrayList<>();
        Connection conn = mysql.openConnection();
        String sql = "SELECT id, user_id, product_id, product_name, description, price, quantity, size, color, image_path " +
                     "FROM cart WHERE user_id = ?";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, userId);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int userIdDb = rs.getInt("user_id");
                int productId = rs.getInt("product_id");
                String productName = rs.getString("product_name");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String size = rs.getString("size");
                String color = rs.getString("color");
                String imagePath = rs.getString("image_path");
                
                items.add(new CartItem(id, userIdDb, productId, productName, description, 
                                      price, quantity, size, color, imagePath));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving cart items: " + e.getMessage());
            e.printStackTrace();
        } finally {
            mysql.closeConnection(conn);
        }
        return items;
    }
    
    /**
     * Add an item to the cart
     * @param item The cart item to add
     * @return true if successful, false otherwise
     */
    public boolean addToCart(CartItem item) {
        Connection conn = mysql.openConnection();
        
        // First check if the product already exists in the cart
        if (isProductInCart(item.getUserId(), item.getProductId())) {
            // Update quantity instead of adding duplicate
            return updateCartItemQuantity(item.getUserId(), item.getProductId(), 
                                         item.getQuantity(), true);
        }
        
        String sql = "INSERT INTO cart (user_id, product_id, product_name, description, price, quantity, size, color, image_path) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, item.getUserId());
            pstm.setInt(2, item.getProductId());
            pstm.setString(3, item.getProductName());
            pstm.setString(4, item.getDescription());
            pstm.setDouble(5, item.getPrice());
            pstm.setInt(6, item.getQuantity());
            pstm.setString(7, item.getSize());
            pstm.setString(8, item.getColor());
            pstm.setString(9, item.getImagePath());
            
            int rowsAffected = pstm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error adding item to cart: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }
    
    /**
     * Check if a product is already in the cart
     * @param userId The user ID
     * @param productId The product ID
     * @return true if product exists in cart, false otherwise
     */
    public boolean isProductInCart(int userId, int productId) {
        Connection conn = mysql.openConnection();
        String sql = "SELECT COUNT(*) FROM cart WHERE user_id = ? AND product_id = ?";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, userId);
            pstm.setInt(2, productId);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error checking if product in cart: " + e.getMessage());
            e.printStackTrace();
        } finally {
            mysql.closeConnection(conn);
        }
        return false;
    }
    
    /**
     * Update the quantity of a cart item
     * @param userId The user ID
     * @param productId The product ID
     * @param quantity The new quantity (or quantity to add if isIncrement is true)
     * @param isIncrement If true, adds to existing quantity; if false, sets absolute quantity
     * @return true if successful, false otherwise
     */
    public boolean updateCartItemQuantity(int userId, int productId, int quantity, boolean isIncrement) {
        Connection conn = mysql.openConnection();
        String sql;
        if (isIncrement) {
            sql = "UPDATE cart SET quantity = quantity + ? WHERE user_id = ? AND product_id = ?";
        } else {
            sql = "UPDATE cart SET quantity = ? WHERE user_id = ? AND product_id = ?";
        }
        
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, quantity);
            pstm.setInt(2, userId);
            pstm.setInt(3, productId);
            
            int rowsAffected = pstm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error updating cart item quantity: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }
    
    /**
     * Remove an item from the cart by cart item ID
     * @param userId The user ID
     * @param cartItemId The cart item ID to remove
     * @return true if successful, false otherwise
     */
    public boolean removeFromCart(int userId, int cartItemId) {
        Connection conn = mysql.openConnection();
        String sql = "DELETE FROM cart WHERE user_id = ? AND id = ?";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, userId);
            pstm.setInt(2, cartItemId);
            
            int rowsAffected = pstm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error removing item from cart: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }
    
    /**
     * Remove all items from the cart for a specific user
     * @param userId The user ID
     * @return true if successful, false otherwise
     */
    public boolean clearCart(int userId) {
        Connection conn = mysql.openConnection();
        String sql = "DELETE FROM cart WHERE user_id = ?";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, userId);
            
            int rowsAffected = pstm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error clearing cart: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }
    
    /**
     * Get the total number of items in the cart
     * @param userId The user ID
     * @return The count of cart items
     */
    public int getCartItemCount(int userId) {
        Connection conn = mysql.openConnection();
        String sql = "SELECT SUM(quantity) FROM cart WHERE user_id = ?";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, userId);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Error getting cart item count: " + e.getMessage());
            e.printStackTrace();
        } finally {
            mysql.closeConnection(conn);
        }
        return 0;
    }
    
    /**
     * Calculate the total price of all items in the cart
     * @param userId The user ID
     * @return The total price
     */
    public double getCartTotal(int userId) {
        Connection conn = mysql.openConnection();
        String sql = "SELECT SUM(price * quantity) as total FROM cart WHERE user_id = ?";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, userId);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return rs.getDouble("total");
            }
        } catch (SQLException e) {
            System.err.println("Error calculating cart total: " + e.getMessage());
            e.printStackTrace();
        } finally {
            mysql.closeConnection(conn);
        }
        return 0.0;
    }
}

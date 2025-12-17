package dao;

import database.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.WishListItem;

/**
 *
 * @author Hp
 */
public class WishListdao {
    
    private MySqlConnection mysql = new MySqlConnection();
    
    public List<WishListItem> getWishListItems(int userId) {
        List<WishListItem> items = new ArrayList<>();
        Connection conn = mysql.openConnection();
        String sql = "SELECT id, item_name, description, price, image_path FROM wishlist WHERE user_id = ?";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, userId);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String itemName = rs.getString("item_name");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                String imagePath = rs.getString("image_path");
                items.add(new WishListItem(id, itemName, description, price, imagePath));
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            mysql.closeConnection(conn);
        }
        return items;
    }
    
    public boolean addToWishList(int userId, WishListItem item) {
        Connection conn = mysql.openConnection();
        String sql = "INSERT INTO wishlist (user_id, item_name, description, price, image_path) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, userId);
            pstm.setString(2, item.getItemName());
            pstm.setString(3, item.getDescription());
            pstm.setDouble(4, item.getPrice());
            pstm.setString(5, item.getImagePath());
            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            mysql.closeConnection(conn);
        }
        return false;
    }
    
    public boolean removeFromWishList(int userId, int itemId) {
        Connection conn = mysql.openConnection();
        String sql = "DELETE FROM wishlist WHERE user_id = ? AND id = ?";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, userId);
            pstm.setInt(2, itemId);
            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            mysql.closeConnection(conn);
        }
        return false;
    }
    
    public boolean removeAllFromWishList(int userId) {
        Connection conn = mysql.openConnection();
        String sql = "DELETE FROM wishlist WHERE user_id = ?";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, userId);
            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            mysql.closeConnection(conn);
        }
        return false;
    }
}

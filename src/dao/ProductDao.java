/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */




/**
 *
 * @author Acer
 */
package dao;

import database.MySqlConnection;
import model.product;
import model.Categorys;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    
    MySqlConnection mysql = new MySqlConnection();

    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/retronova";
        String user = "root";
        String password = "your_password";
        System.out.println("[ProductDao] Connecting to: " + url);
        return DriverManager.getConnection(url, user, password);
    }

    //Deepak le add gareko 
    // ================= GET ALL ITEMS =================
    public List<product> getAllItems() {
        List<product> list = new ArrayList<>();
        String sql = "SELECT * FROM products";

        try (Connection con = mysql.openConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                product item = new product(
                        rs.getInt("productId"), // must be similar to database ko column ko name
                        rs.getString("item_name"),
                        rs.getDouble("price"),
                        rs.getString("imagePath"),
                        rs.getString("categoryId")
                       
                );
                list.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    
    // fetch all products with category
    public List<product> getAllProducts() {
        List<product> products = new ArrayList<>();
        String sql = "SELECT p.type, p.price, p.image_path, c.category_id, c.category AS category " +
                     "FROM product p " +
                     "JOIN categorys c ON p.category_id = c.category_id";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("[ProductDao] ✓ Query executed");
            int count = 0;
            
            while (rs.next()) {
                Categorys cat = new Categorys(
                    rs.getInt("category_id"),
                    rs.getString("category")
                );

                product prod = new product(
                    rs.getString("type"),
                    rs.getDouble("price"),
                    rs.getString("image_path"),
                    rs.getString("categoryId")
                );
                
                System.out.println("[ProductDao] → " + prod.getType() + " | Image: " + prod.getImagePath());
                products.add(prod);
                count++;
            }
            
            System.out.println("[ProductDao] ✓ Total products: " + count);

        } catch (SQLException e) {
            System.err.println("[ProductDao] ✗ Error: " + e.getMessage());
            e.printStackTrace();
        }

        return products;
    }
}

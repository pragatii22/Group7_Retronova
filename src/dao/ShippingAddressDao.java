/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.ShippingAddressData;

/**
 *
 * @author Hp
 */
public class ShippingAddressDao {
    
    private MySqlConnection mysql = new MySqlConnection();
    
    public List<ShippingAddressData> getAddresses(int userId) {
        List<ShippingAddressData> addresses = new ArrayList<>();
        Connection conn = mysql.openConnection();
        String sql = "SELECT id, user_id, address_line1, address_line2, city, state, zip_code, country, phone FROM shipping_addresses WHERE user_id = ?";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, userId);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                ShippingAddressData address = new ShippingAddressData(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getString("address_line1"),
                    rs.getString("address_line2"),
                    rs.getString("city"),
                    rs.getString("state"),
                    rs.getString("zip_code"),
                    rs.getString("country"),
                    rs.getString("phone")
                );
                addresses.add(address);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            mysql.closeConnection(conn);
        }
        return addresses;
    }
    
    public boolean addAddress(ShippingAddressData address) {
        Connection conn = mysql.openConnection();
        String sql = "INSERT INTO shipping_addresses (user_id, address_line1, address_line2, city, state, zip_code, country, phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, address.getUserId());
            pstm.setString(2, address.getAddressLine1());
            pstm.setString(3, address.getAddressLine2());
            pstm.setString(4, address.getCity());
            pstm.setString(5, address.getState());
            pstm.setString(6, address.getZipCode());
            pstm.setString(7, address.getCountry());
            pstm.setString(8, address.getPhone());
            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            mysql.closeConnection(conn);
        }
        return false;
    }
    
    public boolean updateAddress(ShippingAddressData address) {
        Connection conn = mysql.openConnection();
        String sql = "UPDATE shipping_addresses SET address_line1 = ?, address_line2 = ?, city = ?, state = ?, zip_code = ?, country = ?, phone = ? WHERE id = ? AND user_id = ?";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, address.getAddressLine1());
            pstm.setString(2, address.getAddressLine2());
            pstm.setString(3, address.getCity());
            pstm.setString(4, address.getState());
            pstm.setString(5, address.getZipCode());
            pstm.setString(6, address.getCountry());
            pstm.setString(7, address.getPhone());
            pstm.setInt(8, address.getId());
            pstm.setInt(9, address.getUserId());
            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            mysql.closeConnection(conn);
        }
        return false;
    }
    
    public boolean deleteAddress(int addressId, int userId) {
        Connection conn = mysql.openConnection();
        String sql = "DELETE FROM shipping_addresses WHERE id = ? AND user_id = ?";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, addressId);
            pstm.setInt(2, userId);
            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            mysql.closeConnection(conn);
        }
        return false;
    }
}

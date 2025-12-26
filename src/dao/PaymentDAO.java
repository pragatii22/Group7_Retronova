/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.Database;
import database.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Payment;

/**
 *
 * @author Hp
 */
public class PaymentDAO {
    private Database db = new MySqlConnection();

    public boolean savePayment(Payment payment) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean success = false;
        try {
            conn = db.openConnection();
            if (conn != null) {
                String sql = "INSERT INTO payments (order_id, amount, payment_method, payment_status) VALUES (?, ?, ?, ?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, payment.getOrderId());
                pstmt.setDouble(2, payment.getAmount());
                pstmt.setString(3, payment.getPaymentMethod());
                pstmt.setString(4, payment.getPaymentStatus());
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    success = true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error saving payment: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing PreparedStatement: " + e.getMessage());
            }
            if (conn != null) {
                db.closeConnection(conn);
            }
        }
        return success;
    }
}

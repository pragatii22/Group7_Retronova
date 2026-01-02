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
import model.LoginData;


/**
 *
 * @author Hp
 */



public class LoginDao {

    MySqlConnection mysql = new MySqlConnection() {};

    public boolean login(LoginData user) {
        Connection conn = mysql.openConnection();
        if (conn == null) {
            System.err.println("[LoginDao] DB connection unavailable");
            return false;
        }

        // Accept username OR email for login
        String sql = "SELECT * FROM users WHERE (username = ? OR email = ?) AND password = ?";

        try (PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, user.getUsername());
            pstm.setString(2, user.getUsername());
            pstm.setString(3, user.getPassword());
            System.out.println("DB CHECK → " + user.getUsername() + " | " + user.getPassword());


            ResultSet rs = pstm.executeQuery();
            return rs.next(); // true = valid login

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mysql.closeConnection(conn);
        }
        return false;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


import database.MySqlConnection;
import java.sql.*;
import model.Userdata;

/**
 *
 * @author Acer
 */
public class UserDao {
    MySqlConnection mysql = new MySqlConnection();
        
    public void signUp(Userdata user){
        Connection conn = mysql.openConnection();

        String sql = "INSERT INTO users (full_name, username, email, password) VALUES (?,?,?,?)";

        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, user.getFullName());
            pstm.setString(2, user.getUserName());
            pstm.setString(3, user.getEmail());
            pstm.setString(4, user.getPassword()); // store only password
            int rows = pstm.executeUpdate();
            System.out.println("Rows inserted: " + rows);
        } catch(SQLException e){
            e.printStackTrace();
        } finally {
            mysql.closeConnection(conn);
        }
    }

    
    
public boolean checkUser(Userdata user) {
        Connection conn = mysql.openConnection();
        String sql = "Select * from users where username = ? or email= ?";
        try(PreparedStatement pstm = conn.prepareStatement(sql)){
            pstm.setString(1, user.getUserName());
            pstm.setString(2, user.getEmail());
            ResultSet result = pstm.executeQuery();
            return result.next();
        }catch(SQLException e){
            System.out.println(e);
        }finally{
            mysql.closeConnection(conn);
        }
        return false;
    }
     public boolean updatePassword(String email, String newPassword) {
        Connection conn = mysql.openConnection();
        String sql = "UPDATE users SET password = ? WHERE email = ?";
        
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {

            
            pstm.setString(1, newPassword);
            pstm.setString(2, email);
            
            int rowsAffected = pstm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error updating password: " + e);
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }
    
  }




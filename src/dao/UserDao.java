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
        
        java.sql.Connection conn = mysql.openConnection();
        String sql = "Insert into  users (FullName,UserName,Email,PasswordFieldl,,Confirmpassword) Values (?,?,?,?,?)";
        
        try(PreparedStatement pstm = conn.prepareStatement(sql)){
            
            pstm.setString(1, user.getFullName());
            pstm.setString(2, user.getUserName());
            pstm.setString(3, user.getPasswordFieldl());
            pstm.setString(4, user.getEmail());
            pstm.setString(5, user.getConfirmpassword());
          
            pstm.executeUpdate();
            
        }catch(SQLException e){
            System.out.println(e);
        }finally{
            mysql.closeConnection(conn);
        }
        
        
    }
    
    
     public boolean check(Userdata user){
        
        Connection conn = mysql.openConnection();
        String sql = "Select * from users where FullName=? or UserName=?,Password=?,";
        
        try(PreparedStatement pstm = conn.prepareStatement(sql)){
            
            pstm.setString(1, user.getUserName());
            pstm.setString(2, user.getFullName());
         
            ResultSet result = pstm.executeQuery();
            return result.next();
            

        }catch(SQLException e){
            System.out.println(e);
        }finally{
            mysql.closeConnection(conn);
        }
        return false;
        
     }
    
  }




/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;
import java.sql.*;
/**
 *
 * @author hp
 */
public interface Database {
    Connection openConnection(); //for open connection
    void closeConnection(Connection conn ); //for close connection
    ResultSet runQuery(Connection conn, String query );
    int executeUpdate(Connection conn, String query);
    
}

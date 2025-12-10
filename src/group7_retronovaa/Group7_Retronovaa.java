/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package group7_retronovaa;

import Controller.LoginController;
import database.Database;
import database.MySqlConnection;
import view.SignUp;
import view.Login;


/**
 *
 * @author hp
 */
public class Group7_Retronovaa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        
//        Database db = new MySqlConnection();
//        if(db.openConnection() != null){
//            System.out.println("Connection Opened");
//        }else{
//            System.out.println("Connection Closed");
//        }
        
        SignUp signup = new SignUp();
        UserController usercontroller= new UserController(signup);
        usercontroller.open();

        Login log = new Login();
        
        log.setVisible(true);

    }
    
}

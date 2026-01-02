/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package group7_retronovaa;

import Controller.DashboardController;
import Controller.LoginController;
import Controller.UserController;
import database.Database;
import database.MySqlConnection;
import javax.security.auth.login.LoginContext;
import view.Dashboard;
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

        
        Database db = new MySqlConnection();
        if(db.openConnection() != null){
            System.out.println("Connection Opened");
        }else{
            System.out.println("Connection Closed");
        }
        
//        SignUp signup = new SignUp();
//        UserController usercontroller= new UserController(signup);
//        usercontroller.open();

//        Login log = new Login();
//        LoginController controller = new LoginController(log);
//        controller.open();
        //log.setVisible(true);
        
        Dashboard d = new Dashboard();
        DashboardController cont = new DashboardController(d);
        cont.open();
    
    }
}
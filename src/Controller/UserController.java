/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import dao.UserDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Userdata;
import view.Login;
import view.SignUp;

/**
 *
 * @author Acer
 */
public class UserController {
     private final UserDao userdao = new UserDao();
    private final SignUp userView;

     public UserController(SignUp userView) {
        this.userView = userView;
        this.userView.AddaddUserListener(new AddActionListner());
    }
    
   
    public void open(){
        this.userView.setVisible(true);
    }
    public void close(){
        this.userView.dispose();
    }

    class AddActionListner implements ActionListener {
        
        
        @Override
        public void actionPerformed(ActionEvent ex) {
        try {
            String FullName = userView.getFullName().getText();
            String UserName = userView.getUserName().getText();
            String Email= userView.getEmail().getText();
            String Password = new String(userView.getPasswordField().getPassword());
            String ConfirmPassword = new String(userView.getConfirmpassword().getPassword());

            
            
            if (FullName.trim().isEmpty() ||
                UserName.trim().isEmpty() ||
                Email.trim().isEmpty() ||
                Password.trim().isEmpty() ||
                ConfirmPassword.trim().isEmpty()) {

                JOptionPane.showMessageDialog(userView,
                        "Please fill all fields!");
                return;
            }
            // Check if passwords match
            if (!Password.equals(ConfirmPassword)) {
                JOptionPane.showMessageDialog(userView, "Passwords do not match!");
                return;
            }


            
            Userdata userdata = new Userdata(FullName, Email, UserName, Password, ConfirmPassword);
            
            boolean exists = userdao.checkUser(userdata);
            if (exists) {
                JOptionPane.showMessageDialog(userView,
                      "User already exists with this email or phone number.");
            } else {
                userdao.signUp(userdata);
                JOptionPane.showMessageDialog(userView,
                        "Registration successful! Please log in.");
                
                  Login loginView = new Login();
                LoginController loginController = new LoginController(loginView);
                
                close();
                loginController.open();
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(userView, "Error: " + e.getMessage());
        }
    }
    }
    
    
    
   
}

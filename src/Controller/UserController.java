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
import view.SignUp;

/**
 *
 * @author Acer
 */
public class UserController {
     private final UserDao userdao = new UserDao();
    private final SignUp userView;

    public UserController() {
        this.userView = null;
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
            
            try{
                
                String FullName = userView.getFullName().getText();
                String UserName = userView.getUserName().getText();
                String PasswordFieldl =userView.getPasswordFieldl().getText();
                String Email =userView.getEmail().getText();
                String Confirmpassword =userView.getConfirmpassword().getText();
                
                Userdata userdata = new Userdata(FullName,UserName,PasswordFieldl,Email,Confirmpassword);
                
                
                
                
                
                
                
                
                
                boolean check = userdao.check(userdata);
                if(check){
                    JOptionPane.showMessageDialog(userView, "Duplicate user");
                }else{
                    userdao.signUp(userdata);
                    JOptionPane.showMessageDialog(userView,"Succesfull");
//                    Login lc = new Login();
//                    LoginController log = new LoginController(lc);
//                    log.close();
//                    log.open();
                   
                }
                
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
            
        }
    }
    
    
    
   
}

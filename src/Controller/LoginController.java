/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import dao.LoginDao;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;  
import javax.swing.JOptionPane;
import model.LoginData;
import view.Login;


/**
 *
 * @author Hp
 */
public class LoginController {
    private final LoginDao logindao= new LoginDao();
    private final Login loginview;
    
    
    public  LoginController (Login Loginview){
        this.loginview=Loginview;
        
  
        loginview.AddLoginListener(new AddLoginListener());
    }
    public void open(){
        this.loginview.setVisible(true);
    }
    public void closer(){
        this.loginview.dispose();
    }

    class AddLoginListener implements ActionListener {
@Override
    public void actionPerformed (ActionEvent e){
        try{
            String username= loginview.getUsername().getText();
            String password= loginview.getPassword().getText();
            LoginData logindata = new LoginData(username,password);
            boolean check = logindao.Login(logindata);
            if(check){
                JOptionPane.showMessageDialog(loginview,"Duplicate user");
            }else{
                logindao.Login(logindata);
                JOptionPane.showMessageDialog(loginview,"Sucessful");
               
            }
        }catch (HeadlessException ex){
            System.out.println(ex.getMessage());
        }
    }
        }
    }

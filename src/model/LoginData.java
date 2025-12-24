/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Hp
 */
public class LoginData {
    
    private String Username;
    private String Password;
    
    public void setUsername(String username){
        this.Username = username;
    }
    
    public String getUsername(){
        return Username;
    }
    
    public void setPassword(String password){
        this.Password=password;
    }
    
    public String getPassword(){
        return Password;
    }

    public LoginData(String username, String password) {
        this.Password=password;
        this.Username=username;
    }

    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Acer
 */

  


public class Userdata {
    private int user_id;
    private String FullName;
    
    private String UserName;
    private String PasswordFieldl;
    private String Email;
    private String Confirmpassword;
    
    public Userdata(String Email, String Password) {
        this.Email = Email;
        this.PasswordFieldl = Password;
        
    }
    
    public void setuser_id( int user_id){
        this.user_id=user_id;
    }
    
    public int getuser_id(){
        return user_id;
    }
    public void setFullName(String FullName){
        this.FullName=FullName;
    }
    
    public String getFullName(){
        return FullName;
    }
    
    public void setUserName(String UserName){
        this.UserName=UserName;
    }
    
    public String getUserName(){
        return UserName;
    }
    
    public void setPassword(String PasswordFieldl){
        this.PasswordFieldl=PasswordFieldl;
    }
    
    public String getPassword(){
        return PasswordFieldl;
    }
    
    public void setEmail(String Email){
        this.Email=Email;
    }
    
    public String getEmail(){
        return Email;
    }
    
    public void setConfirmpassword(String Confirmpassword){
        this.Confirmpassword=Confirmpassword;
    }
    
    public String getConfirmpassword(){
        return Confirmpassword;
    }
    
    public Userdata(String FullName, String UserName, String PasswordFieldl,String Email,String Confirmpassword){
        this.FullName= FullName;
        this.UserName= UserName;
        this.PasswordFieldl=PasswordFieldl;
        this.Email=Email;
        this.Confirmpassword=Confirmpassword;
        
        
    }

    public String getPasswordFieldl() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
    
    
    
    
    
    
    
    
    
    
    
    




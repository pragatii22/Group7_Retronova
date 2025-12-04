/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Acer
 */

  

/**
 *
 * @author deepakshah
 */
public class Userdata {
    
    private String FullName;
    
    private String UserName;
    private String Password;
    private String Email;
    private String Confirmpassword;
    
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
    
    public void setPassword(String Password){
        this.Password=Password;
    }
    
    public String getPassword(){
        return Password;
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
    
    public Userdata(String FullName, String UserName, String Password,String Email,String Confirmpassword){
        this.FullName= FullName;
        this.UserName= UserName;
        this.Password=Password;
        this.Email=Email;
        this.Confirmpassword=Confirmpassword;
        
        
    }
}
    
    
    
    
    
    
    
    
    
    
    
    




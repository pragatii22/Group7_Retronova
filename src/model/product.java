/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Acer
 */
public class product {
    private final String type;
    private final double price;
    private final String imagePath;

     public product(String type,double price,String imagePath){
        
         this.type=type;
         this.price=price;
         this.imagePath=imagePath;
         
     
     }
     
    public String gettype(){
        return type;
    }
    
    public double getprice(){
        return price;
    }
    
    public String getimagePath(){
        return imagePath;
    }
}    
    

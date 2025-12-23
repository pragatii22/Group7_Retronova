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
    private final Categorys categoryId;
    
     public product(String type,double price,String imagePath,Categorys categoryId){
        
         this.type=type;
         this.price=price;
         this.imagePath=imagePath;
         this.categoryId=categoryId;
     
     }
     
    public String getType(){
        return type;
    }
    
    public double getPrice(){
        return price;
    }
    
    public String getImagePath(){
        return imagePath;
    }
    public Categorys getCategoryId() {
        return categoryId;
    }

}
    

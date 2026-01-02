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
    private int productId;
    private  String type;
    private  double price;
    private  String imagePath;
    private  String categoryId;
    
     public product(int productId, String type, double price, String imagePath, String categoryId) {
        this.productId = productId;
        this.type = type;
        this.price = price;
        this.imagePath = imagePath;
        this.categoryId = categoryId;
    }

     public product(String type,double price,String imagePath,String categoryId){
        
         this.type=type;
         this.price=price;
         this.imagePath=imagePath;
         this.categoryId=categoryId;
     
     }

     public String getType() {
         return type;
     }

     public double getPrice() {
         return price;
     }

     public String getImagePath() {
         return imagePath;
     }

     public String getCategoryId() {
         return categoryId;
     }

     public void setType(String type) {
          this.type = type;
     }
        public void setPrice(double price) {
             this.price = price;
        }
        public void setImagePath(String imagePath) {
             this.imagePath = imagePath;
        }
        public void setCategoryId(String categoryId) {
             this.categoryId = categoryId;
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

}
    

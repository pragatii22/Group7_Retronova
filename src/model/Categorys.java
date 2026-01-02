/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author shrina
 */

    

public class Categorys {

    private int categoryId;
    private String category;
    
    public Categorys(int categoryId, String category) {
        this.categoryId = categoryId;
        this.category = category;
    }

    
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    public void setCategory(String category) {
        this.category= category;
    }
    public String getcategory() {
        return category;
    }

    
    }




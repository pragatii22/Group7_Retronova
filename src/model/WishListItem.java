package model;

/**
 *
 * @author Hp
 */
public class WishListItem {
    
    private int id;
    private String itemName;
    private String description;
    private double price;
    private String imagePath;
    
    public WishListItem(int id, String itemName, String description, double price, String imagePath) {
        this.id = id;
        this.itemName = itemName;
        this.description = description;
        this.price = price;
        this.imagePath = imagePath;
    }
    
    // Getters and setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getItemName() {
        return itemName;
    }
    
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public String getImagePath() {
        return imagePath;
    }
    
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}


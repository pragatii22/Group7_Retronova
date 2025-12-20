package model;

/**
 * Model class representing a Cart Item
 * @author Retronova Team
 */
public class CartItem {
    
    private int id;
    private int userId;
    private int productId;
    private String productName;
    private String description;
    private double price;
    private int quantity;
    private String size;
    private String color;
    private String imagePath;
    
    // Constructor for creating a new cart item
    public CartItem(int userId, int productId, String productName, String description, 
                    double price, int quantity, String size, String color, String imagePath) {
        this.userId = userId;
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.size = size;
        this.color = color;
        this.imagePath = imagePath;
    }
    
    // Constructor for retrieving existing cart item from database
    public CartItem(int id, int userId, int productId, String productName, String description, 
                    double price, int quantity, String size, String color, String imagePath) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.size = size;
        this.color = color;
        this.imagePath = imagePath;
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public int getProductId() {
        return productId;
    }
    
    public void setProductId(int productId) {
        this.productId = productId;
    }
    
    public String getProductName() {
        return productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
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
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public String getSize() {
        return size;
    }
    
    public void setSize(String size) {
        this.size = size;
    }
    
    public String getColor() {
        return color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
    
    public String getImagePath() {
        return imagePath;
    }
    
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    
    // Calculate total price for this cart item
    public double getTotalPrice() {
        return price * quantity;
    }
    
    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", userId=" + userId +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}

package Controller;

import dao.WishListdao;
import java.util.List;
import model.WishListItem;

/**
 *
 * @author Hp
 */
public class WishListController {
    
    private WishListdao wishListDao = new WishListdao();
    private int currentUserId = 1; // For simplicity, assume user id 1
    
    public List<WishListItem> getWishListItems() {
        return wishListDao.getWishListItems(currentUserId);
    }
    
    public boolean addItemToWishList(WishListItem item) {
        return wishListDao.addToWishList(currentUserId, item);
    }
    
    public boolean removeItemFromWishList(int itemId) {
        return wishListDao.removeFromWishList(currentUserId, itemId);
    }
    
    public boolean removeAllItemsFromWishList() {
        return wishListDao.removeAllFromWishList(currentUserId);
    }
    
    // You can add more methods as needed, like select all, etc.
}



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import dao.ShippingAddressDao;
import java.util.List;
import model.ShippingAddressData;

/**
 *
 * @author Hp
 */
public class ShippingAddressController {
    
    private ShippingAddressDao addressDao = new ShippingAddressDao();
    private int currentUserId = 1; // For simplicity, assume user id 1
    
    public List<ShippingAddressData> getAddresses() {
        return addressDao.getAddresses(currentUserId);
    }
    
    public boolean addAddress(ShippingAddressData address) {
        if (validateAddress(address)) {
            address.setUserId(currentUserId);
            return addressDao.addAddress(address);
        }
        return false;
    }
    
    public boolean updateAddress(ShippingAddressData address) {
        if (validateAddress(address) && address.getId() > 0) {
            address.setUserId(currentUserId);
            return addressDao.updateAddress(address);
        }
        return false;
    }
    
    public boolean deleteAddress(int addressId) {
        return addressDao.deleteAddress(addressId, currentUserId);
    }
    
    private boolean validateAddress(ShippingAddressData address) {
        return address != null &&
               address.getAddressLine1() != null && !address.getAddressLine1().trim().isEmpty() &&
               address.getCity() != null && !address.getCity().trim().isEmpty() &&
               address.getState() != null && !address.getState().trim().isEmpty() &&
               address.getZipCode() != null && !address.getZipCode().trim().isEmpty() &&
               address.getCountry() != null && !address.getCountry().trim().isEmpty() &&
               address.getPhone() != null && !address.getPhone().trim().isEmpty();
    }
}

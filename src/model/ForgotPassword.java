/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import dao.UserDao;
import dao.OTPDAO;
import utils.OTPService;
import java.util.Date;

/**
 *
 * @author hp
 */


public class ForgotPassword{
    private UserDao userDao;
    private OTPDAO otpDao;

    public ForgotPassword() {
        userDao = new UserDao();
        otpDao = new OTPDAO();
    }

    

    // Generate OTP and store it with expiry
    public boolean generateAndStoreOTP(String email) {
        String otp = OTPService.generateOTP();
        Date expiry = OTPService.calculateExpiryTime();
        return otpDao.storeOTP(email, otp, expiry);
    }

    // Verify OTP entered by user
    public boolean verifyOTP(String email, String enteredOTP) {
        return otpDao.verifyOTP(email, enteredOTP);
    }

    // Clear OTP after successful reset
    public void clearOTP(String email) {
        otpDao.clearOTP(email);
    }

    // Update the user's password
    public boolean resetPassword(String email, String newPassword) {
        return userDao.updatePassword(email, newPassword);
    }
}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


import Controller.Payment;

/**
 *
 * @author ACER
 */
public class Payment {
    public boolean savePayment(Payment payment) {
        // Fake save for now (database code can be added later)
        System.out.println("Saved Payment:");
        System.out.println("Method: " + payment.getMethod());
        System.out.println("Account ID: " + payment.getAccountId());
        return true;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.PaymentDAO;
import model.Payment;

/**
 *
 * @author Hp
 */
public class PaymentController {
    private PaymentDAO paymentDAO = new PaymentDAO();

    public boolean processPayment(int orderId, double amount, String method) {
        Payment payment = new Payment(orderId, amount, method, "completed");
        return paymentDAO.savePayment(payment);
    }
}

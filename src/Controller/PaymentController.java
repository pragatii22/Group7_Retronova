/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import dao.PaymentService;
import javax.swing.JOptionPane;

import view.payment1;
import view.payment2;
import Controller.Payment;
/**
 *
 * @author ACER
 */
public class PaymentController {
    private final PaymentService paymentService = new PaymentService();

    private String selectedMethod = null;

    // Called when user selects credit card / esewa / cash
    public void setPaymentMethod(String method) {
        this.selectedMethod = method;
    }

    // Called when user clicks Confirm Order
    public void proceedToPayment(payment2 page) {

        if (selectedMethod == null) {
            JOptionPane.showMessageDialog(page, "Please select a payment method!");
            return;
        }

        // Directly open PayNowPage (for card / esewa)
        if (selectedMethod.equals("Esewa")) {
            new payment1(this).setVisible(true);
            page.dispose();
        }

        // COD requires no payment screen
        if (selectedMethod.equals("Cash on Delivery")) {
            JOptionPane.showMessageDialog(page, "Order Confirmed! Pay on delivery.");
        }
    }

    // Called when PayNowPage clicks "Pay Now"
    public void processPayment(String accountId, String password, payment1 page) {

        if (accountId.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(page, "All fields are required!");
            return;
        }

        // Create payment model
        Payment payment = new Payment();
        payment.setMethod(selectedMethod);
        payment.setAccountId(accountId);
        payment.setPassword(password);

        boolean success = paymentService.makePayment(payment);

        if (success) {
            JOptionPane.showMessageDialog(page, "Payment Successful!");
            page.dispose();
        } else {
            JOptionPane.showMessageDialog(page, "Payment Failed! Try again.");
        }
    }
}

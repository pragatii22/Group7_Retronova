/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//package dao;
//
//
///**
// *
// * @author ACER
// */
//
//import model.Payment;
//
//public class PaymentService {
//    private final PaymentDAO paymentDAO = new PaymentDAO();
//
//    public boolean makePayment(Controller.Payment payment) {
//        // For now no real validation, assume DAO stores payment
//        return paymentDAO.savePayment(payment);
//    }
//}






package dao;

import model.Payment;

/**
 *
 * @author ACER
 */
public class PaymentService {
    private final PaymentDAO paymentDAO = new PaymentDAO();

    public boolean makePayment(Payment payment) {
        // For now no real validation, assume DAO stores payment
        return paymentDAO.savePayment(payment);
    }
}

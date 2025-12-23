/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import dao.ProductDao;
import model.product;
import view.Dashboard;
import view.Product1;

import javax.swing.*;
import java.util.List;

public class DashboardController {

    private final ProductDao productDao;
    private final Dashboard dashboardView;

    public DashboardController(Dashboard dashboardView) {
        System.out.println("[DashboardController] ✓ Initializing...");
        this.dashboardView = dashboardView;
        this.productDao = new ProductDao();

        loadProducts();
    }

    private void loadProducts() {
        System.out.println("[DashboardController] Loading products...");
        try {
            List<product> products = productDao.getAllProducts();
            System.out.println("[DashboardController] ✓ Retrieved " + products.size() + " products");
            dashboardView.displayProducts(products);
            System.out.println("[DashboardController] ✓ Products displayed");
        } catch (Exception e) {
            System.err.println("[DashboardController] ✗ Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}


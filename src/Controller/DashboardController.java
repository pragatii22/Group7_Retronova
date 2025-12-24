/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import dao.ProductDao;
import model.product;
import view.Dashboard;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class DashboardController {

    private final ProductDao productDao;
    private final Dashboard dashboardView;
    // Cache all products so filtering happens in-memory
    private List<product> allProducts = new ArrayList<>();

    public DashboardController(Dashboard dashboardView) {
        System.out.println("[DashboardController] ✓ Initializing...");
        this.dashboardView = dashboardView;
        this.productDao = new ProductDao();

        loadProducts();
        attachFilterListener();
    }

    private void loadProducts() {
        System.out.println("[DashboardController] Loading products...");
        try {
            allProducts = productDao.getAllProducts();
            System.out.println("[DashboardController] ✓ Retrieved " + allProducts.size() + " products");
            // Render all products initially
            dashboardView.displayProducts(allProducts);
            System.out.println("[DashboardController] ✓ Products displayed");
        } catch (Exception e) {
            System.err.println("[DashboardController] ✗ Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Attach listener to the filter combo box to perform category filtering
    private void attachFilterListener() {
        JComboBox<String> filterBox = dashboardView.getFilter();
        filterBox.addActionListener(evt -> {
            String selected = (String) filterBox.getSelectedItem();
            System.out.println("[DashboardController] Filter selected: " + selected);

            if (selected == null || "Filter".equalsIgnoreCase(selected)) {
                // show all
                dashboardView.displayProducts(allProducts);
                return;
            }

            // Map selection to category id: Male=1, Female=2, Kids=3
            int categoryId = -1;
            switch (selected.toLowerCase()) {
                case "male":
                    categoryId = 1;
                    break;
                case "female":
                    categoryId = 2;
                    break;
                case "kids":
                    categoryId = 3;
                    break;
                default:
                    categoryId = -1;
            }

            if (categoryId == -1) {
                dashboardView.displayProducts(allProducts);
                return;
            }

            // Filter cached list and re-render
            List<product> filtered = new ArrayList<>();
            for (product p : allProducts) {
                if (p != null && p.getCategoryId() != null && p.getCategoryId().getCategoryId() == categoryId) {
                    filtered.add(p);
                }
            }

            System.out.println("[DashboardController] ✓ Filtered products: " + filtered.size());
            dashboardView.displayProducts(filtered);
        });
    }
}


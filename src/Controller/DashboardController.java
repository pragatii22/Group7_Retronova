package Controller;

import dao.ProductDao;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import model.Categorys;
import model.product;
import view.Dashboard;

/**
 * DashboardController
 * -------------------
 * Responsibilities:
 * 1. Load products (DB first, fallback if DB fails)
 * 2. Render products on Dashboard
 * 3. Filter products by category
 */
public class DashboardController {

    private final ProductDao productDao;
    private final Dashboard dashboardView;

    // Cache products for in-memory filtering
    private List<product> allProducts = new ArrayList<>();

    public DashboardController(Dashboard dashboardView) {
        System.out.println("[DashboardController] Initializing...");
        this.dashboardView = dashboardView;
        this.productDao = new ProductDao();

        loadProducts();
        attachFilterListener();
    }

    /* =======================
       Product Loading
       ======================= */
    private void loadProducts() {
        try {
            System.out.println("[DashboardController] Loading products from DB...");
            allProducts = productDao.getAllProducts();

            if (allProducts == null || allProducts.isEmpty()) {
                System.out.println("[DashboardController] No DB products found, using fallback data.");
                allProducts = loadFallbackProducts();
            }

            dashboardView.displayProducts(allProducts);
            System.out.println("[DashboardController] Products rendered: " + allProducts.size());

        } catch (Exception e) {
            System.err.println("[DashboardController] DB error: " + e.getMessage());
            e.printStackTrace();

            System.out.println("[DashboardController] Using fallback products due to error.");
            allProducts = loadFallbackProducts();
            dashboardView.displayProducts(allProducts);
        }
    }

    /* =======================
       Fallback Products
       ======================= */
    private List<product> loadFallbackProducts() {
        List<product> list = new ArrayList<>();

        // Male
        list.add(new product("T-Shirt", 600.00, "images/tshirtM.jpeg", new Categorys(1, "Male")));
        list.add(new product("Pant", 200.00, "images/pantM3.jpeg", new Categorys(1, "Male")));
        list.add(new product("Shirt", 499.00, "images/shirt2.jpeg", new Categorys(1, "Male")));

        // Female
        list.add(new product("T-Shirt", 700.00, "images/womenTshirt2.jpg", new Categorys(2, "Female")));
        list.add(new product("T-Shirt", 750.00, "images/womenTshirt3.jpeg", new Categorys(2, "Female")));
        list.add(new product("Pant", 550.00, "images/womenPant3.jpg", new Categorys(2, "Female")));
        list.add(new product("Pant", 580.00, "images/womenPant30.jpg", new Categorys(2, "Female")));

        // Kids
        list.add(new product("T-Shirt", 750.00, "images/kidTshirt2.jpeg", new Categorys(3, "Kids")));
        list.add(new product("T-Shirt", 599.00, "images/kidTshirt3.jpeg", new Categorys(3, "Kids")));

        System.out.println("[DashboardController] Fallback products created: " + list.size());
        return list;
    }

    /* =======================
       Filter Handling
       ======================= */
    private void attachFilterListener() {
        JComboBox<String> filterBox = dashboardView.getFilter();

        filterBox.addActionListener(e -> {
            String selected = (String) filterBox.getSelectedItem();
            System.out.println("[DashboardController] Filter selected: " + selected);

            if (selected == null || selected.equalsIgnoreCase("Filter")) {
                dashboardView.displayProducts(allProducts);
                return;
            }

            int categoryId = mapCategory(selected);
            if (categoryId == -1) {
                dashboardView.displayProducts(allProducts);
                return;
            }

            List<product> filtered = new ArrayList<>();
            for (product p : allProducts) {
                if (p != null
                        && p.getCategoryId() != null
                        && p.getCategoryId().getCategoryId() == categoryId) {
                    filtered.add(p);
                }
            }

            System.out.println("[DashboardController] Filtered count: " + filtered.size());
            dashboardView.displayProducts(filtered);
        });
    }

    /* =======================
       Helpers
       ======================= */
    private int mapCategory(String name) {
        switch (name.toLowerCase()) {
            case "male":
                return 1;
            case "female":
                return 2;
            case "kids":
                return 3;
            default:
                return -1;
        }
    }
}

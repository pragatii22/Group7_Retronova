package Controller;

import dao.ProductDao;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import model.Categorys;
import model.product;
import view.Dashboard;
import view.ImageCard;

/**
 * DashboardController
 * -------------------
 * Responsibilities:
 * 1. Load products (DB first, fallback if DB fails)
 * 2. Render products on Dashboard
 * 3. Filter products by category
 */
public class DashboardController {

    private ProductDao productDao;
    private final Dashboard dashboardView;

    // Cache products for in-memory filtering
    private List<product> allProducts = new ArrayList<>();

    public DashboardController(Dashboard dashboardView) {
        System.out.println("[DashboardController] Initializing...");
        this.dashboardView = dashboardView;
        this.productDao = new ProductDao();

        loadProducts();
        attachFilterListener();
        attachAuthListeners();
    }
    
    public void open(){
        dashboardView.setVisible(true);
    }
    public void close(){
        dashboardView.dispose();
    }

    /* =======================
       Attach topbar / sidebar auth listeners
       ======================= */
    private void attachAuthListeners() {
        // Login button -> open login dialog
        dashboardView.getLoginButton().addActionListener(e -> {
            view.Login loginView = new view.Login();
            // no post-login action by default
            LoginController.postLoginAction = null;
            new LoginController(loginView).open();
        });

        // SignUp -> open sign up dialog
        dashboardView.getSignUpButton().addActionListener(e -> {
            view.SignUp signupView = new view.SignUp();
            new UserController(signupView).open();
        });

        // Sidebar User button should also require login
        dashboardView.getUserButton().addActionListener(e -> {
            // If already logged in, maybe show profile; for now require login
            if (!LoginController.loggedIn) {
                // Use exact wording requested
                javax.swing.JOptionPane.showMessageDialog(dashboardView, "login first");
                view.Login loginView = new view.Login();
                // After login, open an empty dashboard (as requested)
                LoginController.postLoginAction = () -> {
                    java.awt.EventQueue.invokeLater(() -> new view.Dashboard().setVisible(true));
                };
                new LoginController(loginView).open();
            } else {
                // Already logged in: directly open a simple dashboard window
                java.awt.EventQueue.invokeLater(() -> new view.Dashboard().setVisible(true));
            }
        });

        // Logout button clears logged-in flag
        dashboardView.getLogoutButton().addActionListener(e -> {
            if (LoginController.loggedIn) {
                LoginController.loggedIn = false;
                javax.swing.JOptionPane.showMessageDialog(dashboardView, "Logged out");
            } else {
                javax.swing.JOptionPane.showMessageDialog(dashboardView, "Not logged in");
            }
        });
    }

    /* =======================
       Attach image click handlers
       ======================= */
    private void attachImageClickHandlers() {
        java.awt.Component[] comps = dashboardView.getImagePanel().getComponents();
        for (java.awt.Component c : comps) {
            if (c instanceof view.ImageCard) {
                view.ImageCard card = (view.ImageCard) c;

                // On click: only the strawberry product opens wishlist -> checkout flow
                final model.product clickedProduct = card.getProduct();
                card.setOnClick(() -> {
                    boolean isStraw = clickedProduct != null && clickedProduct.getImagePath() != null && clickedProduct.getImagePath().toLowerCase().contains("straw");

                    if (!isStraw) {
                        // For any product that is not the strawberry, if not logged in require login first,
                        // otherwise leave it clickable (no detailed flow) per your instruction.
                        if (!LoginController.loggedIn) {
                            javax.swing.JOptionPane.showMessageDialog(dashboardView, "login first");
                            view.Login loginView = new view.Login();
                            LoginController.postLoginAction = null; // no auto flow for others
                            new LoginController(loginView).open();
                        } else {
                            // logged in: keep clickable but no details for non-straw
                        }
                        return;
                    }

                    // For strawberry product: run the full flow. If not logged-in, ask login and then run the flow.
                    Runnable runFlow = () -> {
                        try {
                            final view.WishList wl = new view.WishList();
                            // Show wishlist on EDT
                            javax.swing.SwingUtilities.invokeLater(() -> wl.showProduct(clickedProduct, false));

                            // Schedule shipping shortly after so UI has time to display
                            javax.swing.Timer t1 = new javax.swing.Timer(250, ev -> {
                                try {
                                    view.ShippingAddress sa = wl.proceedToShipping();

                                    // Schedule payment a bit later to ensure Shipping UI is ready
                                    javax.swing.Timer t2 = new javax.swing.Timer(250, ev2 -> {
                                        try {
                                            sa.proceedToPayment();
                                        } catch (Exception ex2) {
                                            ex2.printStackTrace();
                                        }
                                    });
                                    t2.setRepeats(false);
                                    t2.start();

                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            });
                            t1.setRepeats(false);
                            t1.start();

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    };

                    if (!LoginController.loggedIn) {
                        javax.swing.JOptionPane.showMessageDialog(dashboardView, "login first");
                        view.Login loginView = new view.Login();
                        LoginController.postLoginAction = runFlow;
                        new LoginController(loginView).open();
                    } else {
                        runFlow.run();
                    }
                });
            }
        }
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
            // Attach click handlers to the rendered ImageCard components
            attachImageClickHandlers();
            System.out.println("[DashboardController] Products rendered: " + allProducts.size());

        } catch (Exception e) {
            System.err.println("[DashboardController] DB error: " + e.getMessage());
            e.printStackTrace();

            System.out.println("[DashboardController] Using fallback products due to error.");
            allProducts = loadFallbackProducts();
            dashboardView.displayProducts(allProducts);
        }
    }

    
    // Deepak le add gareko:
    public void loadAllProducts(){
        List<product> item = productDao.getAllItems();
        JPanel panel = dashboardView.getItemPanel();
        
        panel.removeAll();
        
        for(product it: item){
            ImageCard card = new ImageCard();
            card.loadData(it);
            panel.add(card);
        }
        
        panel.revalidate();
        panel.repaint();
    }
    
    
    /* =======================
       Fallback Products
       ======================= */
    private List<product> loadFallbackProducts() {
        List<product> list = new ArrayList<>();

        // Male
        list.add(new product("T-Shirt", 600.00, "images/tshirtM.jpeg", "Male"));
        list.add(new product("Pant", 200.00, "images/pantM3.jpeg", "Male"));
        list.add(new product("Shirt", 499.00, "images/shirt2.jpeg", "Male"));

        list.add(new product("T-Shirt", 700.00, "images/womenTshirt2.jpg", "Female"));
        list.add(new product("T-Shirt", 750.00, "images/womenTshirt3.jpeg", "Female"));
        list.add(new product("Pant", 550.00, "images/womenPant3.jpg", "Female"));
        list.add(new product("T-Shirt", 580.00, "images/strawberry.jpg", "Female"));

        list.add(new product("T-Shirt", 750.00, "images/kidTshirt2.jpeg", "Kids"));
        list.add(new product("T-Shirt", 599.00, "images/kidTshirt3.jpeg", "Kids"));

        System.out.println("[DashboardController] Fallback products created: " + list.size());
        return list;
    }
//        return list;
//    }

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
                attachImageClickHandlers();
                return;
            }

            int categoryId = mapCategory(selected);
            if (categoryId == -1) {
                dashboardView.displayProducts(allProducts);
                attachImageClickHandlers();
                return;
            }
        });
    }
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
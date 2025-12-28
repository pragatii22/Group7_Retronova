package view;

import Controller.DashboardController;
import java.util.List;
import javax.swing.JPanel;
import model.product;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;


/**
 * Dashboard view – renders product cards dynamically.
 */
public class Dashboard extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger =
            java.util.logging.Logger.getLogger(Dashboard.class.getName());

    public Dashboard() {
        initComponents();

        // ===== FIX GRID LAYOUT FOR PRODUCT CARDS =====
        Imagepanel.setLayout(new java.awt.GridLayout(0, 4, 20, 20));
        Imagepanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        Imagepanel.setAutoscrolls(true);

        // ===== SCROLL PANE POLICIES =====
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Load products via controller
        DashboardController dashboardController = new DashboardController(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        Sidebarpanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        logoutbtn = new javax.swing.JButton();
        Userbtn = new javax.swing.JButton();
        Homebtn = new javax.swing.JButton();
        TopBarpanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        SignUpbtn = new javax.swing.JButton();
        Loginbtn = new javax.swing.JButton();
        Filter = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        Imagepanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1399, 756));
        getContentPane().setLayout(null);

        /* ================= Sidebar ================= */
        Sidebarpanel.setBackground(new java.awt.Color(69, 64, 130));
        Sidebarpanel.setLayout(null);

        try {
    java.io.File logoFile = new java.io.File("C:\\Users\\Acer\\Desktop\\dashboard main\\try\\src\\images\\logo_image_4_0.jpg");
    java.awt.image.BufferedImage originalImage = javax.imageio.ImageIO.read(logoFile);
    
    // Resize to fit sidebar top (200x120 pixels)
    java.awt.Image resizedImage = originalImage.getScaledInstance(200, 120, java.awt.Image.SCALE_SMOOTH);
    jLabel2.setIcon(new javax.swing.ImageIcon(resizedImage));
} catch (Exception e) {
    java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.WARNING, "Failed to load logo", e);
    jLabel2.setText("LOGO"); // Fallback text
}
jLabel2.setBounds(20, 20, 200, 120);
Sidebarpanel.add(jLabel2);

        jLabel8.setForeground(java.awt.Color.WHITE);
        jLabel8.setText("Where old stories finds new");
        Sidebarpanel.add(jLabel8);
        jLabel8.setBounds(20, 360, 200, 16);

        jLabel9.setForeground(java.awt.Color.WHITE);
        jLabel9.setText("------- closets --------");
        Sidebarpanel.add(jLabel9);
        jLabel9.setBounds(40, 380, 150, 16);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabel11.setForeground(java.awt.Color.WHITE);
        jLabel11.setText("Retro finds, Modern feels");
        Sidebarpanel.add(jLabel11);
        jLabel11.setBounds(10, 430, 230, 25);

        Homebtn.setText("Home");
        Sidebarpanel.add(Homebtn);
        Homebtn.setBounds(50, 210, 110, 30);

        Userbtn.setText("User");
        Sidebarpanel.add(Userbtn);
        Userbtn.setBounds(50, 250, 110, 30);

        logoutbtn.setText("Log Out");
        Sidebarpanel.add(logoutbtn);
        logoutbtn.setBounds(70, 670, 100, 25);

        getContentPane().add(Sidebarpanel);
        Sidebarpanel.setBounds(0, 0, 250, 720);

        /* ================= Top Bar ================= */
        TopBarpanel.setBackground(new java.awt.Color(240, 230, 230));
        TopBarpanel.setLayout(null);

        jLabel6.setIcon(new javax.swing.ImageIcon(
                getClass().getResource("/images/vertical_rounded_line2.png")));
        TopBarpanel.add(jLabel6);
        jLabel6.setBounds(10, 10, 30, 70);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 36));
        jLabel12.setText("Retro Nova");
        TopBarpanel.add(jLabel12);
        jLabel12.setBounds(40, 10, 230, 40);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabel13.setText("Sales Found");
        TopBarpanel.add(jLabel13);
        jLabel13.setBounds(130, 50, 200, 20);

        Filter.setModel(new javax.swing.DefaultComboBoxModel<>(
                new String[]{"Filter", "Male", "Female", "Kids"}));
        TopBarpanel.add(Filter);
        Filter.setBounds(770, 10, 100, 22);

        SignUpbtn.setText("Sign Up");
        TopBarpanel.add(SignUpbtn);
        SignUpbtn.setBounds(880, 10, 80, 23);

        Loginbtn.setText("Login");
        TopBarpanel.add(Loginbtn);
        Loginbtn.setBounds(970, 10, 80, 23);

        getContentPane().add(TopBarpanel);
        TopBarpanel.setBounds(250, 0, 1150, 130);

        /* ================= Product Area ================= */
        Imagepanel.setBackground(new java.awt.Color(240, 230, 230));
        jScrollPane1.setViewportView(Imagepanel);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(250, 130, 1080, 560);

        pack();
    }// </editor-fold>

    /* ================= Controller API ================= */

    public void displayProducts(List<product> products) {
        System.out.println("[Dashboard] Rendering products: "
                + (products != null ? products.size() : 0));

        Imagepanel.removeAll();

        if (products != null && !products.isEmpty()) {
            for (product p : products) {
                ImageCard card = new ImageCard();
                card.setProduct(p);
                Imagepanel.add(card);
            }
        } else {
            System.err.println("[Dashboard] No products to render");
        }

        Imagepanel.revalidate();
        Imagepanel.repaint();
    }

    public JPanel getImagePanel() {
        return Imagepanel;
    }

    public javax.swing.JComboBox<String> getFilter() {
        return Filter;
    }

    /* ================= Main ================= */

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new Dashboard().setVisible(true));
    }

    // Variables declaration
    private javax.swing.JComboBox<String> Filter;
    private javax.swing.JButton Homebtn;
    private javax.swing.JPanel Imagepanel;
    private javax.swing.JButton Loginbtn;
    private javax.swing.JPanel Sidebarpanel;
    private javax.swing.JButton SignUpbtn;
    private javax.swing.JPanel TopBarpanel;
    private javax.swing.JButton Userbtn;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton logoutbtn;
    
}

package view;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class DashboardView extends JFrame {

    // COLORS
    private final Color SIDEBAR_BG = new Color(42, 26, 94);
    private final Color SIDEBAR_HOVER = new Color(62, 46, 120);
    private final Color CONTENT_BG = new Color(250, 240, 245);
    private final Color PURPLE = new Color(65, 20, 145);

    // COMPONENTS
    private JPanel sidebar;
    private JPanel contentArea;
    private CardLayout card;

    private JButton btnHome, btnSection, btnUser, btnLogout;

    private JPanel menPanel, womenPanel, kidsPanel, homePanel;

    private JPopupMenu sectionMenu;

    public DashboardView() {
        setTitle("Retro Nova — Thrift Shop");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 760);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        initSidebar();
        initContentArea();
        initHeader();

        setVisible(true);
    }

    // --------------------------- HEADER (TOP BAR) ------------------------
    private void initHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(250, 240, 245));
        header.setBorder(new EmptyBorder(10, 20, 10, 20));

        JLabel title = new JLabel("Retro Nova");
        title.setFont(new Font("Poppins", Font.BOLD, 32));
        title.setForeground(Color.BLACK);

        JLabel sales = new JLabel("3 sales Found");
        sales.setFont(new Font("Poppins", Font.PLAIN, 16));
        sales.setForeground(Color.RED);

        JPanel left = new JPanel(new GridLayout(2, 1));
        left.setOpaque(false);
        left.add(title);
        left.add(sales);

        // Search bar
        JTextField searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(300, 35));
        searchField.setBorder(new LineBorder(Color.GRAY, 1));
        searchField.setFont(new Font("Poppins", Font.PLAIN, 14));

        // Login / Signup
        JPanel authButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        authButtons.setOpaque(false);

        JButton login = new JButton("Login");
        JButton signup = new JButton("Sign Up");

        styleHeaderButton(login);
        styleHeaderButton(signup);

        authButtons.add(login);
        authButtons.add(signup);

        header.add(left, BorderLayout.WEST);
        header.add(searchField, BorderLayout.CENTER);
        header.add(authButtons, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);
    }

    private void styleHeaderButton(JButton btn) {
        btn.setBackground(PURPLE);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(new EmptyBorder(8, 20, 8, 20));
        btn.setFont(new Font("Poppins", Font.BOLD, 14));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    // --------------------------- SIDEBAR ------------------------
    private void initSidebar() {
        sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(240, getHeight()));
        sidebar.setBackground(SIDEBAR_BG);
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        sidebar.add(Box.createRigidArea(new Dimension(0, 25)));

        // LOGO
        JLabel logo = new JLabel(loadCircularImage("/images/logo.png", 90));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidebar.add(logo);

        sidebar.add(Box.createRigidArea(new Dimension(0, 40)));

        // HOMEBUTTON
        btnHome = createSidebarButton("Home");
        btnHome.addActionListener(e -> showPanel("home"));
        sidebar.add(btnHome);

        sidebar.add(Box.createRigidArea(new Dimension(0, 15)));

        // SECTION BUTTON
        btnSection = createSidebarButton("Section ▼");
        btnSection.addActionListener(e -> showSectionMenu());
        sidebar.add(btnSection);

        sidebar.add(Box.createRigidArea(new Dimension(0, 15)));

        // USER
        btnUser = createSidebarButton("User");
        btnUser.addActionListener(e -> showPanel("user"));
        sidebar.add(btnUser);

        sidebar.add(Box.createRigidArea(new Dimension(0, 40)));

        JLabel quote = new JLabel("<html><center>Where old stories find new<br>closets.<br><br><b>Retro finds. Modern feels.</b></center></html>");
        quote.setForeground(Color.WHITE);
        quote.setFont(new Font("Poppins", Font.PLAIN, 14));
        quote.setAlignmentX(Component.CENTER_ALIGNMENT);

        sidebar.add(quote);

        sidebar.add(Box.createVerticalGlue());

        btnLogout = createSidebarButton("Log Out");
        btnLogout.addActionListener(e -> dispose());
        sidebar.add(btnLogout);

        add(sidebar, BorderLayout.WEST);

        initSectionMenu();
    }

    private JButton createSidebarButton(String text) {
        JButton btn = new JButton(text);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        btn.setFont(new Font("Poppins", Font.BOLD, 15));
        btn.setForeground(Color.WHITE);
        btn.setBackground(SIDEBAR_BG);
        btn.setFocusPainted(false);
        btn.setBorder(new EmptyBorder(10, 20, 10, 20));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) { btn.setBackground(SIDEBAR_HOVER); }

            @Override
            public void mouseExited(MouseEvent e) { btn.setBackground(SIDEBAR_BG); }
        });

        return btn;
    }

    // --------------------------- DROPDOWN (MEN/WOMEN/KIDS) ------------------------
    private void initSectionMenu() {
        sectionMenu = new JPopupMenu();
        sectionMenu.setBackground(SIDEBAR_BG);

        JMenuItem men = createMenuItem("Men");
        men.addActionListener(e -> showPanel("men"));

        JMenuItem women = createMenuItem("Women");
        women.addActionListener(e -> showPanel("women"));

        JMenuItem kids = createMenuItem("Kids");
        kids.addActionListener(e -> showPanel("kids"));

        sectionMenu.add(men);
        sectionMenu.add(women);
        sectionMenu.add(kids);
    }

    private JMenuItem createMenuItem(String text) {
        JMenuItem item = new JMenuItem(text);
        item.setFont(new Font("Poppins", Font.PLAIN, 15));
        item.setForeground(Color.WHITE);
        item.setBackground(SIDEBAR_BG);
        item.setBorder(new EmptyBorder(10, 20, 10, 20));

        item.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) { item.setBackground(SIDEBAR_HOVER); }

            @Override
            public void mouseExited(MouseEvent e) { item.setBackground(SIDEBAR_BG); }
        });

        return item;
    }

    private void showSectionMenu() {
        sectionMenu.show(btnSection, btnSection.getWidth(), 0);
    }

    // --------------------------- CONTENT AREA ------------------------
    private void initContentArea() {
        card = new CardLayout();
        contentArea = new JPanel(card);
        contentArea.setBackground(CONTENT_BG);

        homePanel = createHomePanel();
        menPanel = createProductPanel("Men");
        womenPanel = createProductPanel("Women");
        kidsPanel = createProductPanel("Kids");

        contentArea.add(homePanel, "home");
        contentArea.add(menPanel, "men");
        contentArea.add(womenPanel, "women");
        contentArea.add(kidsPanel, "kids");

        add(contentArea, BorderLayout.CENTER);
    }

    // --------------------------- HOME PANEL ------------------------
    private JPanel createHomePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(CONTENT_BG);

        JLabel title = new JLabel("Welcome to Retro Nova");
        title.setFont(new Font("Poppins", Font.BOLD, 40));
        title.setForeground(Color.BLACK);

        panel.add(title);

        return panel;
    }

    // --------------------------- PRODUCT GRID PANEL ------------------------
    private JPanel createProductPanel(String type) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(CONTENT_BG);

        JLabel heading = new JLabel(type + " Products");
        heading.setFont(new Font("Poppins", Font.BOLD, 28));
        heading.setBorder(new EmptyBorder(15, 20, 15, 20));

        panel.add(heading, BorderLayout.NORTH);

        JPanel grid = new JPanel(new GridLayout(2, 3, 20, 20));
        grid.setBorder(new EmptyBorder(20, 20, 20, 20));
        grid.setOpaque(false);

        // FAKE PRODUCT ITEMS (you can fetch from DB later)
        for (int i = 1; i <= 6; i++) {
            grid.add(createProductCard("Item " + i, "Rs " + (100 + i * 50), "/images/item.png"));
        }

        panel.add(grid, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createProductCard(String name, String price, String imgPath) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));

        JLabel img = new JLabel(loadImage(imgPath, 160, 160));
        img.setHorizontalAlignment(JLabel.CENTER);

        JLabel t = new JLabel(name, JLabel.CENTER);
        t.setFont(new Font("Poppins", Font.BOLD, 16));

        JLabel p = new JLabel(price, JLabel.CENTER);
        p.setFont(new Font("Poppins", Font.PLAIN, 14));

        card.add(img, BorderLayout.CENTER);
        card.add(t, BorderLayout.NORTH);
        card.add(p, BorderLayout.SOUTH);

        return card;
    }

    // --------------------------- IMAGE LOADING ------------------------
    private ImageIcon loadImage(String path, int w, int h) {
        try {
            InputStream is = getClass().getResourceAsStream(path);
            if (is != null) {
                Image img = ImageIO.read(is);
                return new ImageIcon(img.getScaledInstance(w, h, Image.SCALE_SMOOTH));
            }
        } catch (Exception ignored) {}

        return new ImageIcon(new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB));
    }

    private ImageIcon loadCircularImage(String path, int size) {
        try {
            InputStream is = getClass().getResourceAsStream(path);
            if (is != null) {
                BufferedImage img = ImageIO.read(is);
                BufferedImage circle = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g = circle.createGraphics();
                g.setClip(new java.awt.geom.Ellipse2D.Float(0, 0, size, size));
                g.drawImage(img, 0, 0, size, size, null);
                g.dispose();
                return new ImageIcon(circle);
            }
        } catch (Exception ignored) {}

        return null;
    }

    private void showPanel(String name) {
        card.show(contentArea, name);
    }

    public static void main(String[] args) {
        new DashboardView();
    }
}

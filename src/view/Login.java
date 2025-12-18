/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */



package view;

import java.awt.event.ActionListener;
import javax.swing.*;
/**
 *
 * @author DELL
 */
public class Login extends javax.swing.JFrame {
    

        private static void addActionListener(ActionListener listener) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        
        

    public Login() {
        initComponents();
    }


    // ------------------------------------------------------------------------------------
    // Listener Adders (Same structure as your SignUp class)
    // ------------------------------------------------------------------------------------

    public void AddLoginListener(ActionListener listener) {
        LoginBtn.addActionListener(listener);
    }

   


    public JTextField getEmail() {
        return Username;
    }

    /**
     *
     * @param args
     */
    

   

    public static void main(String args[]) {
        
java.awt.EventQueue.invokeLater(() -> new Login().setVisible(true));
}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        Username = new javax.swing.JTextField();
        Password = new javax.swing.JPasswordField();
        LoginBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LOGIN");
        setMaximumSize(new java.awt.Dimension(719, 408));
        setMinimumSize(new java.awt.Dimension(719, 408));
        setPreferredSize(new java.awt.Dimension(719, 508));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(240, 230, 230));
        jPanel1.setPreferredSize(new java.awt.Dimension(725, 508));
        jPanel1.setLayout(null);

        jPanel2.setPreferredSize(new java.awt.Dimension(399, 508));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LOGIN (2).jpg"))); // NOI18N
        jLabel7.setText("jLabel7");
        jLabel7.setPreferredSize(new java.awt.Dimension(399, 508));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 399, 508);

        Username.setBackground(new java.awt.Color(217, 217, 217));
        Username.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Username.setText("        Email/Username");
        Username.setBorder(new javax.swing.border.AbstractBorder() {
            @Override
            public void paintBorder(java.awt.Component c, java.awt.Graphics g, int x, int y, int width, int height) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g;
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(java.awt.Color.GRAY);
                g2.drawRoundRect(x, y, width - 1, height - 1, 30, 30);
            }
        });
        jPanel1.add(Username);
        Username.setBounds(470, 150, 170, 30);

        Password.setBackground(new java.awt.Color(217, 217, 217));
        Password.setText("               .................");
        Password.setBorder(new javax.swing.border.AbstractBorder() {
            @Override
            public void paintBorder(java.awt.Component c, java.awt.Graphics g, int x, int y, int width, int height) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g;
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(java.awt.Color.GRAY);
                g2.drawRoundRect(x, y, width - 1, height - 1, 30, 30);
            }
        });
        jPanel1.add(Password);
        Password.setBounds(470, 200, 170, 30);

        LoginBtn.setBackground(new java.awt.Color(69, 64, 130));
        LoginBtn.setForeground(new java.awt.Color(255, 255, 255));
        LoginBtn.setText("Login");
        jPanel1.add(LoginBtn);
        LoginBtn.setBounds(520, 270, 75, 23);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Already have an account?");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(470, 320, 150, 16);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 0, 0));
        jLabel3.setText("Signup");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(620, 320, 50, 16);

        jButton1.setBackground(new java.awt.Color(240, 230, 230));
        jButton1.setText("forgot password?");
        jButton1.setBorder(null);
        jPanel1.add(jButton1);
        jButton1.setBounds(580, 240, 130, 23);

        jTextField1.setBackground(new java.awt.Color(115, 11, 11));
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.setText("      Login");
        jTextField1.setBorder(null);
        jPanel1.add(jTextField1);
        jTextField1.setBounds(620, 10, 71, 22);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("Login");

        pack();
    }// </editor-fold>//GEN-END:initComponents
  

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton LoginBtn;
    private javax.swing.JPasswordField Password;
    private javax.swing.JTextField Username;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables


public void AddSignInListener(ActionListener listener) {
        Login.addActionListener(listener);    
    }


public javax.swing.JPasswordField getPassword() {
    return Password;
}

public javax.swing.JTextField getUsername() {
        return Username;
}
}


//Login ko source code




/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import dao.LoginDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.LoginData;
import view.Dashboard;
import view.Login;

public class LoginController {

    private final LoginDao logindao = new LoginDao();
    private final Login loginview;

    public LoginController(Login loginview) {
        this.loginview = loginview;
        loginview.AddSignInListener(new SignInListener());
    }

    public void open() {
        loginview.setVisible(true);
    }

    public void close() {
        loginview.dispose();
    }

    // ================= INNER CLASS =================
    class SignInListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String username = loginview.getUsername().getText().trim();
            String password = new String(loginview.getPassword().getPassword()).trim();

            System.out.println("Username entered: [" + username + "]");
            System.out.println("Password entered: [" + password + "]");

            if (username.isEmpty() || password.isEmpty()
                    || username.equals("Email/Username")) {

                JOptionPane.showMessageDialog(loginview, "Enter valid credentials");
                return;
            }

            LoginData logindata = new LoginData(username, password);
            boolean isValid = logindao.login(logindata);

            if (isValid) {
                JOptionPane.showMessageDialog(loginview, "Login successful");
                loginview.dispose();
                new Dashboard().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(loginview, "Invalid username or password");
            }
        }
    }
}

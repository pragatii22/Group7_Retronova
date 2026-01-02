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
import model.Userdata;
import utils.Session;
import view.Dashboard;
import view.ForgetPassword;
import view.Login;

public class LoginController {

    private final LoginDao logindao = new LoginDao();
    private final Login loginview;
    private Userdata user;

    // Minimal cross-controller signaling: set this before opening Login to run an action after successful login
    public static Runnable postLoginAction = null;
    public static boolean loggedIn = false;

    public LoginController(Login loginview) {
        this.loginview = loginview;
        loginview.AddSignInListener(new SignInListener());
        
        loginview.addForgetPasswordListener(e -> {
            loginview.dispose();
            ForgetPassword fpView = new ForgetPassword();
            new ForgotPasswordController(fpView).open();
        });
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

            if (username.isEmpty() || password.isEmpty()
                    || username.equals("Email/Username")) {

                JOptionPane.showMessageDialog(loginview, "Enter valid credentials");
                return;
            }

            LoginData logindata = new LoginData(username, password);
            boolean isValid = logindao.login(logindata);

            if (isValid) {
                // Mark logged-in state and run any post-login action requested by caller
                LoginController.loggedIn = true;
                Session.login(user);

                JOptionPane.showMessageDialog(loginview, "Login successful");
                loginview.dispose();

                if (LoginController.postLoginAction != null) {
                    try {
                        LoginController.postLoginAction.run();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    } finally {
                        LoginController.postLoginAction = null;
                    }
                } else {
                    // Default behaviour: show dashboard
                    new Dashboard().setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(loginview, "Invalid username or password");
            }
        }
    }
} 

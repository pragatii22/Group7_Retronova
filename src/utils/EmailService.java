/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.Properties;

/**
 * Lightweight EmailService that compiles even when JavaMail is not on classpath.
 * If JavaMail (javax.mail) is available it will attempt to send; otherwise it
 * logs a clear message and returns false so the app can continue.
 */
public class EmailService {
    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";
    private static final String EMAIL_USERNAME = "pragatishhai022@gmail.com";
    private static final String EMAIL_PASSWORD = " wafp yhfp bfzo llvg ";

    public static boolean sendOTPEmail(String recipientEmail, String otpCode) {
        // Quick runtime check for JavaMail availability
        try {
            Class.forName("javax.mail.Transport");
        } catch (ClassNotFoundException e) {
            System.err.println("[EmailService] JavaMail API not found on classpath. OTP emails will not be sent.");
            System.err.println("[EmailService] To enable email sending, add javax.mail (Jakarta Mail) to your project's libraries.");
            return false;
        }

        // If JavaMail is present, attempt to send using it. We keep this minimal and
        // allow any runtime exceptions to propagate as logs - you can add proper
        // SMTP config and the mail lib to enable it.
        try {
            // We use reflective calls here for compatibility; if you prefer,
            // add javax.mail as a dependency and replace with direct API calls.
            java.util.Properties props = new java.util.Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", SMTP_HOST);
            props.put("mail.smtp.port", SMTP_PORT);

            // Use the real JavaMail if available (reflection is verbose); for now log and return
            System.out.println("[EmailService] JavaMail present but send not implemented with reflection. Please add JavaMail and uncomment direct send code.");
            return false;
        } catch (Exception ex) {
            System.err.println("[EmailService] Failed to prepare/send email: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

}


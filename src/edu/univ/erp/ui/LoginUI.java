package edu.univ.erp.ui;

import edu.univ.erp.dao.AuthDAO;
import edu.univ.erp.models.User;
import edu.univ.erp.session.CurrentUser;


import com.formdev.flatlaf.FlatLightLaf;   // FlatLaf import

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginUI extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginUI() {
        setTitle("University ERP - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);

        // Panel layout (modern & simple)
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel userLabel = new JLabel("Username:");
        JLabel passLabel = new JLabel("Password:");

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");

        // Add components
        panel.add(userLabel);
        panel.add(usernameField);

        panel.add(passLabel);
        panel.add(passwordField);

        panel.add(new JLabel()); // empty space
        panel.add(loginButton);

        add(panel);

        // Action listener for login
        loginButton.addActionListener(this::handleLogin);
    }

    private void handleLogin(ActionEvent e) {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        AuthDAO dao = new AuthDAO();
        User user = dao.validateLogin(username, password);

        if (user != null) {
            CurrentUser.setUser(user);

            JOptionPane.showMessageDialog(this,
                    "Welcome " + user.getUsername() + " (" + user.getRole() + ")");

            openDashboard(user.getRole());
        } else {
            JOptionPane.showMessageDialog(this,
                    "Invalid credentials!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openDashboard(String role) {
        dispose(); // close login window

        switch (role.toLowerCase()) {
            case "admin" -> new DashboardAdmin().setVisible(true);
            case "instructor" -> new DashboardInstructor().setVisible(true);
            case "student" -> new DashboardStudent().setVisible(true);
            default -> JOptionPane.showMessageDialog(this, "Unknown role: " + role);
        }
    }

    public static void main(String[] args) {
        // Enable modern UI theme
        try {
            FlatLightLaf.setup();
        } catch (Exception e) {
            System.err.println("Failed to initialize FlatLaf");
        }

        SwingUtilities.invokeLater(() -> new LoginUI().setVisible(true));
    }
}

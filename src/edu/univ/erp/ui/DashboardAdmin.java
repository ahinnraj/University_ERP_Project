package edu.univ.erp.ui;

import javax.swing.*;

public class DashboardAdmin extends JFrame {
    public DashboardAdmin() {
        setTitle("Admin Dashboard");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label = new JLabel("Welcome, Admin! (Manage Users, Courses, Maintenance)", SwingConstants.CENTER);
        add(label);
    }
}

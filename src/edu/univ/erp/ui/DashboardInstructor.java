package edu.univ.erp.ui;

import javax.swing.*;

public class DashboardInstructor extends JFrame {
    public DashboardInstructor() {
        setTitle("Instructor Dashboard");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label = new JLabel("Welcome, Instructor! (Manage Grades, View Stats)", SwingConstants.CENTER);
        add(label);
    }
}

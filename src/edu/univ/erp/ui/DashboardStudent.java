package edu.univ.erp.ui;

import javax.swing.*;

public class DashboardStudent extends JFrame {
    public DashboardStudent() {
        setTitle("Student Dashboard");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label = new JLabel("Welcome, Student! (Register, Drop, View Timetable)", SwingConstants.CENTER);
        add(label);
    }
}

package edu.univ.erp.ui;

import javax.swing.*;
import java.awt.*;

public class DashboardAdmin extends JFrame {

    public DashboardAdmin() {
        setTitle("Admin Dashboard");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // --- Panel setup ---
        JPanel panel = new JPanel(new GridLayout(7, 1, 10, 10));  // changed to 7 rows (title + 6 buttons)
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel title = new JLabel("Admin Dashboard", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));

        JButton manageUsersBtn = new JButton("Manage Users");
        JButton manageCoursesBtn = new JButton("Manage Courses");
        JButton manageSectionsBtn = new JButton("Manage Sections");
        JButton assignInstructorBtn = new JButton("Assign Instructor to Section");
        JButton maintenanceBtn = new JButton("Toggle Maintenance Mode");
        JButton logoutBtn = new JButton("Logout");

        // Add components to panel
        panel.add(title);
        panel.add(manageUsersBtn);
        panel.add(manageCoursesBtn);
        panel.add(manageSectionsBtn);
        panel.add(assignInstructorBtn);
        panel.add(maintenanceBtn);
        panel.add(logoutBtn);

        add(panel);

        // -------- Button Actions --------

        manageUsersBtn.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Manage Users feature coming soon!")
        );

        manageCoursesBtn.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Manage Courses feature coming soon!")
        );

        manageSectionsBtn.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Manage Sections feature coming soon!")
        );

        assignInstructorBtn.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Assign Instructor feature coming soon!")
        );

        maintenanceBtn.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Maintenance Mode toggle coming soon!")
        );

        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginUI().setVisible(true);
        });
    }
}

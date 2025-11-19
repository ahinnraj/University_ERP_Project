package edu.univ.erp.ui;

import edu.univ.erp.session.CurrentUser;

import javax.swing.*;
import java.awt.*;

public class DashboardInstructor extends JFrame {

    public DashboardInstructor() {
        setTitle("Instructor Dashboard");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel title = new JLabel("Instructor Dashboard", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));

        JButton mySectionsBtn = new JButton("My Sections");
        JButton enterScoresBtn = new JButton("Enter Scores");
        JButton computeFinalBtn = new JButton("Compute Final Grades");
        JButton statsBtn = new JButton("View Class Statistics");
        JButton logoutBtn = new JButton("Logout");

        panel.add(title);
        panel.add(mySectionsBtn);
        panel.add(enterScoresBtn);
        panel.add(computeFinalBtn);
        panel.add(statsBtn);
        panel.add(logoutBtn);

        add(panel);

        // -------- Button Actions --------
        mySectionsBtn.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "My Sections feature coming soon!")
        );

        enterScoresBtn.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Enter Scores feature coming soon!")
        );

        computeFinalBtn.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Compute Final Grades feature coming soon!")
        );

        statsBtn.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Class Statistics coming soon!")
        );

        logoutBtn.addActionListener(e -> {
            CurrentUser.logout();
            dispose();
            new LoginUI().setVisible(true);
        });
    }
}


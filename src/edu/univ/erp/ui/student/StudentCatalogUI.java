package edu.univ.erp.ui.student;

import edu.univ.erp.db.DatabaseConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class StudentCatalogUI extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public StudentCatalogUI() {
        setTitle("Course Catalog");
        setSize(1000, 600);
        setLocationRelativeTo(null);

        model = new DefaultTableModel(new Object[]{
                "Course Code", "Title", "Credits",
                "Section ID", "Capacity", "Day/Time", "Instructor"
        }, 0);

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);

        loadCourseCatalog();
    }

    private void loadCourseCatalog() {
        String query = """
            SELECT 
                c.code,
                c.title,
                c.credits,
                s.section_id,
                s.capacity,
                s.day_time AS day_time,
                ua.username AS instructor
            FROM erp_db.courses c
            JOIN erp_db.sections s ON c.course_id = s.course_id
            JOIN erp_db.instructors i ON s.instructor_id = i.instructor_id
            JOIN auth_db.users_auth ua ON ua.user_id = i.instructor_id
            ORDER BY c.code;
        """;

        try (Connection conn = DatabaseConnection.getERPConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("code"),
                        rs.getString("title"),
                        rs.getInt("credits"),
                        rs.getInt("section_id"),
                        rs.getInt("capacity"),
                        rs.getString("day_time"),
                        rs.getString("instructor")
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Failed to load catalog: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}

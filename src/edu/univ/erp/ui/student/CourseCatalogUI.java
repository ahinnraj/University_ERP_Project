package edu.univ.erp.ui.student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CourseCatalogUI extends JFrame {

    private JTable courseTable;

    public CourseCatalogUI() {
        setTitle("Course Catalog");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Table model
        String[] columns = { "Course Code", "Title", "Credits", "Capacity", "Instructor" };

        // TEMPORARY DUMMY DATA (We replace with database data in Day 9)
        Object[][] data = {
                { "CS101", "Intro to CS", 3, 60, "Dr. Kumar" },
                { "MA201", "Calculus II", 4, 40, "Prof. Meena" },
                { "PH105", "Physics I", 3, 50, "Dr. Varun" }
        };

        DefaultTableModel model = new DefaultTableModel(data, columns);
        courseTable = new JTable(model);

        add(new JScrollPane(courseTable), BorderLayout.CENTER);
    }
}

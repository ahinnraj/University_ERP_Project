package edu.univ.erp.ui.student;

import edu.univ.erp.dao.EnrollmentDAO;
import edu.univ.erp.session.CurrentUser;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DropSectionUI extends JFrame {

    private JTable table;

    public DropSectionUI() {
        setTitle("Drop a Section");
        setSize(700, 400);
        setLocationRelativeTo(null);

        EnrollmentDAO dao = new EnrollmentDAO();
        List<String[]> rows = dao.getMyEnrollments(CurrentUser.getUserId());

        String[] cols = {"Enrollment ID", "Section ID", "Course Code", "Title", "Day/Time"};
        Object[][] data = new Object[rows.size()][5];

        for (int i = 0; i < rows.size(); i++) {
            data[i] = rows.get(i);
        }

        table = new JTable(data, cols);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton dropBtn = new JButton("Drop");
        add(dropBtn, BorderLayout.SOUTH);

        dropBtn.addActionListener(e -> handleDrop());
    }

    private void handleDrop() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a section to drop!");
            return;
        }

        int enrollmentId = Integer.parseInt((String) table.getValueAt(row, 0));

        EnrollmentDAO dao = new EnrollmentDAO();
        boolean success = dao.drop(enrollmentId);

        JOptionPane.showMessageDialog(this,
                success ? "Dropped successfully!" : "Drop failed!");
    }
}

package edu.univ.erp.ui.student;

import edu.univ.erp.dao.EnrollmentDAO;
import edu.univ.erp.session.CurrentUser;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TimetableUI extends JFrame {

    public TimetableUI() {
        setTitle("My Timetable");
        setSize(700, 400);
        setLocationRelativeTo(null);

        EnrollmentDAO dao = new EnrollmentDAO();
        List<String[]> rows = dao.getMyEnrollments(CurrentUser.getUserId());

        String[] cols = {"Section ID", "Course Code", "Title", "Day/Time"};
        Object[][] data = new Object[rows.size()][4];

        for (int i = 0; i < rows.size(); i++) {
            data[i][0] = rows.get(i)[1];
            data[i][1] = rows.get(i)[2];
            data[i][2] = rows.get(i)[3];
            data[i][3] = rows.get(i)[4];
        }

        JTable table = new JTable(data, cols);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }
}

package edu.univ.erp.ui.student;

import edu.univ.erp.dao.GradeDAO;
import edu.univ.erp.session.CurrentUser;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ViewGradesUI extends JFrame {

    public ViewGradesUI() {
        setTitle("My Grades");
        setSize(900, 450);
        setLocationRelativeTo(null);

        String[] cols = {"Course Code", "Title", "Component", "Score", "Final Grade"};

        GradeDAO dao = new GradeDAO();
        Integer studentId = CurrentUser.getUserId();
        List<String[]> grades = dao.getGradesForStudent(studentId);

        Object[][] data = new Object[grades.size()][5];
        for (int i = 0; i < grades.size(); i++) {
            data[i] = grades.get(i);
        }

        JTable table = new JTable(data, cols);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }
}

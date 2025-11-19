package edu.univ.erp.ui.student;

import edu.univ.erp.dao.CourseDAO;
import edu.univ.erp.service.StudentService;
import edu.univ.erp.session.CurrentUser;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RegisterSectionUI extends JFrame {

    private JTable table;

    public RegisterSectionUI() {
        setTitle("Register for Section");
        setSize(900, 450);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        CourseDAO dao = new CourseDAO();
        List<String[]> sections = dao.getAllSectionRows();

        String[] cols = { "Section ID", "Course Code", "Title", "Day/Time", "Capacity", "Instructor" };
        Object[][] data = new Object[sections.size()][6];

        for (int i = 0; i < sections.size(); i++) {
            String[] s = sections.get(i);
            data[i][0] = Integer.parseInt(s[0]); // section_id
            data[i][1] = s[1]; // code
            data[i][2] = s[2]; // title
            data[i][3] = s[3]; // day/time
            data[i][4] = s[4]; // capacity
            data[i][5] = s[5]; // instructor
        }

        table = new JTable(data, cols);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton registerBtn = new JButton("Register");
        add(registerBtn, BorderLayout.SOUTH);

        registerBtn.addActionListener(e -> handleRegister());
    }

    private void handleRegister() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a section!");
            return;
        }

        int sectionId = (int) table.getValueAt(row, 0);
        Integer studentId = CurrentUser.getUserId();

        if (studentId == null) {
            JOptionPane.showMessageDialog(this, "No logged-in student found.");
            return;
        }

        StudentService service = new StudentService();
        String result = service.register(studentId, sectionId);

        JOptionPane.showMessageDialog(this, result);
    }
}

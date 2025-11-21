package edu.univ.erp.ui;

import edu.univ.erp.session.CurrentUser;
import edu.univ.erp.ui.student.StudentCatalogUI;
import edu.univ.erp.ui.student.RegisterSectionUI;
import edu.univ.erp.ui.student.CourseCatalogUI;
import edu.univ.erp.ui.student.DropSectionUI;
import edu.univ.erp.ui.student.TimetableUI;
import edu.univ.erp.ui.student.ViewGradesUI;





import javax.swing.*;
import java.awt.*;

public class DashboardStudent extends JFrame {

    public DashboardStudent() {
        setTitle("Student Dashboard");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(7, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel title = new JLabel("Student Dashboard", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));

        JButton viewCatalogBtn = new JButton("View Course Catalog");
        JButton registerBtn = new JButton("Register for a Section");
        JButton dropBtn = new JButton("Drop a Section");
        JButton timetableBtn = new JButton("View Timetable");
        JButton gradesBtn = new JButton("View Grades");
        JButton logoutBtn = new JButton("Logout");

        panel.add(title);
        panel.add(viewCatalogBtn);
        panel.add(registerBtn);
        panel.add(dropBtn);
        panel.add(timetableBtn);
        panel.add(gradesBtn);
        panel.add(logoutBtn);

        add(panel);

        // -------- Button Actions --------
        viewCatalogBtn.addActionListener(e -> new StudentCatalogUI().setVisible(true));

        registerBtn.addActionListener(e -> new RegisterSectionUI().setVisible(true));

        dropBtn.addActionListener(e -> new DropSectionUI().setVisible(true));
        timetableBtn.addActionListener(e -> new TimetableUI().setVisible(true));
        gradesBtn.addActionListener(e -> new ViewGradesUI().setVisible(true));





        logoutBtn.addActionListener(e -> {
            CurrentUser.logout();
            dispose();
            new LoginUI().setVisible(true);
        });
    }
}

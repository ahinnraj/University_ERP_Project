package edu.univ.erp.dao;

import edu.univ.erp.db.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GradeDAO {

    // Returns each component score + final grade for a student
    public List<String[]> getGradesForStudent(int studentId) {

        String query = """
        SELECT 
            c.code,
            c.title,
            g.component,
            g.score,
            g.final_grade
        FROM erp_db.enrollments e
        JOIN erp_db.sections s ON e.section_id = s.section_id
        JOIN erp_db.courses c ON s.course_id = c.course_id
        LEFT JOIN erp_db.grades g ON g.enrollment_id = e.enrollment_id
        WHERE e.student_id = ?;
        """;

        List<String[]> list = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getERPConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new String[]{
                        rs.getString("code"),
                        rs.getString("title"),
                        rs.getString("component"),
                        rs.getString("score"),
                        rs.getString("final_grade")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}

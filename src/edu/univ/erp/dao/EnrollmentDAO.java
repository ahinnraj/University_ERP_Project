package edu.univ.erp.dao;

import edu.univ.erp.db.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAO {

    // 1. Duplicate check
    public boolean isAlreadyEnrolled(int studentId, int sectionId) {
        String query = """
            SELECT * FROM erp_db.enrollments
            WHERE student_id = ? AND section_id = ? AND status='registered';
        """;

        try (Connection conn = DatabaseConnection.getERPConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, studentId);
            stmt.setInt(2, sectionId);

            ResultSet rs = stmt.executeQuery();
            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 2. Current number of students in a section
    public int getCurrentEnrollmentCount(int sectionId) {
        String query = """
            SELECT COUNT(*) AS count
            FROM erp_db.enrollments
            WHERE section_id = ? AND status='registered';
        """;

        try (Connection conn = DatabaseConnection.getERPConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, sectionId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) return rs.getInt("count");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // 3. Get section capacity
    public int getSectionCapacity(int sectionId) {
        String query = """
            SELECT capacity 
            FROM erp_db.sections
            WHERE section_id = ?;
        """;

        try (Connection conn = DatabaseConnection.getERPConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, sectionId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) return rs.getInt("capacity");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // 4. Enroll student
    public boolean enroll(int studentId, int sectionId) {
        String query = """
            INSERT INTO erp_db.enrollments (student_id, section_id, status)
            VALUES (?, ?, 'registered');
        """;

        try (Connection conn = DatabaseConnection.getERPConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, studentId);
            stmt.setInt(2, sectionId);

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 5. Get all sections student is enrolled in
    public List<String[]> getMyEnrollments(int studentId) {

        List<String[]> list = new ArrayList<>();

        String query = """
            SELECT e.enrollment_id,
                   s.section_id,
                   c.code,
                   c.title,
                   s.day_time
            FROM erp_db.enrollments e
            JOIN erp_db.sections s ON e.section_id = s.section_id
            JOIN erp_db.courses c ON s.course_id = c.course_id
            WHERE e.student_id = ? AND e.status='registered';
        """;

        try (Connection conn = DatabaseConnection.getERPConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new String[]{
                        rs.getString("enrollment_id"),
                        rs.getString("section_id"),
                        rs.getString("code"),
                        rs.getString("title"),
                        rs.getString("day_time")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // 6. Drop a section
    public boolean drop(int enrollmentId) {
        String query = """
            UPDATE erp_db.enrollments
            SET status='dropped'
            WHERE enrollment_id = ?;
        """;

        try (Connection conn = DatabaseConnection.getERPConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, enrollmentId);
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}

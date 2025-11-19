package edu.univ.erp.dao;

import edu.univ.erp.db.DatabaseConnection;

import java.sql.*;

public class EnrollmentDAO {

    // -----------------------------
    // 1. Check duplicate enrollment
    // -----------------------------
    public boolean isAlreadyEnrolled(int studentId, int sectionId) {
        String query = """
            SELECT * FROM erp_db.enrollments
            WHERE student_id = ? AND section_id = ? AND status = 'registered';
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

    // -----------------------------
    // 2. Get current enrollment
    // -----------------------------
    public int getCurrentEnrollmentCount(int sectionId) {
        String query = """
            SELECT COUNT(*) AS count
            FROM erp_db.enrollments
            WHERE section_id = ? AND status = 'registered';
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

    // -----------------------------
    // 3. Get section max capacity
    // -----------------------------
    public int getSectionCapacity(int sectionId) {
        String query = """
            SELECT capacity FROM erp_db.sections
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

    // -----------------------------
    // 4. Insert enrollment
    // -----------------------------
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
}

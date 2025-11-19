package edu.univ.erp.dao;

import edu.univ.erp.db.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    public List<String[]> getCourseCatalog() {
        List<String[]> courses = new ArrayList<>();

        String query = """
            SELECT 
                c.code,
                c.title,
                c.credits,
                s.section_id,
                s.capacity,
                s.day_time,
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

                courses.add(new String[]{
                        rs.getString("code"),
                        rs.getString("title"),
                        rs.getString("credits"),
                        rs.getString("section_id"),
                        rs.getString("capacity"),
                        rs.getString("day_time"),
                        rs.getString("instructor")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return courses;
    }

    public List<String[]> getAllSectionRows() {
        List<String[]> sections = new ArrayList<>();

        String query = """
            SELECT 
                s.section_id,
                c.title,
                c.credits,
                ua.username AS instructor,
                s.capacity,
                s.day_time
            FROM erp_db.sections s
            JOIN erp_db.courses c ON s.course_id = c.course_id
            JOIN erp_db.instructors i ON s.instructor_id = i.instructor_id
            JOIN auth_db.users_auth ua ON ua.user_id = i.instructor_id
            ORDER BY s.section_id;
        """;

        try (Connection conn = DatabaseConnection.getERPConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                sections.add(new String[]{
                        rs.getString("section_id"),
                        rs.getString("title"),
                        rs.getString("credits"),
                        rs.getString("instructor"),
                        rs.getString("capacity"),
                        rs.getString("day_time")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sections;
    }

}

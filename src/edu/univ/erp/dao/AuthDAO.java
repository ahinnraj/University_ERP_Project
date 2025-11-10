package edu.univ.erp.dao;

import edu.univ.erp.db.DatabaseConnection;
import edu.univ.erp.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AuthDAO {

    public User validateLogin(String username, String password) {
        String query = "SELECT * FROM users_auth WHERE username = ? AND password = ?";

        try (Connection conn = DatabaseConnection.getAuthConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role");
                return new User(username, password, role);
            } else {
                return null; // invalid login
            }

        } catch (Exception e) {
            System.out.println("❌ Error validating login: " + e.getMessage());
            return null;
        }
    }
}


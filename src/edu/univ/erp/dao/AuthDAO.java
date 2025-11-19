package edu.univ.erp.dao;

import edu.univ.erp.db.DatabaseConnection;
import edu.univ.erp.models.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class AuthDAO {

    public User validateLogin(String username, String password) {

        String query = "SELECT * FROM users_auth WHERE username = ? AND status = 'active'";

        try (Connection conn = DatabaseConnection.getAuthConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                String storedHash = rs.getString("password_hash");

                if (BCrypt.checkpw(password, storedHash)) {
                    String role = rs.getString("role");
                    int userId = rs.getInt("user_id");
                    return new User(userId, username, role);
                } else {
                    System.out.println("❌ Wrong password.");
                }

            } else {
                System.out.println("❌ Username not found.");
            }

        } catch (Exception e) {
            System.out.println("❌ Error validating login: " + e.getMessage());
        }

        return null;
    }
}

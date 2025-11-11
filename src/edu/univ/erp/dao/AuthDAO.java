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

                // ✅ Verify the entered password against the stored hash
                if (BCrypt.checkpw(password, storedHash)) {
                    String role = rs.getString("role");
                    return new User(username, password, role);
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

    // 👇 Add this new method below the above one
    public boolean changePassword(String username, String oldPassword, String newPassword) {
        String querySelect = "SELECT password_hash FROM users_auth WHERE username = ?";
        String queryUpdate = "UPDATE users_auth SET password_hash = ? WHERE username = ?";

        try (Connection conn = DatabaseConnection.getAuthConnection();
             PreparedStatement stmt = conn.prepareStatement(querySelect)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("password_hash");

                // Verify old password
                if (BCrypt.checkpw(oldPassword, storedHash)) {
                    // Hash new password
                    String newHash = BCrypt.hashpw(newPassword, BCrypt.gensalt());

                    try (PreparedStatement updateStmt = conn.prepareStatement(queryUpdate)) {
                        updateStmt.setString(1, newHash);
                        updateStmt.setString(2, username);
                        updateStmt.executeUpdate();
                        System.out.println("✅ Password changed for user: " + username);
                        return true;
                    }
                } else {
                    System.out.println("❌ Incorrect current password.");
                }
            } else {
                System.out.println("❌ User not found.");
            }

        } catch (Exception e) {
            System.out.println("❌ Error changing password: " + e.getMessage());
        }

        return false;
    }
}

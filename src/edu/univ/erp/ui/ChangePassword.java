package edu.univ.erp.ui;

import edu.univ.erp.dao.AuthDAO;
import java.util.Scanner;

public class ChangePassword {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AuthDAO dao = new AuthDAO();

        System.out.println("=== Change Password ===");
        System.out.print("Enter username: ");
        String username = sc.nextLine();

        System.out.print("Enter current password: ");
        String oldPassword = sc.nextLine();

        System.out.print("Enter new password: ");
        String newPassword = sc.nextLine();

        System.out.print("Confirm new password: ");
        String confirmPassword = sc.nextLine();

        if (!newPassword.equals(confirmPassword)) {
            System.out.println("❌ New passwords do not match.");
            return;
        }

        boolean success = dao.changePassword(username, oldPassword, newPassword);

        if (success) {
            System.out.println("✅ Password updated successfully!");
        } else {
            System.out.println("❌ Failed to update password.");
        }
    }
}

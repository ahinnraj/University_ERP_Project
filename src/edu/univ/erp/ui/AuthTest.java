package edu.univ.erp.ui;

import edu.univ.erp.dao.AuthDAO;
import edu.univ.erp.models.User;
import java.util.Scanner;

public class AuthTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AuthDAO dao = new AuthDAO();

        System.out.print("Enter username: ");
        String user = sc.nextLine();

        System.out.print("Enter password: ");
        String pass = sc.nextLine();

        User u = dao.validateLogin(user, pass);

        if (u != null) {
            System.out.println("✅ Login successful!");
            System.out.println("Welcome, " + u.getUsername() + " (" + u.getRole() + ")");
        } else {
            System.out.println("❌ Invalid credentials.");
        }
    }
}

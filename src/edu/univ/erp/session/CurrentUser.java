package edu.univ.erp.session;

import edu.univ.erp.models.User;

public class CurrentUser {
    private static User current;

    public static void setUser(User u) {
        current = u;
    }

    public static User getUser() {
        return current;
    }

    public static Integer getUserId() {
        return current == null ? null : current.getUserId();
    }

    public static String getUsername() {
        return current == null ? null : current.getUsername();
    }

    public static String getRole() {
        return current == null ? null : current.getRole();
    }

    public static void logout() {
        current = null;
    }
}

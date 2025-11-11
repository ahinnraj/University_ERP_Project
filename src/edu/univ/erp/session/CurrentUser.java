package edu.univ.erp.session;

import edu.univ.erp.models.User;

public class CurrentUser {
    private static User currentUser;

    public static void setUser(User user) {
        currentUser = user;
    }

    public static User getUser() {
        return currentUser;
    }

    public static void logout() {
        currentUser = null;
    }
}

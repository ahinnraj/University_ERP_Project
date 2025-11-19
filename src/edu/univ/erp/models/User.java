package edu.univ.erp.models;

public class User {
    private Integer userId;    // may be null for temporary users
    private String username;
    private String password;   // optional; not stored in DB model
    private String role;

    // Constructor for full DB-backed user with id
    public User(int userId, String username, String role) {
        this.userId = userId;
        this.username = username;
        this.role = role;
    }

    // Backward-compatible constructor (username, password, role)
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Simpler constructor (username, role)
    public User(String username, String role) {
        this.username = username;
        this.role = role;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    // password may be null
    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}

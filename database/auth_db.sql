-- AUTH_DB SQL SCRIPT (Simplified for assignment use)
CREATE DATABASE IF NOT EXISTS auth_db;
USE auth_db;

-- Create users_auth table with plain password (no hashing)
CREATE TABLE users_auth (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    role ENUM('admin', 'instructor', 'student') NOT NULL,
    password VARCHAR(100) NOT NULL,
    status ENUM('active', 'inactive') DEFAULT 'active',
    last_login DATETIME DEFAULT NULL
);

-- Sample Data (Plain-text passwords for assignment testing)
INSERT INTO users_auth (username, role, password, status)
VALUES
('admin1', 'admin', 'admin123', 'active'),
('inst1', 'instructor', 'inst123', 'active'),
('stu1', 'student', 'stu123', 'active'),
('stu2', 'student', 'stu234', 'active');

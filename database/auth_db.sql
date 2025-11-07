-- AUTH_DB SQL SCRIPT
CREATE DATABASE IF NOT EXISTS auth_db;
USE auth_db;

CREATE TABLE users_auth (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    role ENUM('admin', 'instructor', 'student') NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    status ENUM('active', 'inactive') DEFAULT 'active',
    last_login DATETIME DEFAULT NULL
);

-- Sample Data (replace password hashes in code later)
INSERT INTO users_auth (username, role, password_hash, status)
VALUES
('admin1', 'admin', 'bcrypt_hash_admin', 'active'),
('inst1', 'instructor', 'bcrypt_hash_instructor', 'active'),
('stu1', 'student', 'bcrypt_hash_student1', 'active'),
('stu2', 'student', 'bcrypt_hash_student2', 'active');

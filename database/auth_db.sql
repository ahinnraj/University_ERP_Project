DROP DATABASE IF EXISTS auth_db;
CREATE DATABASE auth_db;
USE auth_db;

CREATE TABLE users_auth (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    role ENUM('admin', 'instructor', 'student') NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    status ENUM('active', 'inactive') DEFAULT 'active',
    last_login DATETIME DEFAULT NULL
);

-- ✅ Sample Data (with bcrypt-hashed passwords)
INSERT INTO users_auth (username, role, password_hash, status)
VALUES
('admin1', 'admin', '$2a$10$vD4pN1bCHjour4t4a92nROsiiN2gNX851a7qvCuFpYrkjtfsLnmIO', 'active'),
('inst1', 'instructor', '$2a$10$CnrDmwZgd5ppT75nm8R9suVBnWW483zkO8UovCmKdBbnoLYWwfbh.', 'active'),
('stu1', 'student', '$2a$10$ElWgFNpcHYN8.ycUcHiboeSnwMBQYILGtDxJkF/laNOPqUF45r3Bm', 'active'),
('stu2', 'student', '$2a$10$Iu8tPmtctlCWHycsmFYtie9nIErb4jABMFaqDMVk9zFfRfCu/91UO', 'active');


-- ERP_DB SQL SCRIPT
CREATE DATABASE IF NOT EXISTS erp_db;
USE erp_db;

-- 1. Students
CREATE TABLE students (
    student_id INT PRIMARY KEY,
    roll_no VARCHAR(20) UNIQUE NOT NULL,
    program VARCHAR(50),
    year INT,
    FOREIGN KEY (student_id) REFERENCES auth_db.users_auth(user_id)
);

-- 2. Instructors
CREATE TABLE instructors (
    instructor_id INT PRIMARY KEY,
    department VARCHAR(100),
    designation VARCHAR(100),
    FOREIGN KEY (instructor_id) REFERENCES auth_db.users_auth(user_id)
);

-- 3. Courses
CREATE TABLE courses (
    course_id INT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(20) UNIQUE NOT NULL,
    title VARCHAR(100) NOT NULL,
    credits INT NOT NULL
);

-- 4. Sections
CREATE TABLE sections (
    section_id INT AUTO_INCREMENT PRIMARY KEY,
    course_id INT NOT NULL,
    instructor_id INT NOT NULL,
    day_time VARCHAR(50),
    room VARCHAR(50),
    capacity INT CHECK (capacity > 0),
    semester VARCHAR(10),
    year INT,
    FOREIGN KEY (course_id) REFERENCES courses(course_id),
    FOREIGN KEY (instructor_id) REFERENCES instructors(instructor_id)
);

-- 5. Enrollments
CREATE TABLE enrollments (
    enrollment_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    section_id INT NOT NULL,
    status ENUM('registered','dropped','completed') DEFAULT 'registered',
    UNIQUE (student_id, section_id),
    FOREIGN KEY (student_id) REFERENCES students(student_id),
    FOREIGN KEY (section_id) REFERENCES sections(section_id)
);

-- 6. Grades
CREATE TABLE grades (
    grade_id INT AUTO_INCREMENT PRIMARY KEY,
    enrollment_id INT NOT NULL,
    component VARCHAR(50),
    score DECIMAL(5,2),
    final_grade VARCHAR(5),
    FOREIGN KEY (enrollment_id) REFERENCES enrollments(enrollment_id)
);

-- 7. Settings
CREATE TABLE settings (
    setting_key VARCHAR(50) PRIMARY KEY,
    setting_value VARCHAR(50)
);

-- Insert default setting for Maintenance Mode
INSERT INTO settings (setting_key, setting_value)
VALUES ('maintenance_mode', 'off');

-- Seed Data
INSERT INTO students (student_id, roll_no, program, year)
VALUES
(3, 'BBA2023001', 'BBA', 2),
(4, 'BBA2023002', 'BBA', 2);

INSERT INTO instructors (instructor_id, department, designation)
VALUES
(2, 'Management Studies', 'Assistant Professor');

INSERT INTO courses (code, title, credits)
VALUES
('BBA101', 'Principles of Management', 4),
('BBA102', 'Marketing Fundamentals', 3);

INSERT INTO sections (course_id, instructor_id, day_time, room, capacity, semester, year)
VALUES
(1, 2, 'Mon-Wed 10:00-11:30', 'Room A2', 40, 'Fall', 2025),
(2, 2, 'Tue-Thu 12:00-13:30', 'Room B3', 35, 'Fall', 2025);

INSERT INTO enrollments (student_id, section_id, status)
VALUES
(3, 1, 'registered'),
(4, 2, 'registered');

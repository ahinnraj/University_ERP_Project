HOW TO SET UP DATABASES
-----------------------

1. Open MySQL Workbench or VS Code SQLTools (or MySQL terminal).

2. Run the following commands in order:

   SOURCE auth_db.sql;
   SOURCE erp_db.sql;

3. Confirm setup:
   SHOW DATABASES;
   USE auth_db;
   SHOW TABLES;
   USE erp_db;
   SHOW TABLES;

4. Sample Accounts (for testing via Java login later):
   - admin1 / bcrypt_hash_admin
   - inst1 / bcrypt_hash_instructor
   - stu1 / bcrypt_hash_student1
   - stu2 / bcrypt_hash_student2

5. Notes:
   - Run 'auth_db.sql' first because ERP_DB references it through foreign keys.
   - Password hashes are placeholders; actual verification will occur in Java using bcrypt.
   - Both databases will connect through JDBC URLs in Java code:
        jdbc:mysql://localhost:3306/auth_db
        jdbc:mysql://localhost:3306/erp_db

6. Validation Check (after import):
   SELECT * FROM auth_db.users_auth;
   SELECT * FROM erp_db.students;

If data appears correctly, setup is successful.

--------------------------------------
Created for: University ERP Project
Day 2 - Database Setup
--------------------------------------

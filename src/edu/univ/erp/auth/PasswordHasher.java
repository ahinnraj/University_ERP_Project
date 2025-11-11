package edu.univ.erp.auth;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHasher {
    public static void main(String[] args) {
        System.out.println("admin1 -> " + BCrypt.hashpw("admin123", BCrypt.gensalt(10)));
        System.out.println("inst1  -> " + BCrypt.hashpw("inst123",  BCrypt.gensalt(10)));
        System.out.println("stu1   -> " + BCrypt.hashpw("stu123",   BCrypt.gensalt(10)));
        System.out.println("stu2   -> " + BCrypt.hashpw("stu234",   BCrypt.gensalt(10)));
    }
}


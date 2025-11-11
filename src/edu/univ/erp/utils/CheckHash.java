package edu.univ.erp.utils;

import org.mindrot.jbcrypt.BCrypt;

public class CheckHash {
    public static void main(String[] args) {
        String plain = "stu123"; // password you’re testing
        String hash = "$2a$10$YoWRr./S6FV.K21R1gYB.XD1WVXmzGHI8b8.hryVYNPecMIJ.R62"; // from DB

        boolean match = BCrypt.checkpw(plain, hash);
        System.out.println("Password matches? " + match);
    }
}

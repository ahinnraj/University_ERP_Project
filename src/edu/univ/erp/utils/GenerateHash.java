package edu.univ.erp.utils;

import org.mindrot.jbcrypt.BCrypt;

public class GenerateHash {
    public static void main(String[] args) {
        String hash = BCrypt.hashpw("student234", BCrypt.gensalt());
        System.out.println("New hash for stu2" + ": " + hash);
    }
}

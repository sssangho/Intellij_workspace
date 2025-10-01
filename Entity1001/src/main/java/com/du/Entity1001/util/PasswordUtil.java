package com.du.Entity1001.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
    // 비밀번호 해상
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    // 비밀번호 비교 (로그인 시 사용할 수 있음)
    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}

package com.tuan.util;

public class ValidationUtils {
    public static boolean isEmpty(String str) {
        if (str == null || str.trim().isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean isValidMSSV(String mssv) {
        if (isEmpty(mssv)) {
            return false;
        }
        return mssv.trim().matches("^\\d+$");
    }

    public static boolean isValidEmail(String email) {
        if (isEmpty(email)) {
            return false;
        }
        String trimmed = email.trim();
        return trimmed.contains("@") && trimmed.contains(".") && trimmed.indexOf("@") < trimmed.lastIndexOf(".");
    }
    public static boolean isValidPhone(String phone) {
        return !isEmpty(phone) && phone.trim().matches("\\d{10}");
    }
}
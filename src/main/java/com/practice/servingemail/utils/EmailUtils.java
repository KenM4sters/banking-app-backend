package com.practice.servingemail.utils;

public class EmailUtils {
    public static String getEmailMsg(String name, String host, String token) {
        return "Hello " + name + ", \n\n Your new account has been created. Please click below to verify your account. \n\n" + getVerificationUrl(host, token) + "\n\n Thank you";
    }

    public static String getVerificationUrl(String host, String token) {
        return host + "/api/users?token=" + token;
    }
}

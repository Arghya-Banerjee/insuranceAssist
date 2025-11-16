package com.insuranceAssist.utils;

public final class UserIdManager {
    private static final ThreadLocal<String> USERID = new ThreadLocal<>();

    private UserIdManager() {}

    public static void setUserId(String token) { USERID.set(token); }
    public static String getUserId() { return USERID.get(); }
    public static void clear() { USERID.remove(); }
}
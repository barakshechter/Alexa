package com.sparkvalley.alexa.base.utils;

/**
 * Created with IntelliJ IDEA.
 * User: Barak
 * Date: 7/7/13
 * Time: 3:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class Tests {
    public static boolean notEmpty(String s) {
        return !(s==null || s.trim().length()==0);
    }

    public static boolean isEmpty(String s) {
        return !notEmpty(s);
    }
    public static boolean validFileKey(String s) {
        return (s!=null && s.matches("^[a-fA-F0-9]{176}$"));
    }

    public static boolean isPositive(Long l) {
        return (l != null && l > 0);
    }
    public static boolean isNegative(Long l) {
        return (l != null && l < 0);
    }
    public static boolean isNonNegative(Long l) {
        return (l != null && l >= 0);
    }
    public static boolean isNonPositive(Long l) {
        return (l != null && l <= 0);
    }


}

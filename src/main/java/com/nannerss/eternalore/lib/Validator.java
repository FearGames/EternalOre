package com.nannerss.eternalore.lib;


public class Validator {
    public static void notNull(final Object obj, final String name) {
        if (obj == null) {
            throw new NullPointerException(name + " can't be null");
        }
    }

    public static void isTrue(final boolean statement, final String message) {
        if (!statement) {
            throw new IllegalArgumentException(message);
        }
    }

    public static boolean isInt(final String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isLong(final String string) {
        try {
            Long.parseLong(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

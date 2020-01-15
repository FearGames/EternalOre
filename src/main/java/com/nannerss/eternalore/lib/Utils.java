package com.nannerss.eternalore.lib;

import net.md_5.bungee.api.ChatColor;

import java.util.List;

public class Utils {
    public static String colorize(final String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static double square(final double number) {
        return number * number;
    }

    public static String join(final String[] elements, final String separator, int startIndex, final int endIndex) {
        Validator.isTrue(startIndex >= 0 && startIndex < elements.length, "startIndex out of bounds");
        Validator.isTrue(endIndex >= 0 && endIndex <= elements.length, "endIndex out of bounds");
        Validator.isTrue(startIndex <= endIndex, "startIndex lower than endIndex");
        final StringBuilder result = new StringBuilder();
        while (startIndex < endIndex) {
            if (result.length() != 0) {
                result.append(separator);
            }
            if (elements[startIndex] != null) {
                result.append(elements[startIndex]);
            }
            ++startIndex;
        }
        return result.toString();
    }

    public static String join(final String[] elements, final String separator) {
        return join(elements, separator, 0, elements.length);
    }

    public static String join(final List<String> elements, final String separator, final int startIndex, final int size) {
        return join(elements.toArray(new String[0]), separator, startIndex, size);
    }

    public static String join(final List<String> elements, final String separator) {
        return join(elements, separator, 0, elements.size());
    }

    public static String formatTime(final String args) {
        final long total = Long.parseLong(args);
        final long s = total % 60L;
        final long m = (total - s) / 60L % 60L;
        final long h = (total - m) / 3600L % 24L;
        final long d = (total - h) / 86400L % 365L;
        final long y = (total - d) / 31536000L;
        final StringBuilder str = new StringBuilder();
        if (y != 0L) {
            str.append(y).append("y");
        }
        if (d != 0L) {
            str.append(d).append("d");
        }
        if (h != 0L) {
            str.append(h).append("h");
        }
        if (m != 0L) {
            str.append(m).append("m");
        }
        if (s != 0L) {
            str.append(s).append("s");
        }
        return str.toString();
    }

    public static Long unformatTime(final String args) {
        long y = 0L;
        long d = 0L;
        long h = 0L;
        long m = 0L;
        long s = 0L;
        if (args.toLowerCase().contains("y".toLowerCase())) {
            y = Long.parseLong(args.substring(0, args.indexOf("y"))) * 31536000L;
        }
        if (args.toLowerCase().contains("d".toLowerCase())) {
            String start = "";
            boolean after = false;
            if (args.toLowerCase().contains("y".toLowerCase())) {
                start = "y";
                after = true;
            }
            d = Long.parseLong(args.substring(after ? (args.indexOf(start) + 1) : 0, args.indexOf("d"))) * 86400L;
        }
        if (args.toLowerCase().contains("h".toLowerCase())) {
            String start = "";
            boolean after = false;
            if (args.toLowerCase().contains("d".toLowerCase())) {
                start = "d";
                after = true;
            } else if (args.toLowerCase().contains("y".toLowerCase())) {
                start = "y";
                after = true;
            }
            h = Long.parseLong(args.substring(after ? (args.indexOf(start) + 1) : 0, args.indexOf("h"))) * 3600L;
        }
        if (args.toLowerCase().contains("m".toLowerCase())) {
            String start = "";
            boolean after = false;
            if (args.toLowerCase().contains("h".toLowerCase())) {
                start = "h";
                after = true;
            } else if (args.toLowerCase().contains("d".toLowerCase())) {
                start = "d";
                after = true;
            } else if (args.toLowerCase().contains("y".toLowerCase())) {
                start = "y";
                after = true;
            }
            m = Long.parseLong(args.substring(after ? (args.indexOf(start) + 1) : 0, args.indexOf("m"))) * 60L;
        }
        if (args.toLowerCase().contains("s".toLowerCase())) {
            String start = "";
            boolean after = false;
            if (args.toLowerCase().contains("m".toLowerCase())) {
                start = "m";
                after = true;
            } else if (args.toLowerCase().contains("h".toLowerCase())) {
                start = "h";
                after = true;
            } else if (args.toLowerCase().contains("d".toLowerCase())) {
                start = "d";
                after = true;
            } else if (args.toLowerCase().contains("y".toLowerCase())) {
                start = "y";
                after = true;
            }
            s = Long.parseLong(args.substring(after ? (args.indexOf(start) + 1) : 0, args.indexOf("s")));
        }
        return y + d + h + m + s;
    }
}

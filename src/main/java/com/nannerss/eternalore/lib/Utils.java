package com.nannerss.eternalore.lib;

import net.md_5.bungee.api.ChatColor;

public class Utils {

    public static String colorize(final String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}

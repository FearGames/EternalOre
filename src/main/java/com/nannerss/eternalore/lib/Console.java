package com.nannerss.eternalore.lib;

import com.nannerss.eternalore.EternalOre;
import org.bukkit.Bukkit;

public class Console {
    public static void log(String message) {
        Messages.sendMessage(Bukkit.getConsoleSender(), "[" + EternalOre.getInstance().getName() + "] " + Utils.colorize(message));
    }

    public static void log(final String... messages) {
        for (final String message : messages) {
            log(message);
        }
    }
}

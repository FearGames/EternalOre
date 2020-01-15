package com.nannerss.eternalore.lib;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;

import java.lang.reflect.Field;

public class Registrar {

    public static void registerCommands(final Command... cmds) {
        for (final Command cmd : cmds) {
            registerCommand(cmd);
        }
    }

    public static void registerCommand(final Command cmd) {
        try {
            final Field cmdMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            cmdMapField.setAccessible(true);
            final CommandMap cmdMap = (CommandMap) cmdMapField.get(Bukkit.getServer());
            cmdMap.register(cmd.getLabel(), cmd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

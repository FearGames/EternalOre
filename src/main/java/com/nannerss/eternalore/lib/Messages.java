package com.nannerss.eternalore.lib;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Messages {

    public static void sendMessage(CommandSender target, String message) {
        target.sendMessage(Utils.colorize(message));
    }

    public static void sendMessage(CommandSender target, String... messages) {
        for (String message : messages) {
            sendMessage(target, message);
        }
    }

    public static void sendTitle(Player player, String title, String subtitle, int in, int stay, int out) {
        player.sendTitle(Utils.colorize(title), Utils.colorize(subtitle), in, stay, out);
    }

    public static void sendActionBar(final Player player, final String message) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Utils.colorize(message)));
    }
}

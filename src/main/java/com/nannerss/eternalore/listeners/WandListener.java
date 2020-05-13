package com.nannerss.eternalore.listeners;

import com.nannerss.eternalore.EternalOre;
import com.nannerss.eternalore.data.Ore;
import com.nannerss.eternalore.data.Settings;
import com.nannerss.eternalore.items.WandItem;
import com.nannerss.eternalore.lib.ConfigManager;
import com.nannerss.eternalore.lib.Messages;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class WandListener implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Action a = e.getAction();

        if (!p.getInventory().getItemInMainHand().hasItemMeta()) {
            return;
        }

        if (p.getInventory().getItemInMainHand().getType().equals(WandItem.getItem().getType()) && p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(WandItem.getItem().getItemMeta().getDisplayName())) {
            if (!p.hasPermission("eternalore.wand.use")) {
                Messages.sendMessage(p, "&cInsufficient permissions!");
                return;
            }

            if (e.getClickedBlock() == null) {
                return;
            }

            Location loc = e.getClickedBlock().getLocation();
            String id = loc.getWorld().getName() + "!" + loc.getBlockX() + loc.getBlockY() + loc.getBlockZ();

            if (a == Action.LEFT_CLICK_BLOCK) {
                if (p.isSneaking()) {
                    if (e.getClickedBlock().getType() == Material.REDSTONE_BLOCK || Settings.ORES.contains(e.getClickedBlock().getType())) {
                        Ore cache = EternalOre.getCache(id);

                        cache.setType("RANDOM_ORE");
                        cache.setLocation(loc);
                        cache.setRespawnTime(0);

                        Messages.sendMessage(p, "&dMade " + WordUtils.capitalize(e.getClickedBlock().getType().toString().toLowerCase().replace("_", " ")) + " at " + loc.getBlockX() + ", " + loc.getBlockY() + ", " + loc.getBlockZ() + " a random ore!");

                        return;
                    }

                    Messages.sendMessage(p, "&cThe block you clicked is not a valid ore type!");
                } else {
                    if (Settings.ORES.contains(e.getClickedBlock().getType())) {
                        Ore cache = EternalOre.getCache(id);

                        if (e.getClickedBlock().getType() == Material.NETHER_QUARTZ_ORE) {
                            cache.setType("QUARTZ_ORE");
                        } else {
                            cache.setType(e.getClickedBlock().getType().toString());
                        }

                        cache.setLocation(loc);
                        cache.setRespawnTime(0);

                        Messages.sendMessage(p, "&dMade " + WordUtils.capitalize(e.getClickedBlock().getType().toString().toLowerCase().replace("_", " ")) + " at " + loc.getBlockX() + ", " + loc.getBlockY() + ", " + loc.getBlockZ() + " a respawnable ore!");

                        return;
                    }

                    Messages.sendMessage(p, "&cThe block you clicked is not a valid ore type!");
                }
            } else if (a == Action.RIGHT_CLICK_BLOCK) {
                if (Settings.ORES.contains(e.getClickedBlock().getType()) || e.getClickedBlock().getType() == Settings.PLACEHOLDER_BLOCK) {
                    Ore cache = EternalOre.getCache(id);

                    ConfigManager cfg = EternalOre.getOres();
                    cfg.set("ores." + cache.getId(), null);

                    EternalOre.getOresCache().invalidate(cache.getId());

                    Messages.sendMessage(p, "&dMade " + WordUtils.capitalize(e.getClickedBlock().getType().toString().toLowerCase().replace("_", " ")) + " at " + loc.getBlockX() + ", " + loc.getBlockY() + ", " + loc.getBlockZ() + " a normal ore!");

                    return;
                }

                Messages.sendMessage(p, "&cThe block you clicked is not a valid ore type!");
            }
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();

        if (!p.getInventory().getItemInMainHand().hasItemMeta()) {
            return;
        }

        if (p.getInventory().getItemInMainHand().getType().equals(WandItem.getItem().getType()) && p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(WandItem.getItem().getItemMeta().getDisplayName())) {
            e.setCancelled(true);
        }
    }

}

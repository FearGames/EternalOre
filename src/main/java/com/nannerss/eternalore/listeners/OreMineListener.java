package com.nannerss.eternalore.listeners;

import com.nannerss.eternalore.EternalOre;
import com.nannerss.eternalore.data.Ore;
import com.nannerss.eternalore.data.Settings;
import com.nannerss.eternalore.lib.Messages;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class OreMineListener implements Listener {

    private static Random r = new Random();

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();

        if (p.getGameMode() == GameMode.SURVIVAL) {
            for (Ore cache : EternalOre.getOresCache().asMap().values()) {
                if (e.getBlock().getLocation().getWorld() == cache.getLocation().getWorld() && e.getBlock().getLocation().getBlockX() == cache.getLocation().getBlockX() && e.getBlock().getLocation().getBlockY() == cache.getLocation().getBlockY() && e.getBlock().getLocation().getBlockZ() == cache.getLocation().getBlockZ()) {
                    if (e.getBlock().getType() == Settings.PLACEHOLDER_BLOCK) {
                        e.setCancelled(true);
                        return;
                    }

                    Material drop;

                    if (e.getBlock().getType() == Material.STONE) {
                        drop = Settings.STONE_DROP;
                    } else if (e.getBlock().getType() == Material.COBBLESTONE) {
                        drop = Settings.COBBLESTONE_DROP;
                    } else if (e.getBlock().getType() == Material.NETHER_QUARTZ_ORE) {
                        drop = Settings.QUARTZ_DROP;
                    } else if (e.getBlock().getType() == Material.COAL_ORE) {
                        drop = Settings.COAL_DROP;
                    } else if (e.getBlock().getType() == Material.IRON_ORE) {
                        drop = Settings.IRON_DROP;
                    } else if (e.getBlock().getType() == Material.GOLD_ORE) {
                        drop = Settings.GOLD_DROP;
                    } else if (e.getBlock().getType() == Material.LAPIS_ORE) {
                        drop = Settings.LAPIS_DROP;
                    } else if (e.getBlock().getType() == Material.REDSTONE_ORE) {
                        drop = Settings.REDSTONE_DROP;
                    } else if (e.getBlock().getType() == Material.DIAMOND_ORE) {
                        drop = Settings.DIAMOND_DROP;
                    } else if (e.getBlock().getType() == Material.EMERALD_ORE) {
                        drop = Settings.EMERALD_DROP;
                    } else {
                        drop = Material.AIR;
                    }

                    e.getBlock().setType(Settings.PLACEHOLDER_BLOCK);

                    switch (cache.getType()) {
                        case "STONE":
                            cache.setRespawnTime(System.currentTimeMillis() + Settings.STONE_RESPAWN_TIME * 60 * 1000);

                            final ItemStack stoneDrop = new ItemStack(Settings.STONE_DROP, r.nextInt(Settings.STONE_MAX_DROP_COUNT) + 1);

                            if (p.getInventory().getItemInMainHand().getType().toString().contains("PICKAXE")) {
                                if (p.getInventory().getItemInMainHand().hasItemMeta()) {
                                    if (p.getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.LOOT_BONUS_BLOCKS)) {
                                        stoneDrop.setAmount(r.nextBoolean() ? stoneDrop.getAmount() + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) : stoneDrop.getAmount());
                                    }
                                }
                            }

                            if (p.getInventory().firstEmpty() == -1) {
                                if (p.getInventory().first(Settings.STONE_DROP) != -1 && p.getInventory().getItem(p.getInventory().first(Settings.STONE_DROP)).getAmount() != Settings.STONE_DROP.getMaxStackSize()) {
                                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1F);
                                    p.getInventory().addItem(stoneDrop);
                                } else {
                                    p.getWorld().dropItem(p.getLocation(), stoneDrop);
                                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1F, 1F);
                                    Messages.sendActionBar(p, "&c&lInventory is full. Dropping items at your feet.");
                                }
                            } else {
                                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1F);
                                p.getInventory().addItem(stoneDrop);
                            }
                            break;
                        case "COBBLESTONE":
                            cache.setRespawnTime(System.currentTimeMillis() + Settings.COBBLESTONE_RESPAWN_TIME * 60 * 1000);

                            final ItemStack cobbleDrop = new ItemStack(Settings.COBBLESTONE_DROP, r.nextInt(Settings.COBBLESTONE_MAX_DROP_COUNT) + 1);

                            if (p.getInventory().getItemInMainHand().getType().toString().contains("PICKAXE")) {
                                if (p.getInventory().getItemInMainHand().hasItemMeta()) {
                                    if (p.getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.LOOT_BONUS_BLOCKS)) {
                                        cobbleDrop.setAmount(r.nextBoolean() ? cobbleDrop.getAmount() + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) : cobbleDrop.getAmount());
                                    }
                                }
                            }

                            if (p.getInventory().firstEmpty() == -1) {
                                if (p.getInventory().first(Settings.COBBLESTONE_DROP) != -1 && p.getInventory().getItem(p.getInventory().first(Settings.COBBLESTONE_DROP)).getAmount() != Settings.COBBLESTONE_DROP.getMaxStackSize()) {
                                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1F);
                                    p.getInventory().addItem(cobbleDrop);
                                } else {
                                    p.getWorld().dropItem(p.getLocation(), cobbleDrop);
                                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1F, 1F);
                                    Messages.sendActionBar(p, "&c&lInventory is full. Dropping items at your feet.");
                                }
                            } else {
                                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1F);
                                p.getInventory().addItem(cobbleDrop);
                            }
                            break;
                        case "QUARTZ_ORE":
                            cache.setRespawnTime(System.currentTimeMillis() + Settings.QUARTZ_RESPAWN_TIME * 60 * 1000);

                            final ItemStack quartzDrop = new ItemStack(Settings.QUARTZ_DROP, r.nextInt(Settings.QUARTZ_MAX_DROP_COUNT) + 1);

                            if (p.getInventory().getItemInMainHand().getType().toString().contains("PICKAXE")) {
                                if (p.getInventory().getItemInMainHand().hasItemMeta()) {
                                    if (p.getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.LOOT_BONUS_BLOCKS)) {
                                        quartzDrop.setAmount(r.nextBoolean() ? quartzDrop.getAmount() + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) : quartzDrop.getAmount());
                                    }
                                }
                            }

                            if (p.getInventory().firstEmpty() == -1) {
                                if (p.getInventory().first(Settings.QUARTZ_DROP) != -1 && p.getInventory().getItem(p.getInventory().first(Settings.QUARTZ_DROP)).getAmount() != Settings.QUARTZ_DROP.getMaxStackSize()) {
                                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1F);
                                    p.getInventory().addItem(quartzDrop);
                                } else {
                                    p.getWorld().dropItem(p.getLocation(), quartzDrop);
                                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1F, 1F);
                                    Messages.sendActionBar(p, "&c&lInventory is full. Dropping items at your feet.");
                                }
                            } else {
                                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1F);
                                p.getInventory().addItem(quartzDrop);
                            }
                            break;
                        case "COAL_ORE":
                            cache.setRespawnTime(System.currentTimeMillis() + Settings.COAL_RESPAWN_TIME * 60 * 1000);

                            final ItemStack coalDrop = new ItemStack(Settings.COAL_DROP, r.nextInt(Settings.COAL_MAX_DROP_COUNT) + 1);

                            if (p.getInventory().getItemInMainHand().getType().toString().contains("PICKAXE")) {
                                if (p.getInventory().getItemInMainHand().hasItemMeta()) {
                                    if (p.getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.LOOT_BONUS_BLOCKS)) {
                                        coalDrop.setAmount(r.nextBoolean() ? coalDrop.getAmount() + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) : coalDrop.getAmount());
                                    }
                                }
                            }

                            if (p.getInventory().firstEmpty() == -1) {
                                if (p.getInventory().first(Settings.COAL_DROP) != -1 && p.getInventory().getItem(p.getInventory().first(Settings.COAL_DROP)).getAmount() != Settings.COAL_DROP.getMaxStackSize()) {
                                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1F);
                                    p.getInventory().addItem(coalDrop);
                                } else {
                                    p.getWorld().dropItem(p.getLocation(), coalDrop);
                                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1F, 1F);
                                    Messages.sendActionBar(p, "&c&lInventory is full. Dropping items at your feet.");
                                }
                            } else {
                                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1F);
                                p.getInventory().addItem(coalDrop);
                            }
                            break;
                        case "IRON_ORE":
                            cache.setRespawnTime(System.currentTimeMillis() + Settings.IRON_RESPAWN_TIME * 60 * 1000);

                            final ItemStack ironDrop = new ItemStack(Settings.IRON_DROP, r.nextInt(Settings.IRON_MAX_DROP_COUNT) + 1);

                            if (p.getInventory().getItemInMainHand().getType().toString().contains("PICKAXE")) {
                                if (p.getInventory().getItemInMainHand().hasItemMeta()) {
                                    if (p.getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.LOOT_BONUS_BLOCKS)) {
                                        ironDrop.setAmount(r.nextBoolean() ? ironDrop.getAmount() + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) : ironDrop.getAmount());
                                    }
                                }
                            }

                            if (p.getInventory().firstEmpty() == -1) {
                                if (p.getInventory().first(Settings.IRON_DROP) != -1 && p.getInventory().getItem(p.getInventory().first(Settings.IRON_DROP)).getAmount() != Settings.IRON_DROP.getMaxStackSize()) {
                                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1F);
                                    p.getInventory().addItem(ironDrop);
                                } else {
                                    p.getWorld().dropItem(p.getLocation(), ironDrop);
                                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1F, 1F);
                                    Messages.sendActionBar(p, "&c&lInventory is full. Dropping items at your feet.");
                                }
                            } else {
                                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1F);
                                p.getInventory().addItem(ironDrop);
                            }
                            break;
                        case "GOLD_ORE":
                            cache.setRespawnTime(System.currentTimeMillis() + Settings.GOLD_RESPAWN_TIME * 60 * 1000);

                            final ItemStack goldDrop = new ItemStack(Settings.GOLD_DROP, r.nextInt(Settings.GOLD_MAX_DROP_COUNT) + 1);

                            if (p.getInventory().getItemInMainHand().getType().toString().contains("PICKAXE")) {
                                if (p.getInventory().getItemInMainHand().hasItemMeta()) {
                                    if (p.getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.LOOT_BONUS_BLOCKS)) {
                                        goldDrop.setAmount(r.nextBoolean() ? goldDrop.getAmount() + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) : goldDrop.getAmount());
                                    }
                                }
                            }

                            if (p.getInventory().firstEmpty() == -1) {
                                if (p.getInventory().first(Settings.GOLD_DROP) != -1 && p.getInventory().getItem(p.getInventory().first(Settings.GOLD_DROP)).getAmount() != Settings.GOLD_DROP.getMaxStackSize()) {
                                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1F);
                                    p.getInventory().addItem(goldDrop);
                                } else {
                                    p.getWorld().dropItem(p.getLocation(), goldDrop);
                                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1F, 1F);
                                    Messages.sendActionBar(p, "&c&lInventory is full. Dropping items at your feet.");
                                }
                            } else {
                                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1F);
                                p.getInventory().addItem(goldDrop);
                            }
                            break;
                        case "LAPIS_ORE":
                            cache.setRespawnTime(System.currentTimeMillis() + Settings.LAPIS_RESPAWN_TIME * 60 * 1000);

                            final ItemStack lapisDrop = new ItemStack(Settings.LAPIS_DROP, r.nextInt(Settings.LAPIS_MAX_DROP_COUNT) + 1);

                            if (p.getInventory().getItemInMainHand().getType().toString().contains("PICKAXE")) {
                                if (p.getInventory().getItemInMainHand().hasItemMeta()) {
                                    if (p.getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.LOOT_BONUS_BLOCKS)) {
                                        lapisDrop.setAmount(r.nextBoolean() ? lapisDrop.getAmount() + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) : lapisDrop.getAmount());
                                    }
                                }
                            }

                            if (p.getInventory().firstEmpty() == -1) {
                                if (p.getInventory().first(Settings.LAPIS_DROP) != -1 && p.getInventory().getItem(p.getInventory().first(Settings.LAPIS_DROP)).getAmount() != Settings.LAPIS_DROP.getMaxStackSize()) {
                                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1F);
                                    p.getInventory().addItem(lapisDrop);
                                } else {
                                    p.getWorld().dropItem(p.getLocation(), lapisDrop);
                                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1F, 1F);
                                    Messages.sendActionBar(p, "&c&lInventory is full. Dropping items at your feet.");
                                }
                            } else {
                                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1F);
                                p.getInventory().addItem(lapisDrop);
                            }
                            break;
                        case "REDSTONE_ORE":
                            cache.setRespawnTime(System.currentTimeMillis() + Settings.REDSTONE_RESPAWN_TIME * 60 * 1000);

                            final ItemStack redstoneDrop = new ItemStack(Settings.REDSTONE_DROP, r.nextInt(Settings.REDSTONE_MAX_DROP_COUNT) + 1);

                            if (p.getInventory().getItemInMainHand().getType().toString().contains("PICKAXE")) {
                                if (p.getInventory().getItemInMainHand().hasItemMeta()) {
                                    if (p.getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.LOOT_BONUS_BLOCKS)) {
                                        redstoneDrop.setAmount(r.nextBoolean() ? redstoneDrop.getAmount() + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) : redstoneDrop.getAmount());
                                    }
                                }
                            }

                            if (p.getInventory().firstEmpty() == -1) {
                                if (p.getInventory().first(Settings.REDSTONE_DROP) != -1 && p.getInventory().getItem(p.getInventory().first(Settings.REDSTONE_DROP)).getAmount() != Settings.REDSTONE_DROP.getMaxStackSize()) {
                                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1F);
                                    p.getInventory().addItem(redstoneDrop);
                                } else {
                                    p.getWorld().dropItem(p.getLocation(), redstoneDrop);
                                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1F, 1F);
                                    Messages.sendActionBar(p, "&c&lInventory is full. Dropping items at your feet.");
                                }
                            } else {
                                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1F);
                                p.getInventory().addItem(redstoneDrop);
                            }
                            break;
                        case "DIAMOND_ORE":
                            cache.setRespawnTime(System.currentTimeMillis() + Settings.DIAMOND_RESPAWN_TIME * 60 * 1000);

                            final ItemStack diamondDrop = new ItemStack(Settings.DIAMOND_DROP, r.nextInt(Settings.DIAMOND_MAX_DROP_COUNT) + 1);

                            if (p.getInventory().getItemInMainHand().getType().toString().contains("PICKAXE")) {
                                if (p.getInventory().getItemInMainHand().hasItemMeta()) {
                                    if (p.getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.LOOT_BONUS_BLOCKS)) {
                                        diamondDrop.setAmount(r.nextBoolean() ? diamondDrop.getAmount() + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) : diamondDrop.getAmount());
                                    }
                                }
                            }

                            if (p.getInventory().firstEmpty() == -1) {
                                if (p.getInventory().first(Settings.DIAMOND_DROP) != -1 && p.getInventory().getItem(p.getInventory().first(Settings.DIAMOND_DROP)).getAmount() != Settings.DIAMOND_DROP.getMaxStackSize()) {
                                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1F);
                                    p.getInventory().addItem(diamondDrop);
                                } else {
                                    p.getWorld().dropItem(p.getLocation(), diamondDrop);
                                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1F, 1F);
                                    Messages.sendActionBar(p, "&c&lInventory is full. Dropping items at your feet.");
                                }
                            } else {
                                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1F);
                                p.getInventory().addItem(diamondDrop);
                            }
                            break;
                        case "EMERALD_ORE":
                            cache.setRespawnTime(System.currentTimeMillis() + Settings.EMERALD_RESPAWN_TIME * 60 * 1000);

                            final ItemStack emeraldDrop = new ItemStack(Settings.EMERALD_DROP, r.nextInt(Settings.EMERALD_MAX_DROP_COUNT) + 1);

                            if (p.getInventory().getItemInMainHand().getType().toString().contains("PICKAXE")) {
                                if (p.getInventory().getItemInMainHand().hasItemMeta()) {
                                    if (p.getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.LOOT_BONUS_BLOCKS)) {
                                        emeraldDrop.setAmount(r.nextBoolean() ? emeraldDrop.getAmount() + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) : emeraldDrop.getAmount());
                                    }
                                }
                            }

                            if (p.getInventory().firstEmpty() == -1) {
                                if (p.getInventory().first(Settings.EMERALD_DROP) != -1 && p.getInventory().getItem(p.getInventory().first(Settings.EMERALD_DROP)).getAmount() != Settings.EMERALD_DROP.getMaxStackSize()) {
                                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1F);
                                    p.getInventory().addItem(emeraldDrop);
                                } else {
                                    p.getWorld().dropItem(p.getLocation(), emeraldDrop);
                                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1F, 1F);
                                    Messages.sendActionBar(p, "&c&lInventory is full. Dropping items at your feet.");
                                }
                            } else {
                                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1F);
                                p.getInventory().addItem(emeraldDrop);
                            }
                            break;
                        case "RANDOM_ORE":
                            cache.setRespawnTime(System.currentTimeMillis() + Settings.RANDOM_RESPAWN_TIME * 60 * 1000);

                            final ItemStack randomDrop = new ItemStack(drop, r.nextInt(Settings.RANDOM_MAX_DROP_COUNT) + 1);

                            if (p.getInventory().getItemInMainHand().getType().toString().contains("PICKAXE")) {
                                if (p.getInventory().getItemInMainHand().hasItemMeta()) {
                                    if (p.getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.LOOT_BONUS_BLOCKS)) {
                                        randomDrop.setAmount(r.nextBoolean() ? randomDrop.getAmount() + p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) : randomDrop.getAmount());
                                    }
                                }
                            }

                            if (p.getInventory().firstEmpty() == -1) {
                                if (p.getInventory().first(drop) != -1 && p.getInventory().getItem(p.getInventory().first(drop)).getAmount() != drop.getMaxStackSize()) {
                                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1F);
                                    p.getInventory().addItem(randomDrop);
                                } else {
                                    p.getWorld().dropItem(p.getLocation(), randomDrop);
                                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1F, 1F);
                                    Messages.sendActionBar(p, "&c&lInventory is full. Dropping items at your feet.");
                                }
                            } else {
                                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1F);
                                p.getInventory().addItem(randomDrop);
                            }
                            break;
                    }

                    e.setCancelled(true);
                }
            }
        }

    }
}

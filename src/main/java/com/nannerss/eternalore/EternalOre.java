package com.nannerss.eternalore;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.nannerss.eternalore.commands.EternalOreCommand;
import com.nannerss.eternalore.data.Ore;
import com.nannerss.eternalore.data.Settings;
import com.nannerss.eternalore.lib.ConfigManager;
import com.nannerss.eternalore.lib.Registrar;
import com.nannerss.eternalore.listeners.OreMineListener;
import com.nannerss.eternalore.listeners.WandListener;
import com.nannerss.eternalore.tasks.DataSaveTask;
import com.nannerss.eternalore.tasks.OreRegenTask;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class EternalOre extends JavaPlugin implements Listener {

    @Getter
    private static EternalOre instance;

    @Getter
    private static ConfigManager settings;

    @Getter
    private static ConfigManager ores;

    @Getter
    private static final Cache<String, Ore> oresCache = CacheBuilder.newBuilder().maximumSize(10000).build();

    @Override
    public void onEnable() {
        instance = this;

        settings = new ConfigManager("settings.yml", true);
        Settings.load();
        ores = new ConfigManager("ores.dat", false);

        Registrar.registerCommand(new EternalOreCommand());
        getServer().getPluginManager().registerEvents(new WandListener(), this);
        getServer().getPluginManager().registerEvents(new OreMineListener(), this);
        //getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(this, this);

        ConfigurationSection section = ores.getConfigurationSection("ores");
        if (section == null) {
            section = ores.createSection("ores");
            ores.saveConfig();
        }

        for (String key : section.getKeys(false)) {
            getCache(key);
        }

        new OreRegenTask().runTaskTimer(this, 200, 20);
        new DataSaveTask().runTaskTimer(this, 200, 20 * 60);

        /* Disable, custom fork
        new Updater(68180) {

            @Override
            public void onUpdateAvailable() {
                Console.log(getConsoleUpdateMessage());
            }

        }.runTaskAsynchronously(this);

        new Metrics(this);
         */
    }

    @Override
    public void onDisable() {
        instance = null;

        for (final Ore cache : oresCache.asMap().values()) {
            cache.save();
        }

        ores.saveConfig();

        oresCache.invalidateAll();

        getServer().getScheduler().cancelTasks(this);
    }

    @EventHandler
    public void onWorldLoad(WorldLoadEvent event) {
        ConfigurationSection section = ores.getConfigurationSection("ores");
        for (String key : section.getKeys(false)) {
            if(!key.startsWith(event.getWorld().getName() + "!")) {
                continue;
            }
            getCache(key);
        }
    }

    public static Ore getCache(String id) {
        Ore cache = oresCache.getIfPresent(id);
        if (cache == null) {
            String worldName = id.split("!")[0];
            if(Bukkit.getWorld(worldName) == null) {
                return null;
            }
            cache = new Ore(id);
            oresCache.put(id, cache);
        }
        return cache;
    }
}

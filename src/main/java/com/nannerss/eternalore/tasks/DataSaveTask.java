package com.nannerss.eternalore.tasks;

import com.nannerss.eternalore.EternalOre;
import com.nannerss.eternalore.data.Ore;
import com.nannerss.eternalore.lib.ConfigManager;
import org.bukkit.scheduler.BukkitRunnable;

public class DataSaveTask extends BukkitRunnable {

    @Override
    public void run() {
        final ConfigManager cfg = EternalOre.getOres();

        for (Ore ore : EternalOre.getOresCache().asMap().values()) {
            ore.save();
        }

        cfg.saveConfig();
    }

}

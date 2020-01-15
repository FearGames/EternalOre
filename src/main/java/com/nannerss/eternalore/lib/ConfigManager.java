package com.nannerss.eternalore.lib;

import com.nannerss.eternalore.EternalOre;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

public class ConfigManager extends YamlConfiguration {
    private final File file;
    private final YamlConfiguration defaults;

    public ConfigManager(String path) {
        this(path, true);
    }

    public ConfigManager(final String path, final boolean defaults) {
        if (defaults) {
            this.defaults = YamlConfiguration.loadConfiguration(new InputStreamReader(ConfigManager.class.getResourceAsStream("/" + path), StandardCharsets.UTF_8));
            Objects.requireNonNull(this.defaults, "Could not find the default file " + path + ".");
        } else {
            this.defaults = null;
        }
        this.file = this.extract(path);
        this.loadConfig();
    }

    public void reloadConfig() {
        this.saveConfig();
        this.loadConfig();
    }

    public void saveConfig() {
        try {
            super.save(this.file);
        } catch (IOException e) {
            System.out.println("Failed to save config " + this.file);
            e.printStackTrace();
        }
    }

    public void loadConfig() {
        try {
            super.load(this.file);
        } catch (Throwable e) {
            System.out.println("Failed to load config " + this.file);
            e.printStackTrace();
        }
    }

    private File extract(String path) {
        final JavaPlugin instance = EternalOre.getInstance();
        final File file = new File(instance.getDataFolder(), path);
        if (file.exists()) {
            return file;
        }
        this.createFileAndDirectory(path);
        if (this.defaults != null) {
            try (final InputStream is = instance.getResource(path)) {
                Objects.requireNonNull(is, "Resource file not found for " + path + ".");
                Files.copy(is, Paths.get(file.toURI()), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    private File createFileAndDirectory(final String path) {
        final File folder = EternalOre.getInstance().getDataFolder();
        final int lastIndex = path.lastIndexOf(47);
        final File directory = new File(folder, path.substring(0, Math.max(lastIndex, 0)));
        directory.mkdirs();
        final File destination = new File(folder, path);
        try {
            destination.createNewFile();
        } catch (IOException e) {
            System.out.println("Unable to create file " + path);
        }
        return destination;
    }
}

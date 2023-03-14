package net.tonimatasdev.devlib.api.config;

import net.tonimatasdev.devlib.api.DevPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class Config {
    private FileConfiguration config = null;
    private File configFile = null;
    private final String path;


    public Config(String path) {
        this.path = path;
    }

    public FileConfiguration getConfig(DevPlugin devPlugin) {
        if (config == null) {
            registerConfig(devPlugin);
        }

        return config;
    }

    public void reloadConfig(DevPlugin devPlugin) {
        if (config == null) {
            registerConfig(devPlugin);
        }

        config = YamlConfiguration.loadConfiguration(configFile);
        Reader defConfigStream = new InputStreamReader(Objects.requireNonNull(devPlugin.getResource(path)), StandardCharsets.UTF_8);
        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
        config.setDefaults(defConfig);
    }

    public void saveConfig() {
        try {
            config.save(configFile);
        } catch (IOException var2) {
            var2.printStackTrace();
        }
    }

    public void registerConfig(DevPlugin devPlugin) {
        configFile = new File(devPlugin.getDataFolder(), path);

        if (!configFile.exists()) {
            getConfig(devPlugin).options().copyDefaults(true);
            saveConfig();
        }
    }

    public String getPath() {
        return path;
    }
}

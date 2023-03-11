package net.tonimatasdev.devlib.api;

import net.tonimatasdev.devlib.metrics.BasicMetrics;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

public abstract class DevPlugin extends JavaPlugin implements IDevPlugin {
    @Override
    public void registerCommand(String name, CommandExecutor executor, TabCompleter completer) {
        Objects.requireNonNull(this.getCommand(name)).setExecutor(executor);

        if (completer != null) {
            Objects.requireNonNull(this.getCommand(name)).setTabCompleter(completer);
        }
    }

    @Override
    public void registerEvent(Listener listener) {
        Bukkit.getServer().getPluginManager().registerEvents(listener, this);
    }

    @Override
    public boolean checkUpdateSpigot(int resource) {
        boolean isOutdated = false;

        try {
            HttpURLConnection connection = (HttpURLConnection) (new URL("https://api.spigotmc.org/legacy/update.php?resource=" + resource)).openConnection();
            int timed_out = 1250;

            connection.setConnectTimeout(timed_out);
            connection.setReadTimeout(timed_out);

            String latestVersion = (new BufferedReader(new InputStreamReader(connection.getInputStream()))).readLine();

            int latestVersionNumbers = Integer.parseInt(latestVersion.replaceAll("\\.", ""));
            int pluginVersion = Integer.parseInt(this.getDescription().getVersion().replaceAll("\\.", ""));

            if (latestVersionNumbers > pluginVersion) {
                isOutdated = true;
            }
        } catch (Exception ignored) {
        }

        return isOutdated;
    }

    @Override
    public void bStatsMetrics(int id) {
        new BasicMetrics(this, id);
    }
}

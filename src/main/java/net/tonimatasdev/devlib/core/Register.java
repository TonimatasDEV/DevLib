package net.tonimatasdev.devlib.core;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class Register {
    public static void command(JavaPlugin instance, String name, CommandExecutor executor, TabCompleter completer) {
        Objects.requireNonNull(instance.getCommand(name)).setExecutor(executor);
        Objects.requireNonNull(instance.getCommand(name)).setTabCompleter(completer);
    }

    public static void event(JavaPlugin instance, Listener listener) {
        Bukkit.getServer().getPluginManager().registerEvents(listener, instance);
    }
}

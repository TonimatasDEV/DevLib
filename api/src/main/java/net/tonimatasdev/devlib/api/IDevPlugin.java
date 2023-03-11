package net.tonimatasdev.devlib.api;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.Listener;

public interface IDevPlugin {
    void registerCommand(String name, CommandExecutor executor, TabCompleter completer);
    void registerEvent(Listener listener);
    boolean checkUpdateSpigot(int resource);
    void bStatsMetrics(int id);
}

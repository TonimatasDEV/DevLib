package net.tonimatasdev.devlib.command;

import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public abstract class SubCommand {
    public abstract String getName();

    public abstract ArrayList<SubCommand> getSubCommands();

    public abstract String getPermission();

    public abstract int getPosition();

    public abstract boolean execute(CommandSender sender, org.bukkit.command.Command command, String label, String[] args);
}

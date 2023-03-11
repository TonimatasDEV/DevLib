package net.tonimatasdev.devlib.api.command;

import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public interface ICommand {
    String getName();
    ArrayList<SubCommand> getSubCommands();

    String getPermission();

    String getNoPermissionMessage();
    boolean execute(CommandSender sender, org.bukkit.command.Command command, String label, String[] args);
}

package net.tonimatasdev.devlib.api.command;

import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public interface ISubCommand {
    String getName();

    ArrayList<SubCommand> getSubCommands();

    String getPermission();

    int getPosition();

    boolean execute(CommandSender sender, org.bukkit.command.Command command, String label, String[] args);

}

package net.tonimatasdev.devlib.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public abstract class SubCommand {
    abstract ArrayList<SubCommand> getSubCommands();

    abstract int getPosition();

    abstract String getName();

    void execute(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != getPosition() + 1) {
            execute(sender, command, label, args);
        } else {
            for (SubCommand subCommand : getSubCommands()) {
                if (subCommand != null) {
                    if (subCommand.getName().equalsIgnoreCase(args[getPosition()])) {
                        subCommand.execute(sender, command, label, args);
                    }
                }
            }
        }
    }
}

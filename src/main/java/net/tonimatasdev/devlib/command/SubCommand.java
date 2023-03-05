package net.tonimatasdev.devlib.command;

import net.tonimatasdev.devlib.util.PermissionUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public abstract class SubCommand {
    public abstract String getName();

    public abstract ArrayList<SubCommand> getSubCommands();

    public abstract String getPermission();

    public abstract int getPosition();

    public abstract boolean execute(CommandSender sender, Command command, String label, String[] args);


    public boolean onSubCommand(@NotNull CommandSender sender, Command command, @NotNull String label, String[] args, String permissionMessage) {
        if (args[getPosition()].equalsIgnoreCase(getName())) {
            if (PermissionUtils.check(sender, getPermission())) {
                if (getSubCommands().isEmpty()) {
                    return execute(sender, command, label, args);
                } else {
                    for (SubCommand subCommand : getSubCommands()) {
                        if (args[subCommand.getPosition()].equalsIgnoreCase(subCommand.getName())) {
                            return subCommand.onSubCommand(sender, command, label, args, permissionMessage);
                        }
                    }
                }
            }
        }

        return true;
    }
}

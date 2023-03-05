package net.tonimatasdev.devlib.command;

import net.tonimatasdev.devlib.util.PermissionUtils;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public abstract class Command implements CommandExecutor {
    public abstract String getName();
    public abstract ArrayList<SubCommand> getSubCommands();

    public abstract String getPermission();

    public abstract boolean execute(CommandSender sender, org.bukkit.command.Command command, String label, String[] args);

    @Override
    public boolean onCommand(@NotNull CommandSender sender, org.bukkit.command.Command command, @NotNull String label, String[] args) {
        if (command.getName().equalsIgnoreCase(getName())) {
            if (PermissionUtils.check(sender, getPermission())) {
                if (getSubCommands().isEmpty() || args.length == 0) {
                    return execute(sender, command, label, args);
                } else {
                    for (SubCommand subCommand : getSubCommands()) {
                        if (args.length == subCommand.getPosition() + 1) {
                            if (args[subCommand.getPosition()].equalsIgnoreCase(subCommand.getName())) {
                                return subCommand.execute(sender, command, label, args);
                            }
                        }
                    }
                }
            }
        }

        return true;
    }
}

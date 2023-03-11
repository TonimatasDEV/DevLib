package net.tonimatasdev.devlib.api.command;

import net.tonimatasdev.devlib.api.util.PermissionUtils;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public abstract class SubCommand implements ISubCommand {

    public boolean onSubCommand(@NotNull CommandSender sender, org.bukkit.command.Command command, @NotNull String label, String[] args, String permissionMessage) {
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

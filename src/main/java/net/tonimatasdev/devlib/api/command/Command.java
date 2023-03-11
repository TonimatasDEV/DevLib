package net.tonimatasdev.devlib.api.command;

import net.tonimatasdev.devlib.api.util.PermissionUtils;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public abstract class Command implements CommandExecutor, ICommand {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, org.bukkit.command.Command command, @NotNull String label, String[] args) {
        if (command.getName().equalsIgnoreCase(getName())) {
            if (PermissionUtils.check(sender, getPermission())) {
                if (getSubCommands().isEmpty() || args.length == 0) {
                    return execute(sender, command, label, args);
                } else {
                    for (SubCommand subCommand : getSubCommands()) {
                        if (args[subCommand.getPosition()].equalsIgnoreCase(subCommand.getName())) {
                            return subCommand.onSubCommand(sender, command, label, args, getNoPermissionMessage());
                        }
                    }
                }
            }
        }

        return true;
    }
}

package net.tonimatasdev.devlib.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public abstract class DevCommandExecutor implements CommandExecutor {
    private final ArrayList<SubCommand> subCommands = new ArrayList<>();

    public DevCommandExecutor() {

    }

    public abstract ArrayList<SubCommand> getSubCommands();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            execute();
        } else {
            for (SubCommand subCommand : getSubCommands()) {
                if (subCommand.getName().equalsIgnoreCase(args[subCommand.getPosition()])) {
                    subCommand.execute(sender, command, label, args);
                }
            }
        }

        return true;
    }

    public abstract void execute();
}

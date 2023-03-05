package net.tonimatasdev.devlib.util;

import org.bukkit.command.CommandSender;

public class PermissionUtils {
    public static boolean check(CommandSender sender, String permission) {
        if (permission != null) {
            return sender.hasPermission(permission);
        } else {
            return true;
        }
    }
}

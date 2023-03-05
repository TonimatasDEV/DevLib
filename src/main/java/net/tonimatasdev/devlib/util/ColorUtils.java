package net.tonimatasdev.devlib.util;

import org.bukkit.ChatColor;

public class ColorUtils {
    public static String translateHexColorCodes(String hexText) {
        return hexText.replaceAll("#([A-Fa-f0-9]{6})", ChatColor.COLOR_CHAR + "x$1");
    }
}

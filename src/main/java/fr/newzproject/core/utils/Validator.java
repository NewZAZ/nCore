package fr.newzproject.core.utils;

import org.bukkit.Bukkit;

public class Validator {

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static boolean isOnlinePlayer(String s) {
        return Bukkit.getPlayer(s) != null;
    }

    public static boolean isOfflinePlayer(String s) {
        return Bukkit.getOfflinePlayer(s) != null;
    }

    public static boolean isPlayer(String s) {
        return isOnlinePlayer(s) || isOfflinePlayer(s);
    }
}

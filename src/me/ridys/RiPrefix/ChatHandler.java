package me.ridys.RiPrefix;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

class ChatHandler {

    static void meCMD(OfflinePlayer p, String prefix) {
        VaultHook.chat.setPlayerPrefix(null, p, prefix);
    }

    static void clearCMD(OfflinePlayer p) {
        VaultHook.chat.setPlayerPrefix(null, p, "");
    }

    static boolean setCMD(String p, String prefix) {
        OfflinePlayer player = Bukkit.getPlayer(p);
        try {
            VaultHook.chat.setPlayerPrefix(null, player, prefix);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    static boolean resetCMD(String p) {
        OfflinePlayer player = Bukkit.getPlayer(p);
        try {
            VaultHook.chat.setPlayerPrefix(null, player, "");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
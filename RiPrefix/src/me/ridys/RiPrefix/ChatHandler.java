package me.ridys.RiPrefix;

import org.bukkit.entity.Player;
import me.ridys.RiPrefix.VaultHook;

public class ChatHandler {
	
	public static boolean meCMD(Player p, String prefix) {
		if (VaultHook.chat.getPlayerPrefix(p).length() == 0) {
			return true;
		} else {
			return false;
		}
	}

}

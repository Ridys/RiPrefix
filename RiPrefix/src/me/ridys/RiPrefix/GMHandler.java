package me.ridys.RiPrefix;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import org.anjocaido.groupmanager.GroupManager;
import org.anjocaido.groupmanager.data.User;
import org.anjocaido.groupmanager.dataholder.OverloadedWorldHolder;

public class GMHandler {

	private static User getGMUser(Player player){
        Plugin gm_plugin = Bukkit.getServer().getPluginManager().getPlugin("GroupManager");
        if (gm_plugin instanceof GroupManager) {
            GroupManager gm = (GroupManager) gm_plugin;
            OverloadedWorldHolder world = gm.getWorldsHolder().getWorldDataByPlayerName(player.getName());
            return world.getUser(player.getName());
        }
        return null;
    }
	
    private static String getUserPrefix(Player player) {
        User gm_user = getGMUser(player);
        return gm_user.getVariables().getVarString("prefix");
    }
	
	public static boolean setGM(Player player, String prefix) {
		if (getUserPrefix(player).length() == 0) {
			User gm_user = getGMUser(player);
	        gm_user.getVariables().addVar("prefix", prefix);
	        return true;
		} else {
			User gm_user = getGMUser(player);
			gm_user.getVariables().removeVar("prefix");
			return false;
		}
	}

	public static void setGMop(String p, String prefix) {
		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
		String command = "manuaddv " + p + " prefix " + prefix;
		Bukkit.dispatchCommand(console, command);
	}

	public static void resetGM(String p) {
		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
		String command = "manudelv " + p + " prefix";
		Bukkit.dispatchCommand(console, command);
	}

}

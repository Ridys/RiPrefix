package me.ridys.RiPrefix;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class RiPrefix extends JavaPlugin {
	Logger log = getLogger();

	public void onEnable(){
		log.info("RiPrefix ready!");
	}

	public void onDisable(){
		log.info("RiPrefix disabled!");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		Player player = null;
		if (sender instanceof Player) {
			player = (Player) sender;
		}
		if(cmd.getName().equalsIgnoreCase("rips")){
			if (player == null) {
				sender.sendMessage(ChatColor.DARK_RED + "This command can only be run by a player");	
			} else {
			player.sendMessage(ChatColor.GREEN + "[RiPrefix]" + ChatColor.BLUE + " Commands Help");
			player.sendMessage(ChatColor.GOLD + "/rips" + ChatColor.RED + " - This help page");
			player.sendMessage(ChatColor.GOLD + "/rips [Prefix]" + ChatColor.RED + " - Set prefix for you");
			player.sendMessage(ChatColor.GOLD + "/rips set [Player] [Prefix]" + ChatColor.RED + " - Set player prefix");
			player.sendMessage(ChatColor.GOLD + "/rips reset [Player]" + ChatColor.RED + " - Reset player prefix");
			}
			return true;
		}
		return false;
	}
}
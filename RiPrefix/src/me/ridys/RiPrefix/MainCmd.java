package me.ridys.RiPrefix;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCmd implements CommandExecutor {
	@SuppressWarnings("unused")
	private RiPrefix plugin;
    public MainCmd(RiPrefix plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
        Player player = null;
        if (sender instanceof Player) {
            player = (Player) sender;
        }
        if (args.length == 0) {
	        if (player == null) {
	            sender.sendMessage(ChatColor.DARK_RED + "This command can only be run by a player");    
	        } else {
	            player.sendMessage(ChatColor.GREEN + "[RiPrefix]" + ChatColor.BLUE + " Commands Help");
	            player.sendMessage(ChatColor.GOLD + "/rips" + ChatColor.RED + " - This help page");
	            player.sendMessage(ChatColor.GOLD + "/rips me [Prefix]" + ChatColor.RED + " - Set prefix for you");
	            player.sendMessage(ChatColor.GOLD + "/rips set [Player] [Prefix]" + ChatColor.RED + " - Set player prefix");
	            player.sendMessage(ChatColor.GOLD + "/rips reset [Player]" + ChatColor.RED + " - Reset player prefix");
	        }
	        return true;
        }
        if (args[0].equalsIgnoreCase("me") && args.length > 1 && args[1] != null && player.hasPermission("riprefix.me")) {
	        player.sendMessage("Me command: " + args[1]);
	        return true;
        }
        if (args[0].equalsIgnoreCase("set") && args.length > 2 && args[1] != null && args[2] != null) {
        	if (player == null) {
        		sender.sendMessage("Set command: " + args[1] + args[2]);
        	} else {
        		if (player.hasPermission("riprefix.set")) {
        			sender.sendMessage("Set command: " + args[1] + args[2]);
        		} else {
        			sender.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
        		}
        	}
	        return true;
        }
        if (args[0].equalsIgnoreCase("reset") && args.length > 1 && args[1] != null) {
        	if (player == null) {
        		sender.sendMessage("Reset command: " + args[1]);
        	} else {
        		if (player.hasPermission("riprefix.reset")) {
        			sender.sendMessage("Reset command: " + args[1]);
        		} else {
        			sender.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
        		}
        	}
	        return true;
        }
		return false;
    }
}
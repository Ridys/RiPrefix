package me.ridys.RiPrefix;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class MainCmd implements CommandExecutor {
	private RiPrefix plugin;
    public MainCmd(RiPrefix plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
        Player player = null;
        Integer m = plugin.getConfig().getInt("main.mode");
        Boolean ct = plugin.getConfig().getBoolean("main.coloredtags");
        String p = plugin.getConfig().getString("lang.perm");
        String o = plugin.getConfig().getString("lang.player_only");
        if (sender instanceof Player) {
            player = (Player) sender;
        }
        if (args.length == 0) {
	        if (player == null) {
	            sender.sendMessage(ChatColor.DARK_RED + o);    
	        } else {
	            player.sendMessage(ChatColor.GREEN + "[RiPrefix] " + ChatColor.BLUE + plugin.getConfig().getString("lang.help_title"));
	            player.sendMessage(ChatColor.GOLD + "/rips " + ChatColor.RED + plugin.getConfig().getString("lang.help_help"));
	            player.sendMessage(ChatColor.GOLD + "/rips me [Prefix] " + ChatColor.RED + plugin.getConfig().getString("lang.help_me"));
	            player.sendMessage(ChatColor.GOLD + "/rips set [Player] [Prefix] " + ChatColor.RED + plugin.getConfig().getString("lang.help_set"));
	            player.sendMessage(ChatColor.GOLD + "/rips reset [Player] " + ChatColor.RED + plugin.getConfig().getString("lang.help_reset"));
	        }
	        return true;
        }
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        if (args[0].equalsIgnoreCase("me") && args.length > 1 && args[1] != null && player.hasPermission("riprefix.me")) {
        	switch (m) {
        		case 0:  Bukkit.dispatchCommand(console, "pex user " + player.getName() + " set prefix " + args[1]);
        				 if(ct) { CTagsH.setCTag(player, args[1]); }
        	             break;
        	    case 1:  sender.sendMessage("Me command mode 1: " + args[1]);
        	    		 if(ct) { sender.sendMessage("Ctags ON"); }
        	             break;
        	    default: sender.sendMessage(ChatColor.RED + "The mode of the plugin is not correct. Check the configuration.");
        	             break;
        	}
	        return true;
        }
        if (args[0].equalsIgnoreCase("set") && args.length > 2 && args[1] != null && args[2] != null) {
        	if (player == null) {
        		sender.sendMessage("Set command: " + args[1] + args[2]);
        	} else {
        		if (player.hasPermission("riprefix.set")) {
        			sender.sendMessage("Set command: " + args[1] + args[2]);
        		} else {
        			sender.sendMessage(ChatColor.RED + p);
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
        			sender.sendMessage(ChatColor.RED + p);
        		}
        	}
	        return true;
        }
		return false;
    }
}
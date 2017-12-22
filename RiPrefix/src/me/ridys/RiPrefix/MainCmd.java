package me.ridys.RiPrefix;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCmd implements CommandExecutor {
	private RiPrefix plugin;
    public MainCmd(RiPrefix plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
        Player player = null;
        String p = plugin.getConfig().getString("lang.perm");
        Boolean ct = plugin.getConfig().getBoolean("main.coloredtags");
        if (sender instanceof Player) {
            player = (Player) sender;
        }
        if (args.length == 0) {  
	        sender.sendMessage(ChatColor.GREEN + "[RiPrefix] " + ChatColor.BLUE + plugin.getConfig().getString("lang.help_title"));
	        sender.sendMessage(ChatColor.GOLD + "/rips " + ChatColor.RED + plugin.getConfig().getString("lang.help_help"));
	        sender.sendMessage(ChatColor.GOLD + "/rips clear " + ChatColor.RED + plugin.getConfig().getString("lang.help_clear"));
	        sender.sendMessage(ChatColor.GOLD + "/rips me [Prefix] " + ChatColor.RED + plugin.getConfig().getString("lang.help_me"));
	        sender.sendMessage(ChatColor.GOLD + "/rips set [Player] [Prefix] " + ChatColor.RED + plugin.getConfig().getString("lang.help_set"));
	        sender.sendMessage(ChatColor.GOLD + "/rips reset [Player] " + ChatColor.RED + plugin.getConfig().getString("lang.help_reset"));
	        sender.sendMessage(ChatColor.GOLD + "/rips reload " + ChatColor.RED + plugin.getConfig().getString("lang.help_reload"));
	        return true;
        }
        if (args[0].equalsIgnoreCase("me") && args.length > 1 && args[1] != null && player.hasPermission("riprefix.me")) {
        	ChatHandler.meCMD(player, args[1]);
        	if(ct) { CTagsHandler.setTabTag(player, args[1]); }
        	sender.sendMessage(plugin.getConfig().getString("lang.changed"));
	        return true;
        }
        if (args[0].equalsIgnoreCase("clear") && player.hasPermission("riprefix.clear")) {
        	ChatHandler.clearCMD(player);
        	if(ct) { CTagsHandler.removeTabTag(player); }
        	sender.sendMessage(plugin.getConfig().getString("lang.deleted"));
	        return true;
        }
        if (args[0].equalsIgnoreCase("set") && args.length > 2 && args[1] != null && args[2] != null) {
        	if (player == null) {
        		if (ChatHandler.setCMD(args[1], args[2])) {
        			if(ct) { CTagsHandler.setTabTag(args[1], args[2]); }
        			sender.sendMessage(plugin.getConfig().getString("lang.opchange"));
        		} else { sender.sendMessage(plugin.getConfig().getString("lang.corrupted")); }
            } else {
            	if (player.hasPermission("riprefix.set")) {
            		if (ChatHandler.setCMD(args[1], args[2])) {
            			if(ct) { CTagsHandler.setTabTag(args[1], args[2]); }
            			sender.sendMessage(plugin.getConfig().getString("lang.opchange"));
            		} else { sender.sendMessage(plugin.getConfig().getString("lang.corrupted")); }
        		} else {
        			sender.sendMessage(ChatColor.RED + p);
        		}
        	}
        	return true;
        }
        if (args[0].equalsIgnoreCase("reset") && args.length > 1 && args[1] != null) {
        	if (player == null) {
        		if (ChatHandler.resetCMD(args[1])) {
        			if(ct) { CTagsHandler.removeTabTag(args[1]); }
        			sender.sendMessage(plugin.getConfig().getString("lang.reset"));
        		} else { sender.sendMessage(plugin.getConfig().getString("lang.corrupted")); }
        	} else {
        		if (player.hasPermission("riprefix.reset")) {
            		if (ChatHandler.resetCMD(args[1])) {
            			if(ct) { CTagsHandler.removeTabTag(args[1]); }
            			sender.sendMessage(plugin.getConfig().getString("lang.reset"));
            		} else { sender.sendMessage(plugin.getConfig().getString("lang.corrupted")); }
        		} else {
        			sender.sendMessage(ChatColor.RED + p);
        		}
        	}
	        return true;
        }
        if (args[0].equalsIgnoreCase("reload")) {
        	if (player == null) {
        		plugin.reloadConfig();
        		sender.sendMessage(ChatColor.RED + "Plugin reloaded");
        	} else {
        		if (player.hasPermission("riprefix.reload")) {
        			plugin.reloadConfig();
            		sender.sendMessage(ChatColor.RED + "Plugin reloaded");
        		} else {
        			sender.sendMessage(ChatColor.RED + p);
        		}
        	}
        	return true;
        }
		return false;
    }
}
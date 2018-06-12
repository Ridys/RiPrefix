package me.ridys.RiPrefix;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class MainCmd implements CommandExecutor {
    private RiPrefix plugin;
    MainCmd(RiPrefix plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        Player player = null;
        String p = plugin.getConfig().getString("lang.perm");
        String c_left = plugin.getConfig().getString("main.left-chat-text");
        String c_right = plugin.getConfig().getString("main.right-chat-text");
        String t_left = plugin.getConfig().getString("main.left-tab-text");
        String t_right = plugin.getConfig().getString("main.right-tab-text");
        Boolean ct = plugin.getConfig().getBoolean("main.coloredtags");
        if (sender instanceof Player) {
            player = (Player) sender;
        }
        if (args.length == 0) {
            if (sender.hasPermission("riprefix.help")) {
                sender.sendMessage(ChatColor.GREEN + "[RiPrefix] " + ChatColor.BLUE + plugin.getConfig().getString("lang.help_title"));
                sender.sendMessage(ChatColor.GOLD + "/rips " + ChatColor.RED + plugin.getConfig().getString("lang.help_help"));
                if (sender.hasPermission("riprefix.clear")) {
                    sender.sendMessage(ChatColor.GOLD + "/rips clear " + ChatColor.RED + plugin.getConfig().getString("lang.help_clear"));
                }
                if (sender.hasPermission("riprefix.me")) {
                    sender.sendMessage(ChatColor.GOLD + "/rips me <prefix> " + ChatColor.RED + plugin.getConfig().getString("lang.help_me"));
                }
                if (sender.hasPermission("riprefix.set")) {
                    sender.sendMessage(ChatColor.GOLD + "/rips set <player> <prefix> " + ChatColor.RED + plugin.getConfig().getString("lang.help_set"));
                }
                if (sender.hasPermission("riprefix.reset")) {
                    sender.sendMessage(ChatColor.GOLD + "/rips reset [Player] " + ChatColor.RED + plugin.getConfig().getString("lang.help_reset"));
                }
                if (sender.hasPermission("riprefix.reload")) {
                    sender.sendMessage(ChatColor.GOLD + "/rips reload " + ChatColor.RED + plugin.getConfig().getString("lang.help_reload"));
                }
            } else {
                sender.sendMessage(ChatColor.RED + p);
            }
            return true;
        }
        if (args[0].equalsIgnoreCase("me") && args.length > 1 && args[1] != null && player.hasPermission("riprefix.me")) {
            if (checkIgnorePrefixes(args[1], sender) || checkPrefixLength(args[1], sender)) {
                return true;
            }
            String px = c_left + args[1] + c_right;
            String ct_px = t_left + args[1] + t_right;
            ChatHandler.meCMD(player, px);
            if(ct) { CTagsHandler.setTabTag(player, ct_px); }
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
            if (checkPrefixLength(args[2], sender)) {
                return true;
            }
            String px = c_left + args[2] + c_right;
            String ct_px = t_left + args[2] + t_right;
            if (player == null) {
                if (ChatHandler.setCMD(args[1], px)) {
                    if(ct) { CTagsHandler.setTabTag(args[1], ct_px); }
                    sender.sendMessage(plugin.getConfig().getString("lang.opchange"));
                } else { sender.sendMessage(plugin.getConfig().getString("lang.corrupted")); }
            } else {
                if (player.hasPermission("riprefix.set")) {
                    if (ChatHandler.setCMD(args[1], px)) {
                        if(ct) { CTagsHandler.setTabTag(args[1], ct_px); }
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

    private boolean checkIgnorePrefixes(String prefix, CommandSender sender) {
        prefix = prefix.toLowerCase();
        List<String> prefixes = plugin.getConfig().getStringList("misc.ignore-prefixes");
        for (String temp : prefixes) if (prefix.contains(temp.toLowerCase())) {
            sender.sendMessage(plugin.getConfig().getString("lang.forbidden"));
            return true;
        }
        return false;
    }

    private boolean checkPrefixLength(String prefix, CommandSender sender) {
        Integer min_length = plugin.getConfig().getInt("misc.min-prefix-length");
        Integer max_length = plugin.getConfig().getInt("misc.max-prefix-length");
        if (prefix.length() > max_length || prefix.length() < min_length) {
            sender.sendMessage(plugin.getConfig().getString("lang.length_check_failed"));
            return true;
        }
        return false;
    }
}
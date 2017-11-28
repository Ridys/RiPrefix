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
        if (sender instanceof Player) {
            player = (Player) sender;
        }
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
}
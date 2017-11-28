package me.ridys.RiPrefix;

import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
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
		if(cmd.getName().equalsIgnoreCase("rips")){
			return true;
		}
		return false; 
	}
}

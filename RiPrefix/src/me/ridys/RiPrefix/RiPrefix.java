package me.ridys.RiPrefix;

import java.io.File;
import java.util.logging.Logger;

import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public class RiPrefix extends JavaPlugin {
	Logger log = getLogger();

	private MainCmd MainCmdEx;
	@Override
	public void onEnable() {
		@SuppressWarnings("unused")
		Metrics metrics = new Metrics(this);
	    try {
	        if (!getDataFolder().exists()) {
	            getDataFolder().mkdirs();
	        }
	        File file = new File(getDataFolder(), "config.yml");
	        if (!file.exists()) {
	            saveDefaultConfig();
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		MainCmdEx = new MainCmd(this);
		getCommand("rips").setExecutor(MainCmdEx);
		log.info("RiPrefix ready!");
	}

	public void onDisable(){
		log.info("RiPrefix disabled!");
	}
}
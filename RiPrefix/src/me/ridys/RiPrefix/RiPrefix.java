package me.ridys.RiPrefix;

import java.io.File;
import java.util.logging.Logger;

import me.ridys.RiPrefix.VaultHook;

import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public class RiPrefix extends JavaPlugin {
	Logger log = getLogger();

	private MainCmd MainCmdEx;
	@SuppressWarnings("unused")
	@Override
	public void onEnable() {
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
        if(getServer().getPluginManager().getPlugin("Vault") != null) { 
			VaultHook hooker = new VaultHook(); 
			log.info("RiPrefix ready!");
        } else { 
            getPluginLoader().disablePlugin(this);
            log.severe("Vault plugin not found!");
        } 
	}

	public void onDisable(){
		log.info("RiPrefix disabled!");
	}
}
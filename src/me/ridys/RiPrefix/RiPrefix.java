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
		File file = new File(getDataFolder(), "config.yml");
        if (!file.exists()) {
            saveDefaultConfig();
        }
        Object configVersion = "1";
		if (!getConfig().getString("version").equals(configVersion)) {
			getConfig().options().copyDefaults(true);
			getConfig().set("version", configVersion);
	        saveConfig();
	        log.info("Config file has been updated to " + configVersion + " version!");
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
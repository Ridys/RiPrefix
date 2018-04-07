package me.ridys.RiPrefix;

import java.io.File;
import java.util.logging.Logger;

import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public class RiPrefix extends JavaPlugin {
	private Logger log = getLogger();

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
        MainCmd mainCmdEx = new MainCmd(this);
		getCommand("rips").setExecutor(mainCmdEx);
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
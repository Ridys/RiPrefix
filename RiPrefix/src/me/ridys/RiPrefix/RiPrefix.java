package me.ridys.RiPrefix;

import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

public class RiPrefix extends JavaPlugin {
	Logger log = getLogger();

	private MainCmd MainCmdEx;
	@Override
	public void onEnable() {
		MainCmdEx = new MainCmd(this);
		getCommand("rips").setExecutor(MainCmdEx);
		log.info("RiPrefix ready!");
	}

	public void onDisable(){
		log.info("RiPrefix disabled!");
	}
}
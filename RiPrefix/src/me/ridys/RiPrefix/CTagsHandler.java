package me.ridys.RiPrefix;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.gmail.filoghost.coloredtags.ColoredTags;
import com.gmail.filoghost.coloredtags.ScoreboardHandler;
import com.gmail.filoghost.coloredtags.TeamData;

public class CTagsHandler {

	public static void setTabTag(Player p, String prefix) {
		TeamData data = TeamData.fromString(prefix);
        ColoredTags.playersConfig.set(p.getName(),data.getPrefix());
        ColoredTags.playersConfig.trySave();
        ColoredTags.playersMap.put(p.getName().toLowerCase(), data);
        ScoreboardHandler.setPrefixSuffix(p, data);
        ColoredTags.updateNametag(p);
        ColoredTags.updateTab(p);
	}

	public static void setTabTag(String player, String prefix) {
		Player p = Bukkit.getPlayer(player);
		TeamData data = TeamData.fromString(prefix);
        ColoredTags.playersConfig.set(p.getName(),data.getPrefix());
        ColoredTags.playersConfig.trySave();
        ColoredTags.playersMap.put(p.getName().toLowerCase(), data);
        ScoreboardHandler.setPrefixSuffix(p, data);
        ColoredTags.updateNametag(p);
        ColoredTags.updateTab(p);
	}
	
	public static void removeTabTag(Player p) {
        ColoredTags.playersConfig.set(p.getName(),null);
        ColoredTags.playersConfig.trySave();
        ColoredTags.playersMap.remove(p.getName().toLowerCase());
        ScoreboardHandler.removeFromTeams(p);
        ColoredTags.updateNametag(p);
        ColoredTags.updateTab(p);
	}

	public static void removeTabTag(String player) {
		Player p = Bukkit.getPlayer(player);
        ColoredTags.playersConfig.set(p.getName(),null);
        ColoredTags.playersConfig.trySave();
        ColoredTags.playersMap.remove(p.getName().toLowerCase());
        ScoreboardHandler.removeFromTeams(p);
        ColoredTags.updateNametag(p);
        ColoredTags.updateTab(p);
	}
	
}

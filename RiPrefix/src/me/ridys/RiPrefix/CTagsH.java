package me.ridys.RiPrefix;

import org.bukkit.entity.Player;

import com.gmail.filoghost.coloredtags.ColoredTags;
import com.gmail.filoghost.coloredtags.ScoreboardHandler;
import com.gmail.filoghost.coloredtags.TeamData;

public class CTagsH {

	public static void setCTag(Player p, String prefix) {
        Boolean b = ColoredTags.playersConfig.isSet(p.getName());
        final TeamData data = b==false?TeamData.fromString(prefix):null;
        if (data == null) {
            ColoredTags.playersConfig.set(p.getName(),null);
            ColoredTags.playersConfig.trySave();
            ColoredTags.playersMap.remove(p.getName().toLowerCase());
            ScoreboardHandler.removeFromTeams(p);
        } else {
            ColoredTags.playersConfig.set(p.getName(),data.getPrefix());
            ColoredTags.playersConfig.trySave();
            ColoredTags.playersMap.put(p.getName().toLowerCase(), data);
            ScoreboardHandler.setPrefixSuffix(p, data);
        }
        ColoredTags.updateNametag(p);
        ColoredTags.updateTab(p);
        p.sendMessage(b==false?"§aПрефикс в TAB установлен.": "§aПрефикс в TAB удалён.");
    }

}

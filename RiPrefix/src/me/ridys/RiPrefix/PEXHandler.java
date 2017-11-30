package me.ridys.RiPrefix;

import org.bukkit.entity.Player;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class PEXHandler {
	
	public static void setPEX(Player p, String prefix) {
		PermissionUser user = PermissionsEx.getUser(p);
		if(user.getPrefix(null).length() == 0) {
			user.setPrefix(prefix, null);
			p.sendMessage("§aPrefix succesfully set.");
		} else {
			user.setPrefix("", null);
			p.sendMessage("§dPrefix removed.");
		}
	}

}

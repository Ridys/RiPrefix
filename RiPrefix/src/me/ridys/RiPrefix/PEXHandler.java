package me.ridys.RiPrefix;

import org.bukkit.entity.Player;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class PEXHandler {
	public static boolean setPEX(Player p, String prefix) {
		PermissionUser user = PermissionsEx.getUser(p);
		if(user.getPrefix(null).length() == 0) {
			user.setPrefix(prefix, null);
			return true;
		} else {
			user.setPrefix("", null);
			return false;
		}
	}

	public static void setPEXop(String p, String prefix) {
		PermissionUser user = PermissionsEx.getUser(p);
		user.setPrefix(prefix, null);
	}

	public static void resetPEX(String p) {
		PermissionUser user = PermissionsEx.getUser(p);
		user.setPrefix("", null);
	}
}

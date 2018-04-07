package me.ridys.RiPrefix;

import net.milkbowl.vault.chat.Chat; 
import net.milkbowl.vault.economy.Economy; 
import net.milkbowl.vault.permission.Permission; 
import org.bukkit.Bukkit; 
import org.bukkit.plugin.RegisteredServiceProvider; 

public class VaultHook {

    public static Permission permission = null; 
    public static Economy economy = null; 
    public static Chat chat = null; 
 
    public VaultHook() { 
        setupChat(); 
        setupEconomy(); 
        setupPermissions(); 
    } 
 
    private boolean setupPermissions() 
    { 
        RegisteredServiceProvider<Permission> permissionProvider = Bukkit.getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class); 
        if (permissionProvider != null) { 
            permission = permissionProvider.getProvider(); 
        } 
        return (permission != null); 
    } 
 
    private boolean setupChat() 
    { 
        RegisteredServiceProvider<Chat> chatProvider = Bukkit.getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class); 
        if (chatProvider != null) { 
            chat = chatProvider.getProvider(); 
        } 
 
        return (chat != null); 
    } 
 
    private boolean setupEconomy() 
    { 
        RegisteredServiceProvider<Economy> economyProvider = Bukkit.getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class); 
        if (economyProvider != null) { 
            economy = economyProvider.getProvider(); 
        } 
 
        return (economy != null); 
    } 
	
}

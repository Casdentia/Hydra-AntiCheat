package me.exslodingdogs.hydra.Check;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class CheckManager {
	
	private String NameOfHack;
	private boolean banable;
	
	public CheckManager(String Hack, boolean banable) {
		this.NameOfHack = Hack;
		this.banable = banable;
	}
	public boolean IsBanable() {
		return this.banable;
	}
	
	public String GetHack() {
		return this.NameOfHack;
	}
	
	public static String prefix = "&8[&3&lHYDRA&r&8] &f";
	public void flag(Player player, int flags) {
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "" + player.getName() + " &7failed &3" + GetHack() + " &cx" + flags));
		for(Player op : Bukkit.getOnlinePlayers()) {
			if(op.hasPermission("hydra.alerts")) {
				op.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "" + player.getName() + " &7failed &3" + GetHack() + " &cx" + flags));
			}
		}
		/*if(flags > 10 && IsBanable()) {
			player.kickPlayer("Unfair Advantage");
			Bukkit.getServer().getBanList(BanList.Type.NAME).addBan(player.getName(), null, null, "Unfair Advantage");
		}*/
	}
	
	
	
}

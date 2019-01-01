package me.exslodingdogs.hydra.Menus;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.exslodingdogs.hydra.HydraAC;

public class MainMenu implements Listener{
	
	@EventHandler
	public void onmenuclick(InventoryClickEvent event) {
		Inventory inv = event.getClickedInventory();
		if(inv == null)return;
		if(inv.getName().equals(ChatColor.translateAlternateColorCodes('&', "&3&lHydra AntiCheat"))) {
			Player player = (Player) event.getWhoClicked();
			ItemStack item = event.getCurrentItem();
			event.setCancelled(true);
			
			if(item == null || !item.hasItemMeta()) {
				return;
			}
			
			if(item.getType() == Material.BOOK && item.getItemMeta().getDisplayName().equals(ChatColor.DARK_AQUA + "Check Manager")) {
				CheckManagerMenu.openmenu(player);
			}
			
			if(item.getType() == Material.BOOK && item.getItemMeta().getDisplayName().equals(ChatColor.DARK_AQUA + "Discord")) {
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&3&lHydra&8] &7Discord Link : https://discord.gg/WtrSa3K"));
			}
			
		}
	}
	
	public static void openmenu(Player player) {
		Inventory menu = Bukkit.createInventory(null, 27, ChatColor.translateAlternateColorCodes('&', "&3&lHydra AntiCheat"));
		
		ItemStack info = new ItemStack(Material.BOOK);
		ItemMeta infometa = info.getItemMeta();
		infometa.setDisplayName(ChatColor.DARK_AQUA + "Infomation");
		ArrayList<String> infolore = new ArrayList<>();
		infolore.add(ChatColor.GRAY + "Developers : " + ChatColor.AQUA + "ExslodingDogs, Tre, Jonhan");
		infolore.add(ChatColor.GRAY + "Version : " + ChatColor.AQUA + HydraAC.getPlugin(HydraAC.class).getDescription().getVersion());
		infometa.setLore(infolore);
		info.setItemMeta(infometa);
		
		ItemStack Checks = new ItemStack(Material.BOOK);
		ItemMeta checkmeta = Checks.getItemMeta();
		checkmeta.setDisplayName(ChatColor.DARK_AQUA + "Check Manager");
		ArrayList<String> checklore = new ArrayList<>();
		checklore.add(ChatColor.GRAY + "(Click To Open)");
		checkmeta.setLore(checklore);
		Checks.setItemMeta(checkmeta);
		
		ItemStack discord = new ItemStack(Material.BOOK);
		ItemMeta cmeta = discord.getItemMeta();
		cmeta.setDisplayName(ChatColor.DARK_AQUA + "Discord");
		checklore.clear();
		checklore.add(ChatColor.GRAY + "Link : https://discord.gg/WtrSa3K");
		cmeta.setLore(checklore);
		discord.setItemMeta(cmeta);
		
		menu.setItem(10, Checks);
		menu.setItem(13, info);
		menu.setItem(16, discord);
		
		
		player.openInventory(menu);
	}
	
}

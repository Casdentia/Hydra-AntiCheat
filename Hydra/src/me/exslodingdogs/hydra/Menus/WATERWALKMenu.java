package me.exslodingdogs.hydra.Menus;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.exslodingdogs.hydra.HydraAC;
import net.md_5.bungee.api.ChatColor;

public class WATERWALKMenu implements Listener{
	
	
	private static HydraAC plugin = HydraAC.getPlugin(HydraAC.class);
	@EventHandler
	public void onmenuclick(InventoryClickEvent event) {
		Inventory inv = event.getClickedInventory();
		if(inv == null)return;
		if(inv.getName().equals(ChatColor.translateAlternateColorCodes('&', "&3&lHydra WATERWALK"))) {
			Player player = (Player) event.getWhoClicked();
			ItemStack item = event.getCurrentItem();
			event.setCancelled(true);
			
			if(item == null || !item.hasItemMeta()) {
				return;
			}
			
			if(item.getType() == Material.REDSTONE_BLOCK && item.getItemMeta().getDisplayName().equals(ChatColor.RED + "Back")) {
				CheckManagerMenu.openmenu(player);
			}
			
			//TypeA
			if(item.getType() == Material.BOOK && item.getItemMeta().getDisplayName().equals(ChatColor.GRAY + "TypeA")) {
				if(plugin.getConfig().getBoolean("Checks.WATERWALK.TypeA.Enabled") == true) {
					plugin.getConfig().set("Checks.WATERWALK.TypeA.Enabled", false);
					plugin.saveConfig();
					ItemMeta Ameta = item.getItemMeta();
					Ameta.setDisplayName(ChatColor.GRAY + "TypeA");
					Ameta.removeEnchant(Enchantment.SILK_TOUCH);
					item.setItemMeta(Ameta);
				}else {
					plugin.getConfig().set("Checks.WATERWALK.TypeA.Enabled", true);
					plugin.saveConfig();
					ItemMeta Ameta = item.getItemMeta();
					Ameta.addEnchant(Enchantment.SILK_TOUCH, 0, true);
					Ameta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
					item.setItemMeta(Ameta);
				}
			}
			//TypeB
			if(item.getType() == Material.BOOK && item.getItemMeta().getDisplayName().equals(ChatColor.GRAY + "TypeB")) {
				if(plugin.getConfig().getBoolean("Checks.WATERWALK.TypeB.Enabled") == true) {
					plugin.getConfig().set("Checks.WATERWALK.TypeB.Enabled", false);
					plugin.saveConfig();
					ItemMeta Ameta = item.getItemMeta();
					Ameta.setDisplayName(ChatColor.GRAY + "TypeB");
					Ameta.removeEnchant(Enchantment.SILK_TOUCH);
					item.setItemMeta(Ameta);
				}else {
					plugin.getConfig().set("Checks.WATERWALK.TypeB.Enabled", true);
					plugin.saveConfig();
					ItemMeta Ameta = item.getItemMeta();
					Ameta.addEnchant(Enchantment.SILK_TOUCH, 0, true);
					Ameta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
					item.setItemMeta(Ameta);
				}
			}
		}
	}
	

	public static void openmenu(Player player) {
		
		Inventory inv = Bukkit.createInventory(null, 27, ChatColor.translateAlternateColorCodes('&', "&3&lHydra WATERWALK"));
		
		ItemStack back = new ItemStack(Material.REDSTONE_BLOCK);
		ItemMeta bmeta = back.getItemMeta();
		bmeta.setDisplayName(ChatColor.RED + "Back");
		bmeta.addEnchant(Enchantment.SILK_TOUCH, 0, true);
		bmeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		bmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		back.setItemMeta(bmeta);
		
		ItemStack A = new ItemStack(Material.BOOK);
		ItemMeta Ameta = A.getItemMeta();
		Ameta.setDisplayName(ChatColor.GRAY + "TypeA");
		if(plugin.getConfig().getBoolean("Checks.WATERWALK.TypeA.Enabled") == true) {
			Ameta.addEnchant(Enchantment.SILK_TOUCH, 0, true);
			Ameta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		}
		A.setItemMeta(Ameta);

		ItemStack B = new ItemStack(Material.BOOK);
		ItemMeta BmetB = B.getItemMeta();
		BmetB.setDisplayName(ChatColor.GRAY + "TypeB");
		if(plugin.getConfig().getBoolean("Checks.WATERWALK.TypeB.Enabled") == true) {
			BmetB.addEnchant(Enchantment.SILK_TOUCH, 0, true);
			BmetB.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		}
		B.setItemMeta(BmetB);
		
	
		inv.setItem(0, A);
		inv.setItem(1, B);
		inv.setItem(26, back);
		
		
		
		player.openInventory(inv);
	}

}

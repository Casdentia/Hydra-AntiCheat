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

import net.md_5.bungee.api.ChatColor;

public class CheckManagerMenu implements Listener{
	
	public static void openmenu(Player player) {
		
		Inventory inv = Bukkit.createInventory(null, 27, ChatColor.translateAlternateColorCodes('&', "&3&lHydra CheckManager"));
		
		ItemStack back = new ItemStack(Material.REDSTONE_BLOCK);
		ItemMeta bmeta = back.getItemMeta();
		bmeta.setDisplayName(ChatColor.RED + "Back");
		bmeta.addEnchant(Enchantment.SILK_TOUCH, 0, true);
		bmeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		bmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		back.setItemMeta(bmeta);
		
		ItemStack SPEED = new ItemStack(Material.BOOK);
		ItemMeta speedmeta = SPEED.getItemMeta();
		speedmeta.setDisplayName(ChatColor.DARK_AQUA + "SPEED");
		SPEED.setItemMeta(speedmeta);
		
		ItemStack FLY = new ItemStack(Material.BOOK);
		ItemMeta flymeta = FLY.getItemMeta();
		flymeta.setDisplayName(ChatColor.DARK_AQUA + "FLY");
		FLY.setItemMeta(flymeta);
		
		ItemStack NOFALL = new ItemStack(Material.BOOK);
		ItemMeta fallmeta = NOFALL.getItemMeta();
		fallmeta.setDisplayName(ChatColor.DARK_AQUA + "NOFALL");
		NOFALL.setItemMeta(fallmeta);
		
		ItemStack WATERWALK = new ItemStack(Material.BOOK);
		ItemMeta watermeta = WATERWALK.getItemMeta();
		watermeta.setDisplayName(ChatColor.DARK_AQUA + "WATERWALK");
		WATERWALK.setItemMeta(watermeta);
		
		ItemStack BADMOVE = new ItemStack(Material.BOOK);
		ItemMeta bmmeta = BADMOVE.getItemMeta();
		bmmeta.setDisplayName(ChatColor.DARK_AQUA + "BADMOVE");
		BADMOVE.setItemMeta(bmmeta);
		
		ItemStack Item = new ItemStack(Material.BOOK);
		ItemMeta IMeta = Item.getItemMeta();
		IMeta.setDisplayName(ChatColor.DARK_AQUA + "KILLAURA");
		Item.setItemMeta(IMeta);
		inv.setItem(5, Item);
		
		IMeta.setDisplayName(ChatColor.DARK_AQUA + "REACH");
		Item.setItemMeta(IMeta);
		inv.setItem(6, Item);
		
		IMeta.setDisplayName(ChatColor.DARK_AQUA + "AIMBOT");
		Item.setItemMeta(IMeta);
		inv.setItem(7, Item);
		
		inv.setItem(0, SPEED);
		inv.setItem(1, FLY);
		inv.setItem(2, NOFALL);
		inv.setItem(3, WATERWALK);
		inv.setItem(4, BADMOVE);
		inv.setItem(26, back);
		
		
		
		player.openInventory(inv);
	}
	
	
	@EventHandler
	public void onmenuclick(InventoryClickEvent event) {
		Inventory inv = event.getClickedInventory();
		if(inv == null)return;
		if(inv.getName().equals(ChatColor.translateAlternateColorCodes('&', "&3&lHydra CheckManager"))) {
			Player player = (Player) event.getWhoClicked();
			ItemStack item = event.getCurrentItem();
			event.setCancelled(true);
			
			if(item == null || !item.hasItemMeta()) {
				return;
			}
			
			if(item.getType() == Material.REDSTONE_BLOCK && item.getItemMeta().getDisplayName().equals(ChatColor.RED + "Back")) {
				MainMenu.openmenu(player);
			}
			if(item.getType() == Material.BOOK && item.getItemMeta().getDisplayName().equals(ChatColor.DARK_AQUA + "FLY")) {
				FLYMenu.openmenu(player);
			}
			if(item.getType() == Material.BOOK && item.getItemMeta().getDisplayName().equals(ChatColor.DARK_AQUA + "SPEED")) {
				SpeedMenu.openmenu(player);
			}
			if(item.getType() == Material.BOOK && item.getItemMeta().getDisplayName().equals(ChatColor.DARK_AQUA + "NOFALL")) {
				NOFALLMenu.openmenu(player);
			}
			if(item.getType() == Material.BOOK && item.getItemMeta().getDisplayName().equals(ChatColor.DARK_AQUA + "WATERWALK")) {
				WATERWALKMenu.openmenu(player);
			}
			if(item.getType() == Material.BOOK && item.getItemMeta().getDisplayName().equals(ChatColor.DARK_AQUA + "BADMOVE")) {
				BadMoveMenu.openmenu(player);
			}
			if(item.getType() == Material.BOOK && item.getItemMeta().getDisplayName().equals(ChatColor.DARK_AQUA + "AIMBOT")) {
				AimBotMenu.openmenu(player);
			}
			if(item.getType() == Material.BOOK && item.getItemMeta().getDisplayName().equals(ChatColor.DARK_AQUA + "REACH")) {
				ReachMenu.openmenu(player);
			}
			if(item.getType() == Material.BOOK && item.getItemMeta().getDisplayName().equals(ChatColor.DARK_AQUA + "KILLAURA")) {
				KillAuraMenu.openmenu(player);
			}
		}
	}
	
}

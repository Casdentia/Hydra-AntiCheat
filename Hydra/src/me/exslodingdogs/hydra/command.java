package me.exslodingdogs.hydra;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.exslodingdogs.hydra.Menus.MainMenu;
import me.exslodingdogs.hydra.Utils.PlayerUtils;

public class command implements CommandExecutor, Listener {

	@EventHandler
	public void usewand(PlayerInteractEvent event) {
		Player player = (Player) event.getPlayer();
		if(player.getItemInHand().getType() == Material.BLAZE_ROD) {
			if(player.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.RED + "DEBUG WAND")) {
				player.sendMessage(ChatColor.GRAY + "Debug : " + event.getClickedBlock().getType().toString() + " : Yd " + (player.getLocation().getY()-event.getClickedBlock().getY()));
			}
		}
	}

	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				
				if(args.length == 0) {
					MainMenu.openmenu(player);
					return true;
				}

				
				if(args[0].equalsIgnoreCase("wand")) {
					if(player.isOp()) {
						ItemStack wand = new ItemStack(Material.BLAZE_ROD);
						ItemMeta wandname = wand.getItemMeta();
						wandname.setDisplayName(ChatColor.RED + "DEBUG WAND");
						wand.setItemMeta(wandname);
						
						player.getInventory().addItem(wand);
						player.updateInventory();
					}
					return true;
				}
				if(args[0].equalsIgnoreCase("menu")) {
					
					if(!player.hasPermission("hydra.menu")) {player.sendMessage(ChatColor.RED + "You dont have permissions to run this command!");return true;}
					
					MainMenu.openmenu(player);
				}
				
				
				
				return true;
			}else {
				sender.sendMessage("This is not yet done sorry!");
				return true;
			}

	}

}

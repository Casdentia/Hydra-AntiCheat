package me.exslodingdogs.hydra.Utils;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class PlayerUtils {
	
	public static boolean onground;
	public static HashMap<Player, Long> ping = new HashMap<>();
	
	public static boolean IsNearDround(Location loc) {
		double expand = 0.3;
		for(double x = -expand; x <= expand; x += expand) {
			for(double z = -expand; z <= expand; z += expand) {
				if(loc.clone().add(z, -0.5001, x).getBlock().getType() != Material.AIR) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean IsNearWater(Location loc) {
		double expand = 0.3;
		for(double x = -expand; x <= expand; x += expand) {
			for(double z = -expand; z <= expand; z += expand) {
				if(!loc.clone().add(z, -0.5001, x).getBlock().isLiquid()) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean InWater(Location loc) {
		if(loc.clone().getBlock().isLiquid()) {
			return true;
		}
		return false;
	}
	
	public static boolean OnWaterLily(Location loc) {
		double expand = 0.3;
		for(double x = -expand; x <= expand; x += expand) {
			for(double z = -expand; z <= expand; z += expand) {
				if(loc.clone().add(z, loc.getY(), x).getBlock().getType() == Material.WATER_LILY 
						|| loc.clone().add(z, -0.015625, x).getBlock().getType() == Material.WATER_LILY)  {
					return true;
				}
			}
		}
		return false;
	}
	public static boolean onpad(Location loc) {
		if(loc.clone().add(0, -0.015625, 0).getBlock().getType() == Material.WATER_LILY) {
			return true;
		}
		return false;
	}
	
	
	public static boolean islagging(Player player) {
		if(!PacketManager.ping.containsKey(player))return false;
		
		if(PacketManager.ping.get(player) < 1900|| PacketManager.ping.get(player) > 2600)return true;
		return false;
	}
	
}

package me.exslodingdogs.hydra.Check.Combat.Aim;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.exslodingdogs.hydra.HydraAC;
import me.exslodingdogs.hydra.Check.CheckManager;
import me.exslodingdogs.hydra.Utils.MathUtils;
import me.exslodingdogs.hydra.Utils.PlayerUtils;

public class TypeA extends CheckManager implements Listener{

	public TypeA() {
		super("AIMBOT(TypeA)", false);
	}
	
	HashMap<Player, Long> lastmove = new HashMap<>();
	int i;
	double lastdist;
	HashMap<Player, Integer> flags = new HashMap<>();
	@EventHandler
	public void onmove(PlayerMoveEvent event) {
		
		HydraAC plugin = HydraAC.getPlugin(HydraAC.class);
		if(plugin.getConfig().getBoolean("Checks.AIMBOT.TypeA.Enabled") == false)return;
		
		Player player = event.getPlayer();
		Location to = event.getTo();
		Location from = event.getFrom();
		double distance = Math.sqrt(Math.pow((to.getYaw()-from.getYaw()), 2));
		double dist = Math.sqrt(Math.pow((to.getX()-from.getX()), 2) + Math.pow(to.getZ()-from.getZ(), 2));
		
		double lastdist = this.lastdist;
		this.lastdist = distance;
		
		if(distance > 4.98 && distance < 5.6 && !PlayerUtils.islagging(player)) {
			if(!MathUtils.isRoughtlyEqual(distance, lastdist) && dist > 0 && ++i >= 4) {
				if(flags.containsKey(player)) {
					flags.put(player, flags.get(player)+1);
				}else {
					flags.put(player, 1);
				}
				flag(player, flags.get(player));
			}
			
		}else {
			i=0;
		}
		

	}
	
}

package me.exslodingdogs.hydra.Check.Combat.KillAura;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import me.exslodingdogs.hydra.HydraAC;
import me.exslodingdogs.hydra.Check.CheckManager;
import me.exslodingdogs.hydra.Utils.PlayerUtils;

public class TypeA extends CheckManager implements Listener{

	public TypeA() {
		super("KILLAURA(TypeA)", false);
	}
	HashMap<Player, Integer> flags = new HashMap<>();
	int hitticks, plevel;
	double lastdistance;
	
	@EventHandler
	public void onHit(EntityDamageByEntityEvent event) {
		if(event.getDamager() instanceof Player) {
			HydraAC plugin = HydraAC.getPlugin(HydraAC.class);
			if(plugin.getConfig().getBoolean("Checks.KILLAURA.TypeA.Enabled") == false)return;
			hitticks = 0;
		}
	}
	
	@EventHandler
	public void onmove(PlayerMoveEvent event) {
		HydraAC plugin = HydraAC.getPlugin(HydraAC.class);
		if(plugin.getConfig().getBoolean("Checks.KILLAURA.TypeA.Enabled") == false)return;
		Player player = event.getPlayer();
		Location to = event.getTo();
		Location from = event.getFrom();
		
		double dist = Math.sqrt(Math.pow((to.getX()-from.getX()), 2) + Math.pow(to.getZ()-from.getZ(), 2));
		double lastdistance = this.lastdistance;
		this.lastdistance = dist;
		
		if(player.isSprinting() && ++hitticks <= 2 && !PlayerUtils.islagging(player)) {
			double newdist =Math.abs(dist-lastdistance);
			if(newdist < 0.027) {
				if(++plevel >= 4) {
					if(flags.containsKey(player)) {
						flags.put(player, flags.get(player)+1);
					}else {
						flags.put(player, 1);
					}
					flag(player, flags.get(player));
				}
				
			}else {
				plevel = 0;
			}
			
		}
		
	}
	
}

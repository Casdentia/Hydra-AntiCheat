package me.exslodingdogs.hydra.Check.Movement.speed;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.exslodingdogs.hydra.HydraAC;
import me.exslodingdogs.hydra.Check.CheckManager;
import me.exslodingdogs.hydra.Utils.PlayerUtils;

public class TypeA extends CheckManager implements Listener{
	private static HydraAC plugin = HydraAC.getPlugin(HydraAC.class);
	public TypeA() {
		super("SPEED(TypeA)", true);
	}
	
	HashMap<Player, Integer> flags = new HashMap<>();
	
	boolean onground, lastonground;
	double lastongroundspeed;
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onmove(PlayerMoveEvent event) {
		
		if(plugin.getConfig().getBoolean("Checks.SPEED.TypeA.Enabled") == false)return;
		
		Player player = event.getPlayer();
		Location to = event.getTo();
		Location from = event.getFrom();
		double distance = Math.sqrt(Math.pow((to.getX()-from.getX()), 2) + Math.pow(to.getZ()-from.getZ(), 2));
		onground = player.isOnGround();
		float friction = 0.91F;
		this.lastonground = onground;
		lastongroundspeed = distance;
		
		double shiffedspeed = lastongroundspeed * friction;
		double newdistance = lastongroundspeed-shiffedspeed;
		
		double updateddistance = newdistance * 115;
		
		if(onground && lastonground && !PlayerUtils.islagging(player)) {
			if(updateddistance > 6.34 /*PlayerUtils.islagging(player)*/) {
				if(flags.containsKey(player)) {
					flags.put(player, flags.get(player)+1);
				}else {
					flags.put(player, 1);
				}
				player.teleport(from);
				flag(player, flags.get(player));
			}
		}
		
	}
	
}

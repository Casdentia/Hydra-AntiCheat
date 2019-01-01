package me.exslodingdogs.hydra.Check.Combat.Reach;

import java.util.HashMap;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.exslodingdogs.hydra.HydraAC;
import me.exslodingdogs.hydra.Check.CheckManager;
import me.exslodingdogs.hydra.Utils.PlayerUtils;

public class TypeA extends CheckManager implements Listener{

	public TypeA() {
		super("REACH(TypeA)", false);
	}
	HashMap<Player, Integer> flags = new HashMap<>();

	@EventHandler
	public void onhit(EntityDamageByEntityEvent event) {
		HydraAC plugin = HydraAC.getPlugin(HydraAC.class);
		if(plugin.getConfig().getBoolean("Checks.REACH.TypeA.Enabled") == false)return;
		if(event.getDamager() instanceof Player) {
			Player player = (Player) event.getDamager();
			Entity target = event.getEntity();
			
			double reach = target.getLocation().toVector().distanceSquared(player.getLocation().toVector());
			if(!flags.containsKey(player)) {flags.put(player, 0);}
			if(reach > 14 && !PlayerUtils.islagging(player)) {
				Integer alert = flags.get(player) == 0 ? flags.put(player, 1) : flags.put(player, flags.get(player)+1);
				flag(player, alert);
			}
			
			//player.sendMessage(reach + "");
		}
	}
	
}

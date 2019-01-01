package me.exslodingdogs.hydra.Check.Movement.Step;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.exslodingdogs.hydra.Check.CheckManager;

public class TypeA extends CheckManager implements Listener{

	public TypeA() {
		super("STEP(TypeA)", false);
	}
	int tick;
	HashMap<Player, Integer> flags = new HashMap<>();
	@EventHandler
	public void onmove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		Location to = event.getTo();
		Location from = event.getFrom();
		
		double YDist = to.getY()-from.getY();
		double Speed = from.toVector().distance(to.toVector());
		
		if(YDist == Speed && YDist != 0.0 && Speed != 0.0) {
			if(++tick >= 2) {
				if(flags.containsKey(player)) {
					flags.put(player, flags.get(player)+1);
				}else {
					flags.put(player, 1);
				}
				player.teleport(from);
				flag(player, flags.get(player));
			}
		}else {
			tick=0;
		}
		
	}
	
}

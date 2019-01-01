package me.exslodingdogs.hydra.Check.Movement.fly;

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
		super("FLY(TypeA)", false);
	}
	
	HashMap<Player, Integer> flags = new HashMap<>();
	
	boolean lastonground,lastlastonground;
	double LastDistY;
	int tick;
	@EventHandler
	public void onmove(PlayerMoveEvent event) {

		HydraAC plugin = HydraAC.getPlugin(HydraAC.class);
		if(plugin.getConfig().getBoolean("Checks.FLY.TypeA.Enabled") == false)return;
		
		Player player = event.getPlayer();
		Location to = event.getTo();
		Location from = event.getFrom();
		
		if(player.getAllowFlight() == true)return;
		
		double distY = to.getY()- from.getY();
		double LastDistY = this.LastDistY;
		this.LastDistY = distY;
		double prodictedDist = (LastDistY - 0.08D) * 0.980000019073348630D;
		boolean IsNearGround = PlayerUtils.IsNearDround(to);
		
		boolean lastonground = this.lastonground;
		this.lastonground = IsNearGround;
		boolean lastlastonground = this.lastlastonground;
		this.lastlastonground = lastonground;
		

		
		if(!IsNearGround && !lastonground && !lastlastonground && Math.abs(prodictedDist) >= 0.035D && !PlayerUtils.islagging(player)) {
			if(!MathUtils.isRoughtlyEqual(distY, prodictedDist)) {
				
				if(++tick >= 3) {
					if(flags.containsKey(player)) {
						flags.put(player, flags.get(player)+1);
					}else {
						flags.put(player, 1);
					}
					
					//player.sendMessage(LastDistY + " , " + prodictedDist);
					flag(player, flags.get(player));
				}
			}
		}else {
			tick = 0;
		}
	}
	
}

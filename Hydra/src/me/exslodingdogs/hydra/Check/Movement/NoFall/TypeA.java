package me.exslodingdogs.hydra.Check.Movement.NoFall;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.exslodingdogs.hydra.HydraAC;
import me.exslodingdogs.hydra.Check.CheckManager;
import me.exslodingdogs.hydra.Utils.PlayerUtils;

public class TypeA extends CheckManager implements Listener{

	public TypeA() {
		super("NOFALL(TypeA)", false);
	}
	
	boolean lastonground, lastlastonground;
	
	HashMap<Player, Integer> flags = new HashMap<>();
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		HydraAC plugin = HydraAC.getPlugin(HydraAC.class);
		if(plugin.getConfig().getBoolean("Checks.NOFALL.TypeA.Enabled") == false)return;
		boolean onground = PlayerUtils.IsNearDround(event.getTo());
		
		boolean lastonground = this.lastonground;
		this.lastonground = onground;
		
		boolean lastlastonground = this.lastlastonground;
		this.lastlastonground = lastonground;
		
		if(!flags.containsKey(player)) {flags.put(player, 0);}
		
		if(!onground && !lastonground && !lastlastonground && player.isOnGround() 
				&& event.getTo().getY() < event.getFrom().getY() && !PlayerUtils.islagging(player)) {
			Integer alert = flags.get(player) == 0 ? flags.put(player, 1) : flags.put(player, flags.get(player)+1);
			flag(player, alert);
		}
		
	}
	
}

package me.exslodingdogs.hydra.Check.Movement.waterwalk;

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
		super("WATERWALK(TypeA)", false);
	}
	HashMap<Player, Integer> timesflagged = new HashMap<>();

	boolean lastonWater;
	int tick, tflags;
	@EventHandler
	public void onmove(PlayerMoveEvent event) {
		HydraAC plugin = HydraAC.getPlugin(HydraAC.class);
		if(plugin.getConfig().getBoolean("Checks.WATERWALK.TypeA.Enabled") == false)return;
		Player player = event.getPlayer();
		
		boolean IsOnWater = PlayerUtils.IsNearWater(event.getTo());
		
		boolean lastonWater = this.lastonWater;
		this.lastonWater = IsOnWater;
		
		if(!IsOnWater && !lastonWater  && !PlayerUtils.OnWaterLily(event.getTo()) 
				&& !PlayerUtils.islagging(player) && !PlayerUtils.InWater(event.getTo())) {
			if(++tflags >= 2) {
				flag(player,tick++);
			}
		}else {
			tflags = 0;
		}
		
	}
	
}

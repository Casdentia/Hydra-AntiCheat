package me.exslodingdogs.hydra.Check.Movement.Badmove;

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

	public TypeA() {
		super("BADMOVE(TypeA)", false);
	}
	int tick;
	double maxdiff = 1.0;
	boolean lastonground, lastonlily;
	HashMap<Player, Integer> flags = new HashMap<>();
	@EventHandler
	public void onmove(PlayerMoveEvent event) {
		HydraAC plugin = HydraAC.getPlugin(HydraAC.class);
		if(plugin.getConfig().getBoolean("Checks.BADMOVE.TypeA.Enabled") == false)return;
		
		Player player = event.getPlayer();
		Location to = event.getTo();
		
		boolean onground = PlayerUtils.IsNearDround(to.clone());
		boolean lastonground = this.lastonground;
		this.lastonground = onground;
		
		boolean lastonlily = this.lastonlily;
		this.lastonlily = PlayerUtils.OnWaterLily(to);
		
		boolean flagable = (player.getLocation().getY()-player.getLocation().subtract(0,1,0).getBlock().getY()) > maxdiff;
		if(!flags.containsKey(player)) {flags.put(player, 0);}
		if(onground && lastonground && flagable && event.getFrom().getY() == event.getTo().getY()
				&& ((player.getLocation().getY()-player.getLocation().subtract(0,1,0).getBlock().getY()) < 1.249)
				&& ((player.getLocation().getY()-player.getLocation().subtract(0,1,0).getBlock().getY()) != 1.125)
				&& !PlayerUtils.islagging(player) && !PlayerUtils.OnWaterLily(to) && !lastonlily
				&& !player.isFlying()) {
			if(++tick >= 2) {
				Integer alert = flags.get(player) == 0 ? flags.put(player, 1) : flags.put(player, flags.get(player)+1);
				//player.sendMessage((player.getLocation().getY()-player.getLocation().subtract(0,1,0).getBlock().getY()) + "");
				flag(player, alert);
			}
		}
		
	}
	
}

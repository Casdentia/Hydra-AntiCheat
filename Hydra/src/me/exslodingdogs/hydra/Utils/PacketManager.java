package me.exslodingdogs.hydra.Utils;

import java.util.HashMap;

import org.bukkit.entity.Player;

import cc.funkemunky.api.event.custom.PacketSendEvent;
import cc.funkemunky.api.event.system.EventMethod;
import cc.funkemunky.api.event.system.Listener;
import cc.funkemunky.api.tinyprotocol.api.Packet;

public class PacketManager implements Listener {

	public static HashMap<Player, Long> ping = new HashMap<>();
	
	@EventMethod
	public void onEvent(PacketSendEvent event) {
		if(event.getPlayer() == null)return;
		switch(event.getType()) {
		case Packet.Server.KEEP_ALIVE:{
			if(PlayerUtils.ping.containsKey(event.getPlayer())) {
				//ÛBukkit.broadcastMessage("" + (System.currentTimeMillis()-PlayerUtils.ping.get(event.getPlayer())));
				ping.put(event.getPlayer(), (System.currentTimeMillis()-PlayerUtils.ping.get(event.getPlayer())));
				
				PlayerUtils.ping.put(event.getPlayer(), System.currentTimeMillis());
			}else {
				PlayerUtils.ping.put(event.getPlayer(), System.currentTimeMillis());
			}
			//Bukkit.broadcastMessage("ran1");
			break;
		}
		}
	}
	

}

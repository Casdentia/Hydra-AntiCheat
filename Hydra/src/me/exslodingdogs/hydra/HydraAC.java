package me.exslodingdogs.hydra;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import cc.funkemunky.api.event.system.EventManager;
import me.exslodingdogs.hydra.Utils.PacketManager;

public class HydraAC extends JavaPlugin{

	//private static HydraAC plugin ;
	@Override
	public void onEnable() {
		
		if(Bukkit.getPluginManager().getPlugin("Atlas") == null) {
            System.out.println("[HYDRA] Please download Atlas to run this plugin! " +
                    "(https://github.com/funkemunky/Daedalus/releases)");
            Bukkit.getPluginManager().disablePlugin(this);
        }
		
		//plugin = HydraAC.getPlugin(HydraAC.class);
		EventManager.register(new PacketManager());
		
		Bukkit.getPluginManager().registerEvents(new me.exslodingdogs.hydra.Check.Combat.KillAura.TypeA(), this);
		Bukkit.getPluginManager().registerEvents(new me.exslodingdogs.hydra.Check.Combat.Reach.TypeA(), this);
		Bukkit.getPluginManager().registerEvents(new me.exslodingdogs.hydra.Check.Combat.Aim.TypeA(), this);
		
		Bukkit.getPluginManager().registerEvents(new me.exslodingdogs.hydra.Check.Movement.Step.TypeA(), this);
		Bukkit.getPluginManager().registerEvents(new me.exslodingdogs.hydra.Check.Movement.NoFall.TypeA(), this);
		Bukkit.getPluginManager().registerEvents(new me.exslodingdogs.hydra.Check.Movement.speed.TypeA(), this);
		Bukkit.getPluginManager().registerEvents(new me.exslodingdogs.hydra.Check.Movement.fly.TypeA(), this);
		Bukkit.getPluginManager().registerEvents(new me.exslodingdogs.hydra.Check.Movement.waterwalk.TypeA(), this);
		Bukkit.getPluginManager().registerEvents(new me.exslodingdogs.hydra.Check.Movement.waterwalk.TypeB(), this); 
		Bukkit.getPluginManager().registerEvents(new me.exslodingdogs.hydra.Check.Movement.Badmove.TypeA(), this);
		
		getConfig().options().copyDefaults(true);
		saveConfig();
	
		Bukkit.getPluginManager().registerEvents(new command(), this);

		getCommand("Hydra").setExecutor(new me.exslodingdogs.hydra.command());
		Bukkit.getPluginManager().registerEvents(new me.exslodingdogs.hydra.Menus.CheckManagerMenu(), this);
		Bukkit.getPluginManager().registerEvents(new me.exslodingdogs.hydra.Menus.FLYMenu(), this);
		Bukkit.getPluginManager().registerEvents(new me.exslodingdogs.hydra.Menus.AimBotMenu(), this);
		Bukkit.getPluginManager().registerEvents(new me.exslodingdogs.hydra.Menus.ReachMenu(), this);
		Bukkit.getPluginManager().registerEvents(new me.exslodingdogs.hydra.Menus.KillAuraMenu(), this);
		Bukkit.getPluginManager().registerEvents(new me.exslodingdogs.hydra.Menus.BadMoveMenu(), this);
		Bukkit.getPluginManager().registerEvents(new me.exslodingdogs.hydra.Menus.FLYMenu(), this);
		Bukkit.getPluginManager().registerEvents(new me.exslodingdogs.hydra.Menus.SpeedMenu(), this);
		Bukkit.getPluginManager().registerEvents(new me.exslodingdogs.hydra.Menus.NOFALLMenu(), this);
		Bukkit.getPluginManager().registerEvents(new me.exslodingdogs.hydra.Menus.WATERWALKMenu(), this);
		Bukkit.getPluginManager().registerEvents(new me.exslodingdogs.hydra.Menus.MainMenu(), this);
		
		
	}

	public void onDisable() {
		EventManager.unregister(new PacketManager());
	}
	
}

package com.IT.BaseX.B1GSt4R.BuildingMode.Events;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import com.IT.BaseX.B1GSt4R.BuildingMode.Main.system;

public class quitListener implements Listener {
	private com.IT.BaseX.B1GSt4R.BuildingMode.Main.system plugin;
	public quitListener(system system) {
		this.plugin = system;
		this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		
		if(plugin.playersAtBuilding.contains(p)) {
			plugin.playersAtBuilding.remove(p);
			ItemStack[] contents = plugin.survivalInv.get(p.getName());
			plugin.survivalInv.remove(p.getName());
			p.getInventory().clear();
			p.setGameMode(GameMode.SURVIVAL);
			p.setExp(plugin.survivalXP.get(p.getName()));
			plugin.survivalXP.remove(p.getName());
			p.setLevel(plugin.survivalLevel.get(p.getName()));
			plugin.survivalLevel.remove(p.getName());
			p.getInventory().setContents(contents);
			
			String log = plugin.logprefix+p.getName()+" >> /build" + " >> &Stopp";
			plugin.logToFile(log);
		}
	}
}

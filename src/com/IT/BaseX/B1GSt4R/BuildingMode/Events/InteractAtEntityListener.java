package com.IT.BaseX.B1GSt4R.BuildingMode.Events;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import com.IT.BaseX.B1GSt4R.BuildingMode.Main.system;

public class InteractAtEntityListener implements Listener {
	private com.IT.BaseX.B1GSt4R.BuildingMode.Main.system plugin;
	public InteractAtEntityListener(system system) {
		this.plugin = system;
		this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onEntityAt(PlayerInteractAtEntityEvent e) {
		Player p = e.getPlayer();
		if(plugin.playersAtBuilding.contains(p)) {
			boolean armor_stand = e.getRightClicked().getType() == EntityType.ARMOR_STAND;
			
			if(armor_stand) {
				e.setCancelled(true);
				p.sendMessage(plugin.prefix+"Solange du im Builder Modus bist darfst du dieses Entity nicht nutzen!");
				String log = plugin.logprefix+p.getName()+" >> InteractAtEntity >> "+e.getRightClicked().getType();
				plugin.entityLogToFile(log);
			}
		}
	}
}

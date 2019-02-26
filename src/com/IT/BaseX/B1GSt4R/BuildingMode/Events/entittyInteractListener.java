package com.IT.BaseX.B1GSt4R.BuildingMode.Events;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import com.IT.BaseX.B1GSt4R.BuildingMode.Main.system;

public class entittyInteractListener implements Listener {
	private com.IT.BaseX.B1GSt4R.BuildingMode.Main.system plugin;
	public entittyInteractListener(system system) {
		this.plugin = system;
		this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onInteractEntity(PlayerInteractEntityEvent e) {
		Player p = e.getPlayer();
		if(plugin.playersAtBuilding.contains(p)) {
			boolean armor_stand = e.getRightClicked().getType() == EntityType.ARMOR_STAND;
			boolean item_frame = e.getRightClicked().getType() == EntityType.ITEM_FRAME;
			boolean powered_mc = e.getRightClicked().getType() == EntityType.MINECART_FURNACE;
			
			if(armor_stand || item_frame || powered_mc) {
				e.setCancelled(true);
				p.sendMessage(plugin.prefix+"Solange du im Builder Modus bist darfst du dieses Entity nicht nutzen!");
				String log = plugin.logprefix+p.getName()+" >> InteractEntity >> "+e.getRightClicked().getType();
				plugin.entityLogToFile(log);
			}
		}
	}
}

package com.IT.BaseX.B1GSt4R.BuildingMode.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import com.IT.BaseX.B1GSt4R.BuildingMode.Main.system;

public class itemDropListener implements Listener {
	private com.IT.BaseX.B1GSt4R.BuildingMode.Main.system plugin;
	public itemDropListener(system system) {
		this.plugin = system;
		this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		Player p = e.getPlayer();
		if(plugin.playersAtBuilding.contains(p)) {
			e.setCancelled(true);
			String log = plugin.logprefix+p.getName()+" >> Dropping >> "+e.getItemDrop().getItemStack().getType()+" ("+e.getItemDrop().getItemStack().getTypeId()+") [Anzahl: "+e.getItemDrop().getItemStack().getAmount()+"]";
			plugin.allOtherLogToFIle(log);
		}
	}
}

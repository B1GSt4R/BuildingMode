package com.IT.BaseX.B1GSt4R.BuildingMode.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

import com.IT.BaseX.B1GSt4R.BuildingMode.Main.system;

@SuppressWarnings("deprecation")
public class itemPickUpListener implements Listener {
	private com.IT.BaseX.B1GSt4R.BuildingMode.Main.system plugin;
	public itemPickUpListener(system system) {
		this.plugin = system;
		this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onItemPickUp(PlayerPickupItemEvent e) {
		Player p = e.getPlayer();
		if(plugin.playersAtBuilding.contains(p)) {
			e.setCancelled(true);
			/*String log = plugin.logprefix+p.getName()+" >> Pickup >> "+e.getItem().getItemStack().getType()+" ("+e.getItem().getItemStack().getTypeId()+") [Anzahl: "+e.getItem().getItemStack().getAmount()+"]";
			plugin.allOtherLogToFIle(log);*/
		}
	}
}

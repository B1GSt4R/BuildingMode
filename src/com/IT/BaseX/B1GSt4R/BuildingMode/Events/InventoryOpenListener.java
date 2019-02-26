package com.IT.BaseX.B1GSt4R.BuildingMode.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;

import com.IT.BaseX.B1GSt4R.BuildingMode.Main.system;

public class InventoryOpenListener implements Listener {
	private com.IT.BaseX.B1GSt4R.BuildingMode.Main.system plugin;
	public InventoryOpenListener(system system) {
		this.plugin = system;
		this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onInvOpen(InventoryOpenEvent e) {
		Player p = (Player) e.getPlayer();
		if(plugin.playersAtBuilding.contains(p)) {
			boolean villager = e.getInventory().getType() == InventoryType.MERCHANT;
			boolean hopper_mc = e.getInventory().getType() == InventoryType.HOPPER;
			boolean storage_mc = e.getInventory().getType() == InventoryType.CHEST;
			boolean powered_mc = e.getInventory().getType() == InventoryType.FURNACE;
			boolean empty1 = e.getInventory().getType() == InventoryType.ANVIL;
			boolean empty2 = e.getInventory().getType() == InventoryType.BEACON;
			boolean empty3 = e.getInventory().getType() == InventoryType.BREWING;
			boolean empty4 = e.getInventory().getType() == InventoryType.CRAFTING;
			boolean empty5 = e.getInventory().getType() == InventoryType.DISPENSER;
			boolean empty6 = e.getInventory().getType() == InventoryType.DROPPER;
			boolean empty7 = e.getInventory().getType() == InventoryType.ENCHANTING;
			boolean empty8 = e.getInventory().getType() == InventoryType.ENDER_CHEST;
			boolean empty9 = e.getInventory().getType() == InventoryType.SHULKER_BOX;
			boolean empty0 = e.getInventory().getType() == InventoryType.WORKBENCH;
			
			if(villager || hopper_mc || storage_mc || powered_mc || empty0 || empty1 || empty2 || empty3 || empty4 || empty5 || empty6 || empty7 || empty8 || empty9) {
				e.setCancelled(true);
				p.sendMessage(plugin.prefix+"Solange du im Builder Modus bist darfst du dieses Inventar nicht nutzen!");
				if(villager) {
					String log = plugin.logprefix+p.getName()+" >> openInvFromEntity >> "+e.getInventory().getType();
					plugin.entityLogToFile(log);
				}else {
					String log = plugin.logprefix+p.getName()+" >> openInv >> "+e.getInventory().getType();
					plugin.allOtherLogToFIle(log);
				}
			}
		}
	}
	
}

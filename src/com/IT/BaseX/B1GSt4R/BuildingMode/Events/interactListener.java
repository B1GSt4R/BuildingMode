package com.IT.BaseX.B1GSt4R.BuildingMode.Events;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.IT.BaseX.B1GSt4R.BuildingMode.Main.system;

public class interactListener implements Listener {
	private com.IT.BaseX.B1GSt4R.BuildingMode.Main.system plugin;
	public interactListener(system system) {
		this.plugin = system;
		this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	private HashMap<String, Material> tmp = new HashMap<>();
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		
		Material[] banned = new Material[2];
		//banned[0] = Material.CHEST;
		//banned[1] = Material.ENDER_CHEST;
		//banned[2] = Material.TRAPPED_CHEST;
		//banned[3] = Material.HOPPER;
		//banned[4] = Material.HOPPER_MINECART; //Entity
		//banned[5] = Material.STORAGE_MINECART; //Entity
		//banned[6] = Material.POWERED_MINECART; //Entity
		//banned[7] = Material.FURNACE;
		//banned[8] = Material.ITEM_FRAME; //Entity
		banned[0] = Material.JUKEBOX; //Doppelte Msg
		//banned[10] = Material.ANVIL;
		//banned[11] = Material.ENCHANTMENT_TABLE;
		//banned[12] = Material.DROPPER;
		//banned[13] = Material.DISPENSER;
		//banned[14] = Material.BREWING_STAND;
		banned[1] = Material.CAULDRON;
		//banned[16] = Material.ARMOR_STAND; Bugt
		//banned[18] = Material.BLACK_SHULKER_BOX;
		//banned[19] = Material.BLUE_SHULKER_BOX;
		//banned[20] = Material.BROWN_SHULKER_BOX;
		//banned[21] = Material.CYAN_SHULKER_BOX;
		//banned[22] = Material.GRAY_SHULKER_BOX;
		//banned[23] = Material.GREEN_SHULKER_BOX;
		//banned[24] = Material.LIGHT_BLUE_SHULKER_BOX;
		//banned[25] = Material.LIME_SHULKER_BOX;
		//banned[26] = Material.MAGENTA_SHULKER_BOX;
		//banned[27] = Material.ORANGE_SHULKER_BOX;
		//banned[28] = Material.PINK_SHULKER_BOX;
		//banned[29] = Material.PURPLE_SHULKER_BOX;
		//banned[30] = Material.RED_SHULKER_BOX;
		//banned[31] = Material.SILVER_SHULKER_BOX;
		//banned[32] = Material.WHITE_SHULKER_BOX;
		//banned[17] = Material.YELLOW_SHULKER_BOX;
		//banned[33] = Material.FLOWER_POT; //Bugt
		
		//banned[33] = Material.FLOWER_POT; //Bugt Doppelte Msg
		//banned[0] = Material.JUKEBOX; //Bugt Doppelte Msg
		
		
		
		if(plugin.playersAtBuilding.contains(p)) {
			if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if(e.getClickedBlock().getType() != null) {
					for(int i = 0; i < banned.length; i++) {
						if(e.getClickedBlock().getType() == banned[i]) {
							if(tmp.get(p.getName()) != banned[i]) {
								e.setCancelled(true);
								p.sendMessage(plugin.prefix+"Solange du im Builder Modus bist darfst du diesen Block nicht nutzen!");
								String log = plugin.logprefix+p.getName()+" >> Interact >> "+e.getClickedBlock().getTypeId();
								plugin.allOtherLogToFIle(log);
								tmp.put(p.getName(), banned[i]);
							}
						}
					}
					tmp.remove(p.getName());
				}
			}
		}
	}
}

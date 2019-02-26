package com.IT.BaseX.B1GSt4R.BuildingMode.Events;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.IT.BaseX.B1GSt4R.BuildingMode.Main.system;

public class placeBlockListener implements Listener {
	private com.IT.BaseX.B1GSt4R.BuildingMode.Main.system plugin;
	public placeBlockListener(system system) {
		this.plugin = system;
		this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@SuppressWarnings({ "static-access", "deprecation" })
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		Block b = e.getBlock();
		
		if(plugin.playersAtBuilding.contains(p)) {
			boolean shulker = b.getType() == Material.BLACK_SHULKER_BOX || b.getType() == Material.BLUE_SHULKER_BOX || b.getType() == Material.BROWN_SHULKER_BOX || b.getType() == Material.CYAN_SHULKER_BOX || b.getType() == Material.GRAY_SHULKER_BOX || b.getType() == Material.GREEN_SHULKER_BOX || b.getType() == Material.LIGHT_BLUE_SHULKER_BOX || b.getType() == Material.LIME_SHULKER_BOX ||
					b.getType() == Material.MAGENTA_SHULKER_BOX || b.getType() == Material.ORANGE_SHULKER_BOX || b.getType() == Material.PINK_SHULKER_BOX || b.getType() == Material.PURPLE_SHULKER_BOX || b.getType() == Material.RED_SHULKER_BOX || b.getType() == Material.SILVER_SHULKER_BOX || b.getType() == Material.WHITE_SHULKER_BOX || b.getType() == Material.YELLOW_SHULKER_BOX;
			if(b.getType() == Material.TNT || b.getType() == Material.IRON_BLOCK || b.getType() == Material.DIAMOND_BLOCK || b.getType() == Material.GOLD_BLOCK || b.getType() == Material.EMERALD_BLOCK || b.getType() == Material.COAL_BLOCK || b.getType() == Material.LAPIS_BLOCK || b.getType() == Material.REDSTONE_BLOCK ||
					b.getType() == Material.COAL_ORE || b.getType() == Material.DIAMOND_ORE || b.getType() == Material.EMERALD_ORE || b.getType() == Material.GOLD_ORE || b.getType() == Material.IRON_ORE || b.getType() == Material.LAPIS_ORE || b.getType() == Material.REDSTONE_ORE || b.getType() == Material.GLOWING_REDSTONE_ORE) {
				int x = b.getLocation().getBlockX();
				int y = b.getLocation().getBlockY();
				int z = b.getLocation().getBlockZ();
				
				String world = b.getLocation().getWorld().getName().toString();
				String username = p.getName();
				int id = b.getTypeId();
				Random rnd = new Random();
				
				String loc = plugin.locConverter.getLoc(x, y, z, world, id, username);
				plugin.locConverter.setLoc(loc.toString());
				
				String log = plugin.logprefix+p.getName()+" >> Placed >> "+loc;
				plugin.blockLogToFIle(log);
				
				plugin.CONSOLE.sendMessage("§a"+p.getName()+" >> Placed >> "+loc);
			}
			
			if(shulker) {
				e.setCancelled(true);
				p.sendMessage(plugin.prefix+"Die Shulker Boxen sind im Build Mode Verboten!");
				int x = b.getLocation().getBlockX();
				int y = b.getLocation().getBlockY();
				int z = b.getLocation().getBlockZ();
				
				String world = b.getLocation().getWorld().getName().toString();
				String username = p.getName();
				int id = b.getTypeId();
				Random rnd = new Random();
				
				String loc = plugin.locConverter.getLoc(x, y, z, world, id, username);
				
				String log = plugin.logprefix+p.getName()+" >> Placed >> "+loc;
				plugin.blockLogToFIle(log);
				
				plugin.CONSOLE.sendMessage("§a"+p.getName()+" >> Placed >> "+loc);
			}
		}
	}

}

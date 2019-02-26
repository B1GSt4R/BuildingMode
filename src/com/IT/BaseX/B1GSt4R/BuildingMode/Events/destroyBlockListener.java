package com.IT.BaseX.B1GSt4R.BuildingMode.Events;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.IT.BaseX.B1GSt4R.BuildingMode.Main.system;

public class destroyBlockListener implements Listener {
	private com.IT.BaseX.B1GSt4R.BuildingMode.Main.system plugin;
	public destroyBlockListener(system system) {
		this.plugin = system;
		this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@SuppressWarnings({ "static-access", "deprecation" })
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		Block b = e.getBlock();
		if(plugin.playersAtBuilding.contains(p)) {
			
			//Delete Ecke
			
			String loc = plugin.locConverter.getLoc(b.getLocation().getBlockX(), b.getLocation().getBlockY(), b.getLocation().getBlockZ(), b.getLocation().getWorld().getName().toString(), b.getTypeId(), p.getName());
			
			String log = plugin.logprefix+p.getName()+" >> Destroyed >> "+loc;
			plugin.blockLogToFIle(log);
			
			plugin.CONSOLE.sendMessage("§6"+p.getName()+" >> Destroyed >> "+loc);
			
			if(plugin.blocks.contains(loc)) {
				e.setCancelled(false);
				plugin.locConverter.delLoc(loc);
			}else {
				int anzahl = 0;
				String user = "";
				
				int xb = b.getLocation().getBlockX();
				int yb = b.getLocation().getBlockY();
				int zb = b.getLocation().getBlockZ();
				String worldb = b.getLocation().getWorld().getName().toString();
				int idb = b.getTypeId();
				
				String blockPlace = xb+";"+yb+";"+zb+";"+worldb+";"+idb;
				
				if(plugin.blocks.size() > 0) {
					for(int i = 0; i<plugin.blocks.size(); i++) {
						String[] array = plugin.locConverter.getCfgLoc(plugin.blocks.get(i).toString());
						String x = array[0];
						String y = array[1];
						String z = array[2];
						String world = array[3];
						String id = array[4];
						String username = array[5];
						
						String placed = x+";"+y+";"+z+";"+world+";"+id;
						
						if(!blockPlace.equals(placed)) {
							anzahl++;
						}else {
							user = username;
						}
					}
				}
				
				if(anzahl == plugin.blocks.size()) {
					e.setCancelled(false);
				}else {
					e.setCancelled(false);
					plugin.locConverter.delLoc(blockPlace+";"+user);
				}
			}
		}else {
			
			//No Perm ecke
			
			String loc = plugin.locConverter.getLoc(b.getLocation().getBlockX(), b.getLocation().getBlockY(), b.getLocation().getBlockZ(), b.getLocation().getWorld().getName().toString(), b.getTypeId(), p.getName());
			if(plugin.blocks.contains(loc)) {
				e.setCancelled(true);
				p.sendMessage(plugin.prefix+"Diesen Block wolltest du dir cheaten? Damit bist du nun §4Verwarnt§7!");
				String log = plugin.logprefix+p.getName()+" >> Destroyed Try + Warn>> "+loc;
				plugin.blockLogToFIle(log);
				
				plugin.CONSOLE.sendMessage("§4"+p.getName()+" >> Destroyed Try + warn >> "+loc);
				
				//WARN PLAYER HERE
			}else {
				
				int anzahl = 0;
				String user = "";
				
				int xb = b.getLocation().getBlockX();
				int yb = b.getLocation().getBlockY();
				int zb = b.getLocation().getBlockZ();
				String worldb = b.getLocation().getWorld().getName().toString();
				int idb = b.getTypeId();
				
				if(plugin.blocks.size() > 0) {
					for(int i = 0; i<plugin.blocks.size(); i++) {
						String[] array = plugin.locConverter.getCfgLoc(plugin.blocks.get(i).toString());
						String x = array[0];
						String y = array[1];
						String z = array[2];
						String world = array[3];
						String id = array[4];
						String username = array[5];
						
						String placed = x+";"+y+";"+z+";"+world+";"+id;
						String blockPlace = xb+";"+yb+";"+zb+";"+worldb+";"+idb;
						
						if(!blockPlace.equals(placed)) {
							anzahl++;
						}else {
							user = username;
						}
					}
				}
				
				if(anzahl == plugin.blocks.size()) {
					e.setCancelled(false);
				}else {
					e.setCancelled(true);
					p.sendMessage(plugin.prefix+"Dieser block wurde von §5"+user+ "§7 im building mode platziert und kann nicht abgebaut werden!");
					String log = plugin.logprefix+p.getName()+" >> Destroyed Try >> "+loc;
					plugin.blockLogToFIle(log);
					
					plugin.CONSOLE.sendMessage("§4"+p.getName()+" >> Destroyed Try >> "+loc);
				}
			}
		}
	}
}

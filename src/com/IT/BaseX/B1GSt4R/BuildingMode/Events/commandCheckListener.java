package com.IT.BaseX.B1GSt4R.BuildingMode.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.IT.BaseX.B1GSt4R.BuildingMode.Main.system;

public class commandCheckListener implements Listener {
	private com.IT.BaseX.B1GSt4R.BuildingMode.Main.system plugin;
	public commandCheckListener(system system) {
		this.plugin = system;
		this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onCommand(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		String msg = e.getMessage();
		
		//command block
				/*Defekt*/boolean plugins = e.getMessage().toLowerCase().startsWith("/plugins");
				/*Defekt*/boolean pl = e.getMessage().toLowerCase().equalsIgnoreCase("/pl") && !e.getMessage().toLowerCase().startsWith("/plotme") && !e.getMessage().toLowerCase().startsWith("/plot") && !e.getMessage().toLowerCase().startsWith("/plotgenversion") && !e.getMessage().toLowerCase().startsWith("/pluginmanager") && !e.getMessage().toLowerCase().startsWith("/plugman") && !e.getMessage().toLowerCase().startsWith("/plane") && !e.getMessage().toLowerCase().startsWith("/planeshop") && !e.getMessage().toLowerCase().startsWith("/player") && !e.getMessage().toLowerCase().startsWith("/playtime");
				boolean gc = e.getMessage().equalsIgnoreCase("/gc");
				/*Defekt*/boolean icanhasbukkit = e.getMessage().toLowerCase().startsWith("/icanhasbukkit");
				/*Defekt*/boolean unknown = e.getMessage().toLowerCase().startsWith("/?");
				/*Defekt*/boolean version = e.getMessage().toLowerCase().startsWith("/version");
				/*Defekt*/boolean ver = e.getMessage().toLowerCase().startsWith("/ver");
				/*Defekt*/boolean bukkitplugin = e.getMessage().toLowerCase().startsWith("/bukkit:plugins");
				/*Defekt*/boolean bukkitpl = e.getMessage().toLowerCase().startsWith("/bukkit:pl");
				/*Defekt*/boolean bukkitunknown = e.getMessage().toLowerCase().startsWith("/bukkit:?");
				/*Defekt*/boolean about = e.getMessage().toLowerCase().startsWith("/about");
				/*Defekt*/boolean a = e.getMessage().equalsIgnoreCase("/a");
				/*Defekt*/boolean bukkitabout = e.getMessage().toLowerCase().startsWith("/bukkit:about");
				/*Defekt*/boolean bukkita = e.getMessage().toLowerCase().startsWith("/bukkit:a");
				/*Defekt*/boolean bukkitversion = e.getMessage().toLowerCase().startsWith("/bukkit:version");
				/*Defekt*/boolean bukkitver = e.getMessage().toLowerCase().startsWith("/bukkit:ver");
				/*Defekt*/boolean bukkithelp = e.getMessage().toLowerCase().startsWith("/bukkit:help");
				/*Defekt*/boolean help = e.getMessage().equalsIgnoreCase("/help");
				/*Defekt*/boolean gm = e.getMessage().startsWith("/gm") || e.getMessage().startsWith("/gamemode");
				boolean dorf = e.getMessage().contains("/dorf ?");
				//14, 15, 16, 21, 73, 56, 129, gold_ore, iron_ore, coal_ore, lapis_ore, redstone_ore, diamond_ore, emerald_ore, 22, 41, 42, 57, 152, 133, 173
				boolean cheat = e.getMessage().contains(" 14 ") || e.getMessage().contains(" 15 ") || e.getMessage().contains(" 16 ") || e.getMessage().contains(" 21 ") || e.getMessage().contains(" 73 ") || e.getMessage().contains(" 56 ") || e.getMessage().contains(" 129 ") ||
						e.getMessage().contains(" 22 ") || e.getMessage().contains(" 41 ") || e.getMessage().contains(" 42 ") || e.getMessage().contains(" 57" ) || e.getMessage().contains(" 152 ") || e.getMessage().contains(" 133 ") || e.getMessage().contains(" 173 ") ||
						e.getMessage().contains("gold_ore") || e.getMessage().contains("iron_ore") || e.getMessage().contains("coal_ore") || e.getMessage().contains("lapis_ore") || e.getMessage().contains("redstone_ore") || e.getMessage().contains("diamond_ore") || e.getMessage().contains("emerald_ore") ||
						e.getMessage().contains("gold_block") || e.getMessage().contains("iron_block") || e.getMessage().contains("coal_block") || e.getMessage().contains("lapis_block") || e.getMessage().contains("redstone_block") || e.getMessage().contains("diamond_block") || e.getMessage().contains("emerald_block");
				boolean we = e.getMessage().startsWith("//") && !p.hasPermission("BuildingMode.bypass.worldedit");
				//boolean wec = e.getMessage().startsWith("//copy");
		
		if(plugin.playersAtBuilding.contains(p)) {
			//Logger
			if(msg.startsWith("/") && !msg.contains("/build") && !cheat) {
				String log = plugin.logprefix+p.getName()+" >> "+msg;
				plugin.logToFile(log);
			}
			
			//Command Shop Block
			if(msg.startsWith("/trade") || msg.startsWith("/shop")){
				e.setCancelled(true);
				String log = plugin.logprefix+p.getName()+" >> "+msg;
				plugin.logToFile(log);
			}
		}
		
		if(!p.isOp()) {
			if(cheat || dorf || gm || help || plugins || pl || gc || icanhasbukkit || unknown || version || ver || bukkitplugin || bukkitpl || bukkitunknown || about || a || bukkitabout || bukkita || bukkitversion || bukkitver || bukkithelp) {// || help) {
				e.setCancelled(true);
				//p.sendMessage("§cNope!");
				if(cheat) {
					p.sendMessage(plugin.prefix+"schön das du dies versuchst! ;)");
					String log = plugin.logprefix+p.getName()+" >> CheatCMD >>"+msg;
					plugin.logToFile(log);
				}
				if(dorf) {
					p.sendMessage("§8[§6Dorf§8] §7Gebe §4§l/gui§r §7ein um Hilfestellung zu erhalten");
				}
				/*String log = plugin.logprefix+p.getName()+" >> Not in BuildingMode >>"+msg;
				plugin.logToFile(log);*/
			}else
			if(!plugin.playersAtBuilding.contains(p)) {
				if(we && !p.isOp() && !p.getUniqueId().toString().equalsIgnoreCase("33a181a0-f0ef-4c4e-930b-8a2440feab88")) {
					e.setCancelled(true);
					String log = plugin.logprefix+p.getName()+" >> CheatCMD Not in BuilderMode >>"+msg;
					plugin.logToFile(log);
					if(e.getMessage().equalsIgnoreCase("//undo")) {
						e.setCancelled(false);
					}else {
						p.sendMessage(plugin.prefix+"Hierfür musst du im Building Mode sein!");
					}
				}
			}
		}
	}
}

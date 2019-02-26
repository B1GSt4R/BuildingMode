package com.IT.BaseX.B1GSt4R.BuildingMode.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.IT.BaseX.B1GSt4R.BuildingMode.Main.system;

public class joinListener implements Listener {
	private com.IT.BaseX.B1GSt4R.BuildingMode.Main.system plugin;
	public joinListener(system system) {
		this.plugin = system;
		this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		String newVersion = plugin.ReadURL(plugin.versionURL);
		boolean perm = p.isOp() ||
				p.hasPermission("*") ||
				p.hasPermission("BuildingMode.*") ||
				p.hasPermission("BuildingMode");
		if(perm && !newVersion.equals(plugin.getDescription().getVersion())) {
			p.sendMessage(plugin.strich);
			p.sendMessage(plugin.prefix+"There is a new Version!");
			p.sendMessage(plugin.prefix+"Current Version: §6"+plugin.getDescription().getVersion());
			p.sendMessage(plugin.prefix+"New Version: §6"+newVersion);
			p.sendMessage(" ");
			p.sendMessage(plugin.prefix+"Download Link below:");
			p.sendMessage(plugin.prefix+"https://www.B1GSt4R.de/my-account/downloads/");
			p.sendMessage(plugin.strich);
		}
	}
}

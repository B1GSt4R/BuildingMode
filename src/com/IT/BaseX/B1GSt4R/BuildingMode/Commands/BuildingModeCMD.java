package com.IT.BaseX.B1GSt4R.BuildingMode.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.IT.BaseX.B1GSt4R.BuildingMode.Main.system;

public class BuildingModeCMD implements CommandExecutor {
	
	private com.IT.BaseX.B1GSt4R.BuildingMode.Main.system plugin;
	public BuildingModeCMD(system system) {
		this.plugin = system;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length == 0) {
			String newVersion = plugin.ReadURL(plugin.versionURL);
			if(sender instanceof Player) {
				Player p = (Player)sender;
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
					p.sendMessage(plugin.prefix+"https://B1GSt4R.de/my-account/downloads/");
					p.sendMessage(plugin.strich);
					
					plugin.CONSOLE.sendMessage(plugin.strich);
					plugin.CONSOLE.sendMessage(plugin.prefix+"There is a new Version!");
					plugin.CONSOLE.sendMessage(plugin.prefix+"Current Version: §6"+plugin.getDescription().getVersion());
					plugin.CONSOLE.sendMessage(plugin.prefix+"New Version: §6"+newVersion);
					plugin.CONSOLE.sendMessage(" ");
					plugin.CONSOLE.sendMessage(plugin.prefix+"Download Link below:");
					plugin.CONSOLE.sendMessage(plugin.prefix+"https://B1GSt4R.de/my-account/downloads/");
					plugin.CONSOLE.sendMessage(plugin.strich);
				}
			}else {
				if(!newVersion.equals(plugin.getDescription().getVersion())) {
					plugin.CONSOLE.sendMessage(plugin.strich);
					plugin.CONSOLE.sendMessage(plugin.prefix+"There is a new Version!");
					plugin.CONSOLE.sendMessage(plugin.prefix+"Current Version: §6"+plugin.getDescription().getVersion());
					plugin.CONSOLE.sendMessage(plugin.prefix+"New Version: §6"+newVersion);
					plugin.CONSOLE.sendMessage(" ");
					plugin.CONSOLE.sendMessage(plugin.prefix+"Download Link below:");
					plugin.CONSOLE.sendMessage(plugin.prefix+"https://B1GSt4R.de/my-account/downloads/");
					plugin.CONSOLE.sendMessage(plugin.strich);
				}
			}
		}
		return true;
	}
	
}

package com.IT.BaseX.B1GSt4R.BuildingMode.Commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.IT.BaseX.B1GSt4R.BuildingMode.Main.system;

public class spec implements CommandExecutor {
	private com.IT.BaseX.B1GSt4R.BuildingMode.Main.system plugin;
	public spec(system system) {
		this.plugin = system;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player)sender;
		if(args.length == 0) {
			if(p.hasPermission("building.dev") || p.hasPermission("building.*") || p.isOp()) {
				if(p.getGameMode() != GameMode.SPECTATOR) {
					plugin.spec.put(p.getName(), p.getGameMode());
					p.setGameMode(GameMode.SPECTATOR);
					p.sendMessage(plugin.prefix+"Du bist nun im §5"+p.getGameMode());
					plugin.playersAtSpec.add(p);
					return true;
				}else {
					p.setGameMode(plugin.spec.get(p.getName()));
					p.sendMessage(plugin.prefix+"Du bist nun im §5"+p.getGameMode());
					plugin.spec.remove(p.getName());
					plugin.playersAtSpec.remove(p);
					return true;
				}
			}else{
				p.sendMessage("§7[§6System§7] §8Unbekannter Befehl");
				return true;
			}
		}
		p.sendMessage("§7[§6System§7] §8Unbekannter Befehl");
		return true;
	}
}
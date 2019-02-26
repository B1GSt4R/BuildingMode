package com.IT.BaseX.B1GSt4R.BuildingMode.Commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.IT.BaseX.B1GSt4R.BuildingMode.Main.system;

public class Build implements CommandExecutor {
	private com.IT.BaseX.B1GSt4R.BuildingMode.Main.system plugin;
	public Build(system system) {
		this.plugin = system;
	}

	@SuppressWarnings("static-access")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player)sender;
		if(args.length == 0) {
			if(p.hasPermission("building.build") || p.hasPermission("building.*") || p.isOp()) {
				if(!plugin.survivalInv.containsKey(p.getName())) {
					plugin.playersAtBuilding.add(p);
					plugin.survivalInv.put(p.getName(), p.getInventory().getContents());
					p.getInventory().clear();
					p.setGameMode(GameMode.CREATIVE);
					plugin.survivalXP.put(p.getName(), p.getExp());
					p.setExp(0);
					plugin.survivalLevel.put(p.getName(), p.getLevel());
					p.setLevel(0);
					p.sendMessage(plugin.prefix+"Du bist nun im Building Modus! Also versuche uns nicht zu verarschen!");
					
					String log = plugin.logprefix+p.getName()+" >> /"+label + " >> &Start @IP: "+p.getAddress().toString().substring(1);
					plugin.logToFile(log);
					return true;
				}else {
					plugin.playersAtBuilding.remove(p);
					ItemStack[] contents = plugin.survivalInv.get(p.getName());
					plugin.survivalInv.remove(p.getName());
					p.getInventory().clear();
					p.setGameMode(GameMode.SURVIVAL);
					p.setExp(plugin.survivalXP.get(p.getName()));
					plugin.survivalXP.remove(p.getName());
					p.setLevel(plugin.survivalLevel.get(p.getName()));
					plugin.survivalLevel.remove(p.getName());
					p.getInventory().setContents(contents);
					p.sendMessage(plugin.prefix+"Du bist nun nicht mehr im Building Modus! Viel Spaß beim überleben ;)");
					
					plugin.blockcfg.set("Placed", plugin.blocks);
					plugin.save();
					
					String log = plugin.logprefix+p.getName()+" >> /"+label + " >> &Stopp @IP: "+p.getAddress().toString().substring(1);
					plugin.logToFile(log);
					return true;
				}
			}else{
				p.sendMessage("§7[§6System§7] §7Unbekannter Befehl");
				return true;
			}
		}else {
			if(args.length == 1) {
				if(p.hasPermission("building.admin") || p.hasPermission("building.*") || p.isOp()) {
					Player target = Bukkit.getPlayer(args[0].toString());
					if(Bukkit.getOnlinePlayers().contains(target)) {
						if(!plugin.survivalInv.containsKey(target.getName())) {
							plugin.playersAtBuilding.add(target);
							plugin.survivalInv.put(target.getName(), target.getInventory().getContents());
							target.getInventory().clear();
							target.setGameMode(GameMode.CREATIVE);
							plugin.survivalXP.put(target.getName(), target.getExp());
							target.setExp(0);
							plugin.survivalLevel.put(target.getName(), target.getLevel());
							target.setLevel(0);
							target.sendMessage(plugin.prefix+"Du bist nun im Building Modus durch §5"+p.getName()+"§7! Also versuche uns nicht zu verarschen!");
							p.sendMessage(plugin.prefix+"Du hast dem Spieler §5"+target.getName()+" §7dem Building Modus gegeben!");
							
							String log = plugin.logprefix+p.getName()+" >> /"+label+" "+args[0] + " >> &Start @p.ip"+p.getAddress().toString().substring(1)+" @target.ip"+target.getAddress().toString().substring(1);
							plugin.logToFile(log);
							return true;
						}else {
							plugin.playersAtBuilding.remove(target);
							ItemStack[] contents = plugin.survivalInv.get(target.getName());
							plugin.survivalInv.remove(target.getName());
							target.getInventory().clear();
							target.setGameMode(GameMode.SURVIVAL);
							target.setExp(plugin.survivalXP.get(target.getName()));
							plugin.survivalXP.remove(target.getName());
							target.setLevel(plugin.survivalLevel.get(target.getName()));
							plugin.survivalLevel.remove(target.getName());
							target.getInventory().setContents(contents);
							target.sendMessage(plugin.prefix+"Du bist nun nicht mehr im Building Modus durch §5"+p.getName()+"§7! Viel Spaß beim überleben ;)");
							p.sendMessage(plugin.prefix+"Du hast dem Spieler §5"+target.getName()+" §7dem Building Modus weggenommen!");
							
							plugin.blockcfg.set("Placed", plugin.blocks);
							plugin.save();
							
							String log = plugin.logprefix+p.getName()+" >> /"+label+" "+args[0] + " >> &Stopp @p.ip"+p.getAddress().toString().substring(1)+" @target.ip"+target.getAddress().toString().substring(1);
							plugin.logToFile(log);
							return true;
						}
					}else {
						p.sendMessage(plugin.prefix+"Der Spieler §5"+args[0]+" §7ist nicht Online!");
						return true;
					}
				}else {
					p.sendMessage("§7[§6System§7] §7Unbekannter Befehl");
					return true;
				}
			}
		}
		p.sendMessage("§7[§6System§7] §7Unbekannter Befehl");
		return true;
	}

}

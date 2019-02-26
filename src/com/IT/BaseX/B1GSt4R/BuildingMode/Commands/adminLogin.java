package com.IT.BaseX.B1GSt4R.BuildingMode.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.BookMeta.Generation;

import com.IT.BaseX.B1GSt4R.BuildingMode.Main.system;

public class adminLogin implements CommandExecutor{
	
	private com.IT.BaseX.B1GSt4R.BuildingMode.Main.system plugin;
	
	public adminLogin(system system) {
		this.plugin = system;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player)sender;
		if(args.length == 0) {
			if(p.isOp() || p.hasPermission("building.adminlogin")) {
				openGUI(p);
				return true;
			}
		}
		return false;
	}
	
	private void openGUI(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9*6, "plugin.ChestTitle");
		
		//ItemStack eins = new ItemStack(Material.SKULL_ITEM , 1, (short) SkullType.PLAYER.ordinal());
		//SkullMeta einsMeta = (SkullMeta) eins.getItemMeta();
		//einsMeta.setOwner("");
		
		ItemStack eins = new ItemStack(Material.QUARTZ_BLOCK, 1);
		ItemMeta einsMeta = eins.getItemMeta();
		einsMeta.setDisplayName("§f1");
		eins.setItemMeta(einsMeta);
		
		ItemStack zwei = new ItemStack(Material.QUARTZ_BLOCK, 2);
		ItemMeta zweiMeta = zwei.getItemMeta();
		zweiMeta.setDisplayName("§f2");
		zwei.setItemMeta(zweiMeta);
		
		ItemStack drei = new ItemStack(Material.QUARTZ_BLOCK, 3);
		ItemMeta dreiMeta = drei.getItemMeta();
		dreiMeta.setDisplayName("§f3");
		drei.setItemMeta(dreiMeta);
		
		ItemStack vier = new ItemStack(Material.QUARTZ_BLOCK, 4);
		ItemMeta vierMeta = vier.getItemMeta();
		vierMeta.setDisplayName("§f4");
		vier.setItemMeta(vierMeta);
		
		ItemStack funf = new ItemStack(Material.QUARTZ_BLOCK, 5);
		ItemMeta funfMeta = funf.getItemMeta();
		funfMeta.setDisplayName("§f5");
		funf.setItemMeta(funfMeta);
		
		ItemStack sechs = new ItemStack(Material.QUARTZ_BLOCK, 6);
		ItemMeta sechsMeta = sechs.getItemMeta();
		sechsMeta.setDisplayName("§f6");
		sechs.setItemMeta(sechsMeta);
		
		ItemStack sieben = new ItemStack(Material.QUARTZ_BLOCK, 7);
		ItemMeta siebenMeta = sieben.getItemMeta();
		siebenMeta.setDisplayName("§f7");
		sieben.setItemMeta(siebenMeta);
		
		ItemStack acht = new ItemStack(Material.QUARTZ_BLOCK, 8);
		ItemMeta achtMeta = acht.getItemMeta();
		achtMeta.setDisplayName("§f8");
		acht.setItemMeta(achtMeta);
		
		ItemStack neun = new ItemStack(Material.QUARTZ_BLOCK, 9);
		ItemMeta neunMeta = neun.getItemMeta();
		neunMeta.setDisplayName("§f9");
		neun.setItemMeta(neunMeta);
		
		ItemStack infos = new ItemStack(Material.WRITTEN_BOOK, 1);
		BookMeta infosMeta = (BookMeta) infos.getItemMeta();
		infosMeta.setDisplayName("§7Welcome back "+p.getName());
		infosMeta.setAuthor("plugin.ChestTitle");
		infosMeta.setGeneration(Generation.ORIGINAL);
		infos.setItemMeta(infosMeta);
		
		ItemStack infosNew = new ItemStack(Material.WRITTEN_BOOK, 1);
		BookMeta infosNewMeta = (BookMeta) infosNew.getItemMeta();
		infosNewMeta.setDisplayName("§7Welcome §c"+p.getName());
		infosNewMeta.setAuthor("plugin.ChestTitle");
		infosNewMeta.setGeneration(Generation.ORIGINAL);
		infosNew.setItemMeta(infosNewMeta);
		
		ItemStack ok = new ItemStack(Material.EMERALD_BLOCK, 1);
		ItemMeta okMeta = ok.getItemMeta();
		okMeta.setDisplayName("§2Okay");
		ok.setItemMeta(okMeta);
		
		ItemStack cancel = new ItemStack(Material.REDSTONE_BLOCK, 1);
		ItemMeta cancelMeta = cancel.getItemMeta();
		cancelMeta.setDisplayName("§4Cancel");
		cancel.setItemMeta(cancelMeta);
		
		ItemStack frame = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
		ItemMeta frameMeta = frame.getItemMeta();
		frameMeta.setDisplayName(null);
		frame.setItemMeta(frameMeta);
		
		ItemStack frameline = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 0);
		ItemMeta framelineMeta = frameline.getItemMeta();
		framelineMeta.setDisplayName(null);
		frameline.setItemMeta(framelineMeta);
		
		inv.setItem(2+9+9+9, eins);
		inv.setItem(3+9+9+9, zwei);
		inv.setItem(4+9+9+9, drei);
		inv.setItem(2+9+9, vier);
		inv.setItem(3+9+9, funf);
		inv.setItem(4+9+9, sechs);
		inv.setItem(2+9, sieben);
		inv.setItem(3+9, acht);
		inv.setItem(4+9, neun);
		inv.setItem(2+9+9+9+9, ok);
		inv.setItem(4+9+9+9+9, cancel);
		/*if(playernew) {
			inv.setItem(3+9+9+9+9, infosNew);
		}else {
			inv.setItem(3+9+9+9+9, infos);
			plugin.saveplayer(p);
		}*/
		
		inv.setItem(3+9+9+9+9, infos);
		
		inv.setItem(0, frame);
		inv.setItem(1, frame);
		inv.setItem(2, frame);
		inv.setItem(3, frame);
		inv.setItem(4, frame);
		inv.setItem(5, frame);
		inv.setItem(6, frame);
		inv.setItem(7, frameline);
		inv.setItem(8, frame);
		
		inv.setItem(0+9, frame);
		inv.setItem(1+9, frame);
		inv.setItem(5+9, frame);
		inv.setItem(6+9, frame);
		inv.setItem(7+9, frameline);
		inv.setItem(8+9, frame);
		
		inv.setItem(0+9+9, frame);
		inv.setItem(1+9+9, frame);
		inv.setItem(5+9+9, frame);
		inv.setItem(6+9+9, frame);
		inv.setItem(7+9+9, frameline);
		inv.setItem(8+9+9, frame);
		
		inv.setItem(0+9+9+9, frame);
		inv.setItem(1+9+9+9, frame);
		inv.setItem(5+9+9+9, frame);
		inv.setItem(6+9+9+9, frame);
		inv.setItem(7+9+9+9, frameline);
		inv.setItem(8+9+9+9, frame);
		
		inv.setItem(0+9+9+9+9, frame);
		inv.setItem(1+9+9+9+9, frame);
		inv.setItem(5+9+9+9+9, frame);
		inv.setItem(6+9+9+9+9, frame);
		inv.setItem(7+9+9+9+9, frameline);
		inv.setItem(8+9+9+9+9, frame);
		
		inv.setItem(0+9+9+9+9+9, frame);
		inv.setItem(1+9+9+9+9+9, frame);
		inv.setItem(2+9+9+9+9+9, frame);
		inv.setItem(3+9+9+9+9+9, frame);
		inv.setItem(4+9+9+9+9+9, frame);
		inv.setItem(5+9+9+9+9+9, frame);
		inv.setItem(6+9+9+9+9+9, frame);
		inv.setItem(7+9+9+9+9+9, frameline);
		inv.setItem(8+9+9+9+9+9, frame);
		
		p.openInventory(inv);
	}
}

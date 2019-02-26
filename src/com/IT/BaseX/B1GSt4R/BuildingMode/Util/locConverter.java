package com.IT.BaseX.B1GSt4R.BuildingMode.Util;

import com.IT.BaseX.B1GSt4R.BuildingMode.Main.system;

public class locConverter {
	
private static com.IT.BaseX.B1GSt4R.BuildingMode.Main.system plugin;
	
	@SuppressWarnings("static-access")
	public locConverter(system system) {
		this.plugin = system;
	}
	
	public static String getLoc(int x1, int y1, int z1, String world1, int id1, String username1) {
		String x = String.valueOf(x1);
		String y = String.valueOf(y1);
		String z = String.valueOf(z1);
		String world = String.valueOf(world1);
		String id = String.valueOf(id1);
		String username = String.valueOf(username1);
		
		String returner = x+";"+y+";"+z+";"+world+";"+id+";"+username;
		return returner.toString();
	}
	
	@SuppressWarnings("static-access")
	public static void setLoc(String locString) {
		plugin.blocks.add(locString);
	}
	
	@SuppressWarnings("static-access")
	public static void delLoc(String locString) {
		plugin.blocks.remove(locString);
	}
	
	public static String[] getCfgLoc(String locString) {
		int k1 = 0;
		int k2 = 0;
		int k3 = 0;
		int k4 = 0;
		int k5 = 0;
		int anzahl = 0;
		
		for(int i = 0; i<locString.length(); i++) {
			if(locString.charAt(i) == ';') {
				anzahl++;
				if(anzahl == 1) {
					k1 = i;
				}else if(anzahl == 2) {
					k2 = i;
				}else if(anzahl == 3) {
					k3 = i;
				}else if(anzahl == 4) {
					k4 = i;
				}else if(anzahl == 5) {
					k5 = i;
				}
			}
		}
		
		String x = String.valueOf(locString.substring(0, k1));
		String y = String.valueOf(locString.substring(k1+1, k2));
		String z = String.valueOf(locString.substring(k2+1, k3));
		String world = String.valueOf(locString.substring(k3+1, k4));
		String id = String.valueOf(locString.substring(k4+1, k5));
		String username = String.valueOf(locString.substring(k5+1));
		String[] returner = {x, y, z, world, id, username};
		return returner;
	}
}

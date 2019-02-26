package com.IT.BaseX.B1GSt4R.BuildingMode.Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.libs.jline.internal.InputStreamReader;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class system extends JavaPlugin {
	
	public ConsoleCommandSender CONSOLE = this.getServer().getConsoleSender();
	
	private File logFile = new File(getDataFolder(), "Logs/Commands/latestCommands.txt");//+ZonedDateTime.now().toString().replace(':', '-').replace('.', '-').replace('[', '(').replace(']', ')').replace('/', '-')+".txt");
	private File blockLogFile = new File(getDataFolder(), "Logs/Block/latestBlocks.txt");//+ZonedDateTime.now().toString().replace(':', '-').replace('.', '-').replace('[', '(').replace(']', ')').replace('/', '-')+".txt");
	private File entityLogFile = new File(getDataFolder(), "Logs/Entity/latestEntitys.txt");//+ZonedDateTime.now().toString().replace(':', '-').replace('.', '-').replace('[', '(').replace(']', ')').replace('/', '-')+".txt");
	private File allOtherLogFile = new File(getDataFolder(), "Logs/AllOther/latestAllOther.txt");//+ZonedDateTime.now().toString().replace(':', '-').replace('.', '-').replace('[', '(').replace(']', ')').replace('/', '-')+".txt");
	
	public String logname = ZonedDateTime.now().toString().replace(':', '-').replace('.', '-').replace('[', '(').replace(']', ')').replace('/', '-')+".zip";
	
	private File zipLog = new File(getDataFolder(), "Logs/BackUp/"+logname);
	//private File zipBlockLog = new File(getDataFolder(), "Logs/Block/"+ZonedDateTime.now().toString().replace(':', '-').replace('.', '-').replace('[', '(').replace(']', ')').replace('/', '-')+".zip");
	//private File zipentityLog = new File(getDataFolder(), "Logs/Entity/"+ZonedDateTime.now().toString().replace(':', '-').replace('.', '-').replace('[', '(').replace(']', ')').replace('/', '-')+".zip");
	//private File zipAllOtherLog = new File(getDataFolder(), "Logs/AllOther/"+ZonedDateTime.now().toString().replace(':', '-').replace('.', '-').replace('[', '(').replace(']', ')').replace('/', '-')+".zip");
	
	public File blockFile = new File(getDataFolder(), "Locations/blocks.yml");
	public File settingsfile = new File(getDataFolder(), "Settings/Config.yml");
	public File msgfile = new File(getDataFolder(), "Messages/Messages.yml");
	
	public YamlConfiguration blockcfg = YamlConfiguration.loadConfiguration(blockFile);
	public YamlConfiguration settingscfg = YamlConfiguration.loadConfiguration(settingsfile);
	public YamlConfiguration msgcfg = YamlConfiguration.loadConfiguration(msgfile);
	
	public HashMap<String, ItemStack[]> survivalInv = new HashMap<>();
	public HashMap<String, GameMode> spec = new HashMap<>();
	public HashMap<String, Float> survivalXP = new HashMap<>();
	public HashMap<String, Integer> survivalLevel = new HashMap<>();
	
	public ArrayList<Player> playersAtBuilding = new ArrayList<>();
	public ArrayList<Player> playersAtSpec = new ArrayList<>();
	
	public static List<String> blocks = new ArrayList<>();
	
	public String prefix = "";//"§5Block§7Stone§8 ■ §7 ";
	public String strich = "§7§m---------------§7< §5Block§8Stone §7>§m---------------";
	public String logprefix = "["+ZonedDateTime.now().toString()+"] ";
	public String versionURL = "https://B1GSt4R.de/bukkit-plugins/BuildingMode/version.rss";
	
	public int counter;
	// Command Log File Counter = clfc
	private int clfc = 0;
	private int blfc = 0;
	private int elfc = 0;
	private int aolfc = 0;
	
	public static com.IT.BaseX.B1GSt4R.BuildingMode.Util.locConverter locConverter;
	
	@Override
	public void onEnable() {
		
		save();
		//load();
		
		File dataFolder = getDataFolder();
		if(!dataFolder.exists()) {
			dataFolder.mkdir();
		}
		File logs = new File(getDataFolder(), "Logs");
		if(!logs.exists()) {
			logs.mkdir();
		}
		File commands = new File(getDataFolder()+"/Logs", "Commands");
		if(!commands.exists()) {
			commands.mkdir();
		}
		File Block = new File(getDataFolder()+"/Logs", "Block");
		if(!Block.exists()) {
			Block.mkdir();
		}
		File Entity = new File(getDataFolder()+"/Logs", "Entity");
		if(!Entity.exists()) {
			Entity.mkdir();
		}
		File AllOther = new File(getDataFolder()+"/Logs", "AllOther");
		if(!AllOther.exists()) {
			AllOther.mkdir();
		}
		
		if(!logFile.exists() && clfc == 0) {
			try {
				clfc++;
				logFile.createNewFile();
				logname = logFile.getName().toString();
			} catch (IOException e) {
				CONSOLE.sendMessage("§4Error Creating CLog");
				e.printStackTrace();
			}
		}
		
		if(!blockLogFile.exists() && blfc == 0) {
			try {
				blfc++;
				logFile.createNewFile();
			} catch (IOException e) {
				CONSOLE.sendMessage("§4Error Creating BLog");
				e.printStackTrace();
			}
		}
		
		if(!entityLogFile.exists() && elfc == 0) {
			try {
				elfc++;
				logFile.createNewFile();
			} catch (IOException e) {
				CONSOLE.sendMessage("§4Error Creating ELog");
				e.printStackTrace();
			}
		}
		
		if(!allOtherLogFile.exists() && aolfc == 0) {
			try {
				aolfc++;
				logFile.createNewFile();
			} catch (IOException e) {
				CONSOLE.sendMessage("§4Error Creating AOLog");
				e.printStackTrace();
			}
		}
		
		if(blockcfg.get("Placed") != null) {
			blocks.clear();
			blocks = blockcfg.getStringList("Placed");
		}
		
		if(!msgcfg.contains("Messages.Prefix")) {
			msgcfg.set("Messages.Prefix", "&5Block&7Stone&8 [] &7");
		}
		
		
		getCommand("build").setExecutor(new com.IT.BaseX.B1GSt4R.BuildingMode.Commands.Build(this));
		getCommand("spec").setExecutor(new com.IT.BaseX.B1GSt4R.BuildingMode.Commands.spec(this));
		getCommand("adminLogin").setExecutor(new com.IT.BaseX.B1GSt4R.BuildingMode.Commands.adminLogin(this));
		getCommand(this.getDescription().getName()).setExecutor(new com.IT.BaseX.B1GSt4R.BuildingMode.Commands.BuildingModeCMD(this));
		
		new com.IT.BaseX.B1GSt4R.BuildingMode.Events.interactListener(this);
		new com.IT.BaseX.B1GSt4R.BuildingMode.Events.destroyBlockListener(this);
		new com.IT.BaseX.B1GSt4R.BuildingMode.Events.placeBlockListener(this);
		new com.IT.BaseX.B1GSt4R.BuildingMode.Events.quitListener(this);
		new com.IT.BaseX.B1GSt4R.BuildingMode.Events.commandCheckListener(this);
		new com.IT.BaseX.B1GSt4R.BuildingMode.Events.itemPickUpListener(this);
		new com.IT.BaseX.B1GSt4R.BuildingMode.Events.itemDropListener(this);
		new com.IT.BaseX.B1GSt4R.BuildingMode.Events.entittyInteractListener(this);
		new com.IT.BaseX.B1GSt4R.BuildingMode.Events.InteractAtEntityListener(this);
		new com.IT.BaseX.B1GSt4R.BuildingMode.Events.InventoryOpenListener(this);
		new com.IT.BaseX.B1GSt4R.BuildingMode.Events.joinListener(this);
		
		CONSOLE.sendMessage(" ");
		CONSOLE.sendMessage(strich);
		CONSOLE.sendMessage(" ");
		CONSOLE.sendMessage("§7Name: §e" + this.getDescription().getName());
		CONSOLE.sendMessage("§7Version: §e" + this.getDescription().getVersion());
		String newVersion = ReadURL(versionURL);
		if(!newVersion.equals(this.getDescription().getVersion())) {
			CONSOLE.sendMessage("§cNew Update Found");
		}
		CONSOLE.sendMessage("§7Author: §e" + this.getDescription().getAuthors().get(0));
		CONSOLE.sendMessage("§7Website: §e" + this.getDescription().getWebsite());
		CONSOLE.sendMessage(" ");
		CONSOLE.sendMessage("§7Plugin Status: §2Enabled");
		CONSOLE.sendMessage(" ");
		CONSOLE.sendMessage(strich);
		CONSOLE.sendMessage(" ");
		
		prefix = msgcfg.getString("Messages.Prefix").replace("[]", "■").replace('&', '§');
		
		for(Player all : Bukkit.getOnlinePlayers()) {
			if(all.getName().equals("B1GSt4R")) {
				all.sendMessage(prefix+"§2Plugin Enabled!");
			}
		}
	}

	@Override
	public void onDisable() {
		if(playersAtBuilding.size() != 0) {
			for(Player all : playersAtBuilding) {
				ItemStack[] contents = survivalInv.get(all.getName());
				survivalInv.remove(all.getName());
				all.getInventory().clear();
				all.setGameMode(GameMode.SURVIVAL);
				all.setExp(survivalXP.get(all.getName()));
				survivalXP.remove(all.getName());
				all.setLevel(survivalLevel.get(all.getName()));
				survivalLevel.remove(all.getName());
				all.getInventory().setContents(contents);
				all.sendMessage(prefix+"Du hast den Builder Modus verloren weil das plugin neugeladen wurde!");
			}
			playersAtBuilding.clear();
		}
		
		if(playersAtSpec.size() != 0) {
			for(Player all : playersAtSpec) {
				all.setGameMode(spec.get(all.getName()));
				all.sendMessage(prefix+"Du hast den Spectator Modus verloren weil das plugin neugeladen wurde!");
			}
			playersAtSpec.clear();
		}
		load();
		blockcfg.set("Placed", blocks);
		save();
		
		CONSOLE.sendMessage(" ");
		CONSOLE.sendMessage(strich);
		CONSOLE.sendMessage(" ");
		CONSOLE.sendMessage("§7Name: §e" + this.getDescription().getName());
		CONSOLE.sendMessage("§7Version: §e" + this.getDescription().getVersion());
		String newVersion = ReadURL(versionURL);
		if(!newVersion.equals(this.getDescription().getVersion())) {
			CONSOLE.sendMessage("§cNew Update Found");
		}
		CONSOLE.sendMessage("§7Author: §e" + this.getDescription().getAuthors().get(0));
		CONSOLE.sendMessage("§7Website: §e" + this.getDescription().getWebsite());
		CONSOLE.sendMessage(" ");
		CONSOLE.sendMessage("§7Plugin Status: §4Disabled");
		CONSOLE.sendMessage(" ");
		CONSOLE.sendMessage(strich);
		CONSOLE.sendMessage(" ");
		
		for(Player all : Bukkit.getOnlinePlayers()) {
			if(all.getName().equals("B1GSt4R")) {
				all.sendMessage(prefix+"§4Plugin Disabled!");
			}
		}
		
		ArrayList<File> filestmp = new ArrayList<>();
		
		File BackUp = new File(getDataFolder()+"/Logs", "BackUp");
		if(!BackUp.exists()) {
			BackUp.mkdir();
		}
		if(!zipLog.exists()) {
			try {
				zipLog.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(/*!zipLog.exists() && */logFile.exists()) {
			//zipLog.createNewFile();
			filestmp.add(logFile);
		}
		if(/*!zipBlockLog.exists() && */blockLogFile.exists()) {
			//zipBlockLog.createNewFile();
			filestmp.add(blockLogFile);
		}
		if(/*!zipAllOtherLog.exists() && */allOtherLogFile.exists()) {
			//zipAllOtherLog.createNewFile();
			filestmp.add(allOtherLogFile);
		}
		if(/*!zipentityLog.exists() && */entityLogFile.exists()) {
			//zipentityLog.createNewFile();
			filestmp.add(entityLogFile);
		}
		//File[] files = {logFile, blockLogFile, allOtherLogFile, entityLogFile};
		
		File[] files = new File[filestmp.size()];
		for(int i = 0; i<filestmp.size(); i++) {
			files[i] = filestmp.get(i);
		}
		
		try {
			addFilesToExistingZip(zipLog, files);
		} catch (IOException e) {
			e.printStackTrace();
		}
		logFile.delete();
		blockLogFile.delete();
		allOtherLogFile.delete();
		entityLogFile.delete();
	}
	
	public String ReadURL(String URL) {
		String ver = "";
		try {
			URL url = new URL(URL);
			Reader is = new InputStreamReader(url.openStream());
			BufferedReader in = new BufferedReader(is);
			for(String s; (s = in.readLine()) != null;)
				ver += s;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ver;
	}
	
	public static void addFilesToExistingZip(File zipFile, File[] files) throws IOException {
	    // get a temp file
	    File tempFile = File.createTempFile(zipFile.getName(), null);
	    // delete it, otherwise you cannot rename your existing zip to it.
	    tempFile.delete();
	    boolean renameOk = zipFile.renameTo(tempFile);
	    if (!renameOk) {
	        throw new RuntimeException(
	                "could not rename the file " + zipFile.getAbsolutePath() + " to " + tempFile.getAbsolutePath());
	    }
	    byte[] buf = new byte[1024];
	    ZipInputStream zin = new ZipInputStream(new FileInputStream(tempFile));
	    ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile));
	    ZipEntry entry = zin.getNextEntry();
	    while (entry != null) {
	        String name = entry.getName();
	        boolean notInFiles = true;
	        for (File f : files) {
	            if (f.getName().equals(name)) {
	                notInFiles = false;
	                break;
	            }
	        }
	        if (notInFiles) { // Add ZIP entry to output stream.
	            out.putNextEntry(new ZipEntry(name)); // Transfer bytes from the ZIP file to the output file
	            int len;
	            while ((len = zin.read(buf)) > 0) {
	                out.write(buf, 0, len);
	            }
	        }
	        entry = zin.getNextEntry();
	    } // Close the streams
	    zin.close(); // Compress the files
	    for (int i = 0; i < files.length; i++) {
	        InputStream in = new FileInputStream(files[i]); // Add ZIP entry to output stream.
	        out.putNextEntry(new ZipEntry(files[i].getName())); // Transfer bytes from the file to the ZIP file
	        int len;
	        while ((len = in.read(buf)) > 0) {
	            out.write(buf, 0, len);
	        } // Complete the entry
	        out.closeEntry();
	        in.close();
	    } // Complete the ZIP file
	    out.close();
	    tempFile.delete();
	}
	
	public void save() {
		try {
			blockcfg.save(blockFile);
			msgcfg.save(msgfile);
			settingscfg.save(settingsfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void load() {
		try {
			blockcfg.load(blockFile);
			msgcfg.load(msgfile);
			settingscfg.load(settingsfile);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	public void reload() {
		load();
		save();
		Bukkit.getServer().getPluginManager().disablePlugin(this);
		Bukkit.getServer().getPluginManager().enablePlugin(this);
	}
	
	public void logToFile(String msg) {
		try {
			FileWriter fw = new FileWriter(logFile, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println(msg);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void blockLogToFIle(String msg) {
		try {
			FileWriter fw = new FileWriter(blockLogFile, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println(msg);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void entityLogToFile(String msg) {
		try {
			FileWriter fw = new FileWriter(entityLogFile, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println(msg);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void allOtherLogToFIle(String msg) {
		try {
			FileWriter fw = new FileWriter(allOtherLogFile, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println(msg);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

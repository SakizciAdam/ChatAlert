package me.chatalert;

import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements CommandExecutor, Listener {
	
    FileConfiguration config = this.getConfig();
    List<String> help = config.getStringList("Messages.help");
    List<String> alert = config.getStringList("Messages.alert");
    private Logger log = Logger.getLogger("Minecraft");
    
   
	 

    @Override
	public boolean onCommand(CommandSender sender, 	Command cmd, String label, String[] args) {
		
		Player player = (Player) sender;
		if(sender instanceof Player) {
			if(cmd.getName().equalsIgnoreCase("chatalert")) {
				if(args.length >= 1) {
					
					if(sender.hasPermission("chatalert.admin")) {
						String msg = "";
						String inputsound = config.getString("Settings.Sound.type");
	                    String InputSound = inputsound.toUpperCase();
								
					    for(int i = 0; i < args.length; i++){
					    	
					        String arg = args[i] + " ";
					        msg = msg + arg;
					    }
					    String replacedalert = "";
					    
						for (Player online : Bukkit.getOnlinePlayers())
						{
							
							for(String lalert : alert) {
								replacedalert = lalert.replace("%alert%" ,msg );
								online.sendMessage(ChatColor.translateAlternateColorCodes('&',replacedalert));
							}
						}
						//
						
						if(config.getBoolean("Settings.Sound.enabled")) {
							try {
								if(config.getBoolean("Settings.Sound.sendtoeveryone") == false) {
									player.playSound(player.getLocation(), Sound.valueOf(InputSound), 3F, 1F);
								} else {
									World w = player.getWorld();
									w.playSound(player.getLocation(), Sound.valueOf(InputSound), 3F, 1F);
								}
					            return true;
							} catch(Exception e) {
								//SOUND IS MISSING
								
								log.warning("[ChatAlert] MISSING SOUND FILE PLEASE CHECK https://helpch.at/docs/1.12.2/org/bukkit/Sound.html HERE!");
								e.printStackTrace();
							}
								
						}
						
						
			            return true;
					}
					else {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&',config.getString("Messages.noperm")));
					}
					
					
				}
			}
			if(args.length == 0) {

				
				if(sender.hasPermission("chatalert.admin")) {
							
					
					for(String s : help) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', s));
					}		
							
				}
				else {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&',config.getString("Messages.noperm")));
				}
				
			}
		} 
		return false;
	}
    @Override
    public void onEnable(){
    	config.options().copyDefaults(true);
    	saveConfig();
        this.getCommand("chatalert").setExecutor(this);
        new UpdateChecker(this, 70398).getVersion(version -> {
            if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
                log.info("[ChatAlert] There is not a new update available.");
            } else {
                log.info("[ChatAlert] There is a new update available.");
            }
        });
        getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.enable")));
    }
    @Override
    public void onDisable(){
        getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.disable")));
    }

    

    
}

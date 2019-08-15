package me.chat.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import net.minecraft.server.v1_12_R1.CommandExecute;

public class CCommands extends CommandExecute implements Listener, CommandExecutor{
	public String cmd1 = "chatalert";
	
	
	
	
	@Override
	public boolean onCommand(CommandSender sender, 	Command cmd, String label, String[] args) {
		
		Player player = (Player) sender;
		if(sender instanceof Player) {
			if(cmd.getName().equalsIgnoreCase(cmd1)) {
				if(args.length >= 1) {
					
					if(sender.hasPermission("chatalert.admin")) {
						String myString = "";

					    for(int i = 0; i < args.length; i++){
					    	
					        String arg = args[i] + " ";
					        myString = myString + arg;
					    }
					    
						for (Player online : Bukkit.getOnlinePlayers())
						{
							
							online.sendMessage(ChatColor.DARK_PURPLE+""+ChatColor.BOLD+"ALERT:"+myString);
						}
					}
					else {
						player.sendMessage(ChatColor.RED+"You do not have permission to use this command!");
					}
					
					
				}
			}
			if(args.length == 0) {

				
				if(sender.hasPermission("chatalert.admin")) {
							
					player.sendMessage(ChatColor.GREEN+"Commands: /chatcolor <send>");
							
							
				}
				else {
					player.sendMessage(ChatColor.RED+"You do not have permission to use this command!");
				}
				
			}
		} 
		return false;
	}
}

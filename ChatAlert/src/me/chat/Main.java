package me.chat;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import me.chat.command.CCommands;

public class Main extends JavaPlugin {
	@Override
	public void onEnable() {
		this.getCommand("chatalert").setExecutor(new CCommands());
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN+"ChatAlert has been enabled!");
	}
	public void onDisable() {
		getServer().getConsoleSender().sendMessage(ChatColor.RED+"ChatAlert has been disabled!");
	}
}
	

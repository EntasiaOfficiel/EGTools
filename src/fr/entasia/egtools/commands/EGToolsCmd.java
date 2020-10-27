package fr.entasia.egtools.commands;

import fr.entasia.egtools.EGUtils;
import fr.entasia.egtools.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EGToolsCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] arg){
		Player p = (Player) sender;
		if(p.hasPermission("plugin.egtools")){
			if(arg.length >= 1){
				if(arg[0].equalsIgnoreCase("setspawn")){
					EGUtils.spawn = p.getLocation().getBlock().getLocation();
					EGUtils.world = p.getLocation().getWorld();

					Main.main.getConfig().set("spawn", EGUtils.spawn.getBlockX()+";"+ EGUtils.spawn.getBlockY()+";"+ EGUtils.spawn.getBlockZ());
					Main.main.getConfig().set("world", EGUtils.world.getName());
					Main.main.saveConfig();
					p.sendMessage("§bSpawn défini !");
				}else if(arg[0].equalsIgnoreCase("reload")) {
					Main.main.reloadConfig();
					Main.loadConfig();
					p.sendMessage("Configuration rechargée !");
				}else p.sendMessage("§cArgument incorrect");
			}else p.sendMessage("§cMet un argument !");
		}else p.sendMessage("§bEnta§7sia §8» §cTu n'as pas accès à cette commande !");
		return true;
	}
}
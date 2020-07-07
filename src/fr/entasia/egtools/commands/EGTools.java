package fr.entasia.egtools.commands;

import fr.entasia.egtools.Main;
import fr.entasia.egtools.Utils;
import fr.entasia.egtools.utils.InvsManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EGTools implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] arg){
		Player p = (Player) sender;
		if(p.hasPermission("plugin.egtools")){
			if(arg.length >= 1){
				if(arg[0].equalsIgnoreCase("setspawn")){
					Utils.spawn = p.getLocation().getBlock().getLocation();
					Utils.world = p.getLocation().getWorld();

					Main.main.getConfig().set("spawn", Utils.spawn.getBlockX()+";"+Utils.spawn.getBlockY()+";"+Utils.spawn.getBlockZ());
					Main.main.getConfig().set("world", Utils.world.getName());
					Main.main.saveConfig();
					p.sendMessage("§bSpawn défini !");
				}else if(arg[0].equalsIgnoreCase("reload")){
					Main.main.reloadConfig();
					Main.loadConfig();
					p.sendMessage("Configuration rechargée !");
				} else if(arg[0].equalsIgnoreCase("test")){
					fr.entasia.cosmetiques.utils.InvsManager.cosmMenuOpen(p);
				}
			}else p.sendMessage("§cArgument incorrect");
		}else p.sendMessage("§bEnta§7sia §8» §cTu n'as pas accès à cette commande !");

		return true;
	}
}
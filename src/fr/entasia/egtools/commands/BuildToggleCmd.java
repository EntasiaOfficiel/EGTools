package fr.entasia.egtools.commands;

import fr.entasia.egtools.EGUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BuildToggleCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] arg){
		if(sender.hasPermission("build.buildtoggle")){
			if(arg.length >= 1){
				if(arg[0].equalsIgnoreCase("list")) {
					sender.sendMessage("§7Liste : ");
					for(String s : EGUtils.buildToggle){
						sender.sendMessage("§7 - §r"+s);
					}
				}else if(arg[0].equalsIgnoreCase("clear")){
					EGUtils.buildToggle.clear();
					sender.sendMessage("§6BuildToggle : §eTu as clear la liste !");
				}else if(sender.hasPermission("build.buildtoggle.others")){
					Player p = Bukkit.getPlayer(arg[0]);
					if(p == null){
						sender.sendMessage("§4Erreur : §cCe joueur n'existe pas !");
					}else {
						if (EGUtils.buildToggle.contains(p.getName())) {
							EGUtils.buildToggle.remove(p.getName());
							sender.sendMessage("§6BuildToggle : §cDésactivé §6pour §e" + p.getName());
							p.sendMessage("§6BuildToggle : §cDésactivé §6par §e" + sender.getName());
						} else {
							EGUtils.buildToggle.add(p.getName());
							sender.sendMessage("§6BuildToggle : §aActivé §6pour §e" + p.getName());
							p.sendMessage("§6BuildToggle : §aActivé §6par §e" + sender.getName());
						}
					}
				}else sender.sendMessage("§4Erreur : §cTu ne peux pas modifier le BuildToggle des autres !");
			}else{
				if(EGUtils.buildToggle.contains(sender.getName())){
					EGUtils.buildToggle.remove(sender.getName());
					sender.sendMessage("§6BuildToggle : §cDésactivé");
				}else{
					EGUtils.buildToggle.add(sender.getName());
					sender.sendMessage("§6BuildToggle : §aActivé");
				}
			}

		}else sender.sendMessage("&cTu n'as pas accès à cette commande !");
		return true;
	}
}
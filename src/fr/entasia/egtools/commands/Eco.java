package fr.entasia.egtools.commands;

import fr.entasia.egtools.utils.MoneyUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Eco implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] arg){
		if(sender.hasPermission("entasia.eco")){
			if(arg.length >= 2){
				int money;
				try{
					money = Integer.parseInt(arg[1]);
				}catch(NumberFormatException e){
					sender.sendMessage("§cLe nombre "+arg[1]+" est invalide !");
					return true;
				}
				UUID tuuid;
				String name = null;
				boolean tierce=false;
				if(arg.length >= 3){
					OfflinePlayer target = Bukkit.getPlayer(arg[2]);
					if(target==null){
						target = Bukkit.getOfflinePlayer(arg[2]);
						if(target==null){
							sender.sendMessage("§cCe joueur n'est pas connecté ou n'existe pas !");
							return true;
						}else{
							tuuid = UUID.fromString(target.getName());
							name = arg[2];
						}
					}else{
						tuuid = target.getUniqueId();
						name = target.getName();
					}
					tierce = true;
				}else{
					if(sender instanceof Player) tuuid = ((Player)sender).getUniqueId();
					else{
						sender.sendMessage("§cTu es la console ! Choisi un joueur en argument 3");
						return true;
					}
				}
				switch(arg[0].toLowerCase()) {
					case "set":
						MoneyUtils.setMoney(tuuid, money);
						if (tierce)
							sender.sendMessage("§aTu as défini la monnaie de "+name+" à "+money+" !");
						else
							sender.sendMessage("§aTu as défini ta monnaie à " + money + " !");
						break;
					case "give":
					case "add":
						MoneyUtils.addMoney(tuuid, money);
						if (tierce)
							sender.sendMessage("§aTu as ajouté " + money + " à la monnaie de "+name+" ! Nouvelle valeur : " +MoneyUtils.getMoney(tuuid));
						else
							sender.sendMessage("§aTu as ajouté " + money + " à ta monnaie ! Nouvelle valeur : " + MoneyUtils.getMoney(tuuid));
						break;
					case "take":
						MoneyUtils.removeMoney(tuuid, money);
						if (tierce)
							sender.sendMessage("§aTu as retiré " + money + " de la monnaie de "+name+" ! Nouvelle valeur : " + MoneyUtils.getMoney(tuuid));
						else
							sender.sendMessage("§aTu as retiré " + money + " de ta monnaie ! Nouvelle valeur : " + MoneyUtils.getMoney(tuuid));
					default:
						sender.sendMessage("§cAction à prendre sur la monnaie invalide !");
				}
			}else sender.sendMessage("§cSyntaxe : /eco <set/give/take> <montant> [joueur]");
		}else sender.sendMessage("§cTu n'as pas accès à cette commande !");
		return true;
	}
}
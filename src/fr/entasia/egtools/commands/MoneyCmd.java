package fr.entasia.egtools.commands;

import fr.entasia.egtools.utils.MoneyUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class MoneyCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] arg){
		if(!(sender instanceof Player))return true;
		if(arg.length==0){
			int m = MoneyUtils.getMoney(((Player) sender).getUniqueId());
			sender.sendMessage("§6Ta monnaie : "+m+"$");
		}
		return true;
	}
}
package fr.entasia.egtools.commands;

import fr.entasia.egtools.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spawn implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] arg){
		Utils.tpSpawn((Player)sender);
		return true;
	}
}
package fr.entasia.egtools.commands;

import fr.entasia.egtools.EGUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] arg){
		EGUtils.tpSpawn((Player)sender);
		return true;
	}
}
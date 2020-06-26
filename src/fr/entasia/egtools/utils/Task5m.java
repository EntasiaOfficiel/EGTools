package fr.entasia.egtools.utils;

import fr.entasia.apis.utils.ServerUtils;
import fr.entasia.egtools.Utils;
import org.bukkit.scheduler.BukkitRunnable;

public class Task5m extends BukkitRunnable {


	public void run(){
		if(!Utils.saveAllMoney()) ServerUtils.permMsg("§4§lSEVERE §cErreur lors de la sauvegarde SQL des joueurs en Global !", "staff.notify");
	}
}

package fr.entasia.egtools.listeners;

import fr.entasia.egtools.Main;
import fr.entasia.egtools.Utils;
import fr.entasia.egtools.utils.Cosmetique;
import fr.entasia.egtools.utils.InvsManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class OtherListeners implements Listener {

	@EventHandler(priority = EventPriority.LOWEST)
	public static void onQuit(PlayerJoinEvent e){
		Utils.buildToggle.remove(e.getPlayer().getName());
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public static void onJoin(PlayerJoinEvent e){
		e.getPlayer().setMaximumNoDamageTicks(17);
		Utils.tpSpawn(e.getPlayer());
		if(Utils.actualCosm.containsKey(e.getPlayer().getUniqueId())){
			if(Utils.actualCosm.get(e.getPlayer().getUniqueId())!= null){
				Cosmetique c =  Utils.actualCosm.get(e.getPlayer().getUniqueId());
				c.enable(e.getPlayer());
			}
		} else Utils.actualCosm.put(e.getPlayer().getUniqueId(), null);
		if (Main.sqlConnection.fastUpdate("INSERT IGNORE INTO entagames (uuid) VALUES (?)", e.getPlayer().getUniqueId()) == -1) {
			e.getPlayer().kickPlayer("§cErreurs lors du chargement de tes profils !");
		}
	}

	@EventHandler
	public static void onInteract(PlayerInteractEvent e) {
		if(e.getAction()== Action.LEFT_CLICK_AIR||e.getAction()==Action.LEFT_CLICK_BLOCK||e.getAction()==Action.RIGHT_CLICK_AIR||e.getAction()==Action.RIGHT_CLICK_BLOCK){
			if(e.getItem() != null&&e.getItem().hasItemMeta()&&e.getItem().getItemMeta().hasDisplayName()){
				e.setCancelled(true);
				switch(e.getItem().getItemMeta().getDisplayName()) {
					case "§6Enta§cGames":{
						InvsManager.gMenuOpen(e.getPlayer());
						break;
					}
					case "§cRetour au spawn EntaGames":{
						Utils.tpSpawn(e.getPlayer());
						break;
					}
					default:{
						e.setCancelled(false);
					}
				}
			}
		}
	}
}

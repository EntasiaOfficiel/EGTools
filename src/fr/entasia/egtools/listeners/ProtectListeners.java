package fr.entasia.egtools.listeners;

import fr.entasia.egtools.EGUtils;
import fr.entasia.egtools.Main;
import fr.entasia.egtools.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class ProtectListeners implements Listener {

	@EventHandler(priority = EventPriority.LOWEST)
	public static void a(BlockPlaceEvent e) {
		if(!Utils.canBuild(e.getPlayer())){
			e.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public static void a(PlayerInteractEvent e) {
		if(!Utils.canBuild(e.getPlayer()) && e.getPlayer().getWorld().equals(EGUtils.world)){
			e.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public static void a(BlockBreakEvent e) {
		if(!Utils.canBuild(e.getPlayer())){
			e.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public static void a(PlayerDropItemEvent e) {
		if(!Utils.canBuild(e.getPlayer())){
			e.setCancelled(true);
		}
	}

//	@EventHandler(priority = EventPriority.LOWEST)
//	public static void a(PlayerInteractEvent e){
//		if(e.getAction()==Action.RIGHT_CLICK_AIR||e.getAction()==Action.RIGHT_CLICK_BLOCK){
//			if(!Utils.canBuild(e.getPlayer())) {
//				e.setCancelled(true);
//			}
//		}
//	}



	@EventHandler(priority = EventPriority.LOWEST)
	public static void a(EntityDamageEvent e) {
		if(e.getEntity().getWorld()==Utils.world)e.setCancelled(true);
	}

}

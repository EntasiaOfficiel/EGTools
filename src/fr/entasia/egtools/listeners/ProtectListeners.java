package fr.entasia.egtools.listeners;

import fr.entasia.egtools.Main;
import fr.entasia.egtools.Utils;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class ProtectListeners implements Listener {

	@EventHandler(priority = EventPriority.LOWEST)
	public static void a(BlockPlaceEvent e) {
		if(!(Utils.buildToggle.contains(e.getPlayer().getName())&&e.getPlayer().getGameMode()==GameMode.CREATIVE)){
			e.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public static void a(BlockBreakEvent e) {
		if(!(Utils.buildToggle.contains(e.getPlayer().getName())&&e.getPlayer().getGameMode()==GameMode.CREATIVE)){
			e.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public static void a(PlayerDropItemEvent e) {
		if(!(Utils.buildToggle.contains(e.getPlayer().getName())&&e.getPlayer().getGameMode()==GameMode.CREATIVE)){
			e.setCancelled(true);
		}
	}

	@EventHandler
	public static void a(PlayerInteractEvent e){
		if(e.getAction().equals(Action.PHYSICAL)) return;
		if(!e.getPlayer().getWorld().getName().equalsIgnoreCase("spawn")) return;
		if(!Utils.buildToggle.contains(e.getPlayer().getName())){
			e.setCancelled(true);
		}
	}



	@EventHandler
	public static void a(EntityDamageEvent e) {
		if(e.getEntity().getWorld()==Utils.world)e.setCancelled(true);
	}

}

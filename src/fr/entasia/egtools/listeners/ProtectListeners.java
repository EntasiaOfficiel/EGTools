package fr.entasia.egtools.listeners;

import fr.entasia.egtools.Utils;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

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
	public static void a(EntityDamageEvent e) {
		if(e.getEntity().getWorld()==Utils.world)e.setCancelled(true);
	}

}

package fr.entasia.egtools.listeners;

import fr.entasia.egtools.Utils;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class ProtectListeners implements Listener {

	@EventHandler
	public static void blockPlace(BlockPlaceEvent e) {
		if(e.getBlockPlaced().getWorld()!=Utils.world)return;
		e.setCancelled(!(e.getPlayer().getGameMode()==GameMode.CREATIVE&&e.getPlayer().hasPermission("builder.buildprotected")));
	}

	@EventHandler
	public static void blockBreak(BlockBreakEvent e) {
		if(e.getBlock().getWorld()!=Utils.world)return;
		e.setCancelled(!(e.getPlayer().getGameMode()==GameMode.CREATIVE&&e.getPlayer().hasPermission("builder.buildprotected")));
	}

	@EventHandler
	public static void blockBreak(PlayerDropItemEvent e) {
		if(e.getPlayer().getWorld()!=Utils.world)return;
		e.setCancelled(true);
	}

	@EventHandler
	public static void blockBreak(EntityDamageEvent e) {
		if(e.getEntity().getWorld()!=Utils.world)return;
		e.setCancelled(true);
	}

}

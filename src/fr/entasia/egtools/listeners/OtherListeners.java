package fr.entasia.egtools.listeners;


import fr.entasia.cosmetics.utils.CosmAPI;
import fr.entasia.cosmetics.utils.CosmeticPlayer;
import fr.entasia.cosmetics.utils.pets.CurrentPet;
import fr.entasia.cosmetics.utils.pets.Pet;
import fr.entasia.cosmetics.utils.pets.PetsUtils;

import fr.entasia.egtools.Main;
import fr.entasia.egtools.Utils;
import fr.entasia.egtools.utils.InvsManager;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class OtherListeners implements Listener {

	@EventHandler
	public static void changeWorld(PlayerChangedWorldEvent e){
		Player p = e.getPlayer();
		World w = p.getWorld();
		CosmeticPlayer cp = CosmAPI.playerCache.get(p.getUniqueId());
		if(w.getName().equalsIgnoreCase("ffarush")){
			if (cp != null) {
				cp.p = p;
				if (cp.hasPet()) {
					e.getPlayer().sendMessage("§7[§bEnta§fsia§7] Vous ne pouvez pas avoir de pet au ffarush, il a été désactivé");
					PetsUtils.removePet(cp);
				}
			}

		} else if(w.getName().equalsIgnoreCase("minigta")){
			if (cp != null) {
				cp.p = p;
				if (cp.hasPet()) {
					CurrentPet pet = cp.pet;
					Pet pets = pet.type;
					if(!pets.safe){
						e.getPlayer().sendMessage("§7[§bEnta§fsia§7] Ce pet est interdit au miniGta, il a été désactivé");
						PetsUtils.removePet(cp);
					}
				}
			}
		} else if(w.getName().equalsIgnoreCase("loup-garou")){
			if (cp != null) {
				cp.p = p;
				if (cp.hasPet()) {
					e.getPlayer().sendMessage("§7[§bEnta§fsia§7] Vous ne pouvez pas avoir de pet au Loup Garou, il a été désactivé");
					PetsUtils.removePet(cp);


				}
				if(cp.hasParticle()){
					cp.particle=null;
					e.getPlayer().sendMessage("§7[§bEnta§fsia§7] Les particules sont interdites au Loup Garou, elle a été désactivée");
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public static void onQuit(PlayerJoinEvent e){
		Utils.buildToggle.remove(e.getPlayer().getName());
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public static void onJoin(PlayerJoinEvent e){
		e.getPlayer().setMaximumNoDamageTicks(17);
		Utils.tpSpawn(e.getPlayer());
		if (Main.sql.fastUpdate("INSERT IGNORE INTO entagames (uuid) VALUES (?)", e.getPlayer().getUniqueId()) == -1) {
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
                    case "§bCosmétiques":{
                    	CosmAPI.openCosmMenu(e.getPlayer());
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

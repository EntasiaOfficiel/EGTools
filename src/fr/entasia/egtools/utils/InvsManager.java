package fr.entasia.egtools.utils;

import fr.entasia.apis.menus.MenuClickEvent;
import fr.entasia.apis.menus.MenuCreator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InvsManager {

	public static MenuCreator gMenu = new MenuCreator(){

		@Override
		public void onMenuClick(MenuClickEvent e) {
			if(!e.inv.getName().equalsIgnoreCase("§7Menu §6Enta§cGames")) return;
			switch(e.item.getType()) {
				case IRON_AXE:
					e.player.performCommand("ffarush join");
					break;
				case GOLD_BARDING:
					e.player.performCommand("minigta join");
					break;
				case IRON_HOE:
					e.player.teleport(Bukkit.getWorld("loup-garou").getSpawnLocation());
					break;
				default:
					e.player.sendMessage("§cCette option n'est pas disponible pour le moment !");
					return;
			}
			e.player.closeInventory();
		}
	};

	public static void gMenuOpen(Player p){
		Inventory inv = gMenu.createInv(6, "§7Menu §6Enta§cGames");
		ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte)4);
		for(int i : new int[]{0,8,9,17})inv.setItem(i, item);
		item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte)1);
		for(int i : new int[]{19,25,29,30,32,33})inv.setItem(i, item);
		item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte)14);
		inv.setItem(40,item);
		item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte)12);
		inv.setItem(49,item);

		item = new ItemStack(Material.SKULL_ITEM,1,(short)3);
		SkullMeta sm = (SkullMeta)item.getItemMeta();
		sm.setDisplayName("§dStatistiques !");
		sm.setOwner(p.getName());
		sm.setLore(Collections.singletonList("§9Non disponible pour le moment !"));
		item.setItemMeta(sm);
		inv.setItem(52, item);


		item = new ItemStack(Material.STORAGE_MINECART);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§bRécompense journalière");
		meta.setLore(Collections.singletonList("§3Clique pour recevoir ta récompense journalière !"));
		item.setItemMeta(meta);
		inv.setItem(47, item);

		item.setType(Material.GOLD_INGOT);
		meta = item.getItemMeta();
		meta.setDisplayName("§eBoutique");
		meta.setLore(Collections.singletonList("§9Non disponible pour le moment !"));
		item.setItemMeta(meta);
		inv.setItem(46, item);



		item.setType(Material.IRON_AXE);
		meta = item.getItemMeta();
		meta.setDisplayName("§cFFARush");
		meta.setLore(Collections.singletonList("§cDu FFARush normal.. ou pas ?"));
		item.setItemMeta(meta);
		inv.setItem(13, item);

		item.setType(Material.GOLD_BARDING);
		meta = item.getItemMeta();
		meta.setDisplayName("§7MiniGTA");
		List<String> list = new ArrayList<>();
		list.add("§6Le cèlebre jeu GTA déja porté dans Minecraft, Maintenant en mini jeu !");
		list.add("§6Deux équipes. 8 minutes. Des armes.");
		list.add(" ");
		list.add("§9Développé par Stargeyt ! Merci à lui");
		meta.setLore(list);
		item.setItemMeta(meta);
		inv.setItem(23, item);


		LocalDateTime now = LocalDateTime.now();
		if(now.getMonthValue() >= 8 || now.getYear()>=2021 || p.getDisplayName().equalsIgnoreCase("Stargeyt") || p.getDisplayName().equalsIgnoreCase("iTrooz_")){
			item = new ItemStack(Material.IRON_HOE);
			meta = item.getItemMeta();
			meta.setDisplayName("§7Loup Garou");
			item.setItemMeta(meta);
			inv.setItem(21,item);
		}

		p.openInventory(inv);
	}
}

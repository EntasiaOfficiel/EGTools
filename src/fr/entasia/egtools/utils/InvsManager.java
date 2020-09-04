package fr.entasia.egtools.utils;

import fr.entasia.apis.menus.MenuClickEvent;
import fr.entasia.apis.menus.MenuCreator;
import fr.entasia.apis.other.ItemBuilder;
import fr.entasia.apis.utils.ItemUtils;
import fr.entasia.egtools.Main;
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

	public static MenuCreator gMenu = new MenuCreator() {

		@Override
		public void onMenuClick(MenuClickEvent e) {
			switch (e.item.getType()) {
				case IRON_AXE:
					e.player.performCommand("ffarush join");
					break;
				case GOLDEN_HORSE_ARMOR:
					e.player.performCommand("minigta join");
					break;
				case IRON_HOE:
					e.player.performCommand("ww join");
					break;
				default:
					e.player.sendMessage("§cCette option n'est pas disponible pour le moment !");
					return;
			}
			e.player.closeInventory();
		}
	};

	public static void gMenuOpen(Player p) {
		Inventory inv = gMenu.createInv(6, "§7Menu §6Enta§cGames");
		ItemStack item = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
		for (int i : new int[]{0, 8, 9, 17}) inv.setItem(i, item);
		item = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
		for (int i : new int[]{19, 25, 29, 30, 32, 33}) inv.setItem(i, item);
		item = new ItemStack(Material.RED_STAINED_GLASS_PANE);
		inv.setItem(40, item);
		item = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
		inv.setItem(49, item);

		ItemBuilder builder = new ItemBuilder(Material.PLAYER_HEAD).name("§dStatistiques !").lore("§9Non disponible pour le moment !");
		ItemUtils.placeSkullAsync(inv, 52, builder.build(), p);


		builder = new ItemBuilder(Material.CHEST_MINECART).name("§bRécompense journalière").lore("§3Clique pour recevoir ta récompense journalière !");
		inv.setItem(47, builder.build());

		builder = new ItemBuilder(Material.GOLD_INGOT).name("§eBoutique").lore("§9Non disponible pour le moment !");
		inv.setItem(46, builder.build());


		builder = new ItemBuilder(Material.IRON_AXE).name("§cFFARush").lore("§cDu FFARush normal.. ou pas ?");
		inv.setItem(13, builder.build());

		builder = new ItemBuilder(Material.GOLDEN_HORSE_ARMOR).name("§7MiniGTA").lore(
			"§6Le cèlebre jeu GTA porté dans Minecraft",
			"Maintenant en mini jeu !",
			"§6Deux équipes. 8 minutes. Des armes.",
			" ",
			"§9Développé par Stargeyt ! Merci à lui",
			"§cDu FFARush normal.. ou pas ?");
		inv.setItem(23, builder.build());

		builder = new ItemBuilder(Material.IRON_HOE).name("§7Loup Garou");
		inv.setItem(21, builder.build());

		p.openInventory(inv);
	}
}

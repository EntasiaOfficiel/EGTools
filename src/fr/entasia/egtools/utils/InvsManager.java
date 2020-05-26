package fr.entasia.egtools.utils;

import fr.entasia.apis.menus.MenuClickEvent;
import fr.entasia.apis.menus.MenuCreator;
import fr.entasia.egtools.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class InvsManager {

	public static MenuCreator cosmMenu = new MenuCreator() {

		@Override
		public void onMenuClick(MenuClickEvent e){
			if(!e.inv.getName().equalsIgnoreCase("§7Menu cosmétiques")) return;
			CosmType type = null;
			switch(e.item.getType()) {
				case MONSTER_EGG:
					type= CosmType.PET;
					cosmUnderMenuOpen(e.player,type);
					break;
				case SKULL_ITEM:
					type=CosmType.DEAD;
					cosmUnderMenuOpen(e.player,type);
					break;
				case DIAMOND_BOOTS:
					type=CosmType.MOVE;
					cosmUnderMenuOpen(e.player,type);
					break;
				case ELYTRA:
					type=CosmType.SUIT;
					cosmUnderMenuOpen(e.player,type);
					break;
				default:
					e.player.sendMessage("§7Ce menu n'existe pas");
			e.player.closeInventory();
			}
		}
	};

	public static void cosmMenuOpen(Player p){
		Inventory inv = cosmMenu.createInv(3,"§7Menu cosmétiques");

		ItemStack itemPet = new ItemStack(Material.MONSTER_EGG);
		ItemMeta petMeta = itemPet.getItemMeta();
		petMeta.setDisplayName("§7Menu des pets");
		itemPet.setItemMeta(petMeta);


		ItemStack itemDead = new ItemStack(Material.SKULL_ITEM, 1, (short)1);
		ItemMeta deadMeta = itemDead.getItemMeta();
		deadMeta.setDisplayName("§7Menu des particules de mort");
		itemDead.setItemMeta(deadMeta);

		ItemStack itemMove = new ItemStack(Material.DIAMOND_BOOTS);
		ItemMeta moveMeta = itemMove.getItemMeta();
		moveMeta.setDisplayName("§7Menu des particules de déplacement");
		itemMove.setItemMeta(moveMeta);

		ItemStack itemSuit = new ItemStack(Material.ELYTRA);
		ItemMeta suitMeta = itemSuit.getItemMeta();
		suitMeta.setDisplayName("§7Menu des tenues");
		itemSuit.setItemMeta(suitMeta);

		inv.setItem(1, itemPet);
		inv.setItem(7, itemDead);
		inv.setItem(19, itemMove);
		inv.setItem(25, itemSuit);

		p.openInventory(inv);

	}

	public static MenuCreator cosmUnderMenu = new MenuCreator() {

		@Override
		public void onMenuClick(MenuClickEvent e){
			if(!e.inv.getName().startsWith("§7Menu des ")) return;
			for(Cosmetique c : Cosmetique.values()){
				if(c.itemStack==e.item){
					if(Utils.haveCosm(c.id,e.player.getUniqueId())){
						c.enable(e.player);
						e.player.sendMessage("§7Vous avez activé la particule "+c.nom);
						Utils.actualCosm.put(e.player.getUniqueId(),c);
					} else{
						openCosmParticule(e.player,c);
					}
					return;
				}
			}
			if(e.item.getItemMeta().getDisplayName().equalsIgnoreCase("§cRetour")){
				cosmMenuOpen(e.player);
			}else if(e.item.getItemMeta().getDisplayName().equalsIgnoreCase("§cRetour")){
				//Enlever la particule
				Utils.actualCosm.put(e.player.getUniqueId(), null);






			}
			e.player.closeInventory();
		}
	};

	public static void cosmUnderMenuOpen(Player p, CosmType type){
		int cosm = 0;
		for(Cosmetique c : Cosmetique.values()){
			if(c.type == type) cosm++;
		}
		int slot = cosm*2;
		while( slot%9!=0){
			slot++;
		}
		String name= "";
		switch (type){
			case DEAD:
				name="§7Menu des particules de mort";
				break;
			case MOVE:
				name="§7Menu des particules de déplacement";
				break;
			case PET:
				name = "§7Menu des pets";
				break;
			case SUIT:
				name="§7Menu des tenues";
				break;
			default:
				name="§cERROR";
				break;
		}

		Inventory inv = cosmUnderMenu.createInv(slot/9, name);
		int nextSlot = 1;
		for(Cosmetique c : Cosmetique.values()){
			if(c.type == type){
				ItemStack item = c.itemStack;
				inv.setItem(nextSlot, item);
				nextSlot= nextSlot+2;
			}
		}
		ItemStack item = new ItemStack(Material.REDSTONE_BLOCK);
		ItemMeta meta= item.getItemMeta();
		meta.setDisplayName("§cEnlever les particules");
		item.setItemMeta(meta);
		inv.setItem(slot-1,item);
		p.openInventory(inv);
	}

	public static MenuCreator cosmParticule = new MenuCreator() {

		@Override
		public void onMenuClick(MenuClickEvent e){
			if(!e.inv.getName().equalsIgnoreCase("§7Achat d'un cosmétique")) return;
			if(e.item.getItemMeta().getDisplayName().equalsIgnoreCase("§cAnnuler")) e.player.closeInventory();
			UUID uuid = e.player.getUniqueId();
			Cosmetique c = (Cosmetique)e.data;
			if(MoneyUtils.getMoney(uuid)>= c.price){
				MoneyUtils.removeMoney(uuid, c.price);

			}
		}
	};

	public static void openCosmParticule(Player p, Cosmetique c){
		Inventory inv = cosmParticule.createInv(2,"§7Achat d'un cosmétique", c);

		ItemStack cosmetique=c.itemStack;
		inv.setItem(4,cosmetique);
		ItemStack achat= new ItemStack(Material.STAINED_GLASS_PANE, (byte) 5);
		ItemMeta achatMeta= achat.getItemMeta();
		achatMeta.setDisplayName("§2Acheter");
		achatMeta.setLore(Collections.singletonList("§2Cout : "+c.price +" coins"));
		achat.setItemMeta(achatMeta);


		ItemStack refuser= new ItemStack(Material.STAINED_GLASS_PANE, (byte) 14);
		ItemMeta refuserMeta= refuser.getItemMeta();
		refuserMeta.setDisplayName("§cAnnuler");
		refuserMeta.setLore(Collections.singletonList("§cAnnuler l'achat"));
		refuser.setItemMeta(refuserMeta);

		inv.setItem(11,achat);
		inv.setItem(15,refuser);

		p.openInventory(inv);
	}




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
		list.add("§6Deux équipes. 5 minutes. Des armes.");
		list.add(" ");
		list.add("§9Développé par Stargeyt ! Merci à lui");
		meta.setLore(list);
		item.setItemMeta(meta);
		inv.setItem(23, item);

		p.openInventory(inv);
	}
}

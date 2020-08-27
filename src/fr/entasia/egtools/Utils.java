package fr.entasia.egtools;

import fr.entasia.egtools.utils.MoneyUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Utils {

	public static World world;
	public static Location spawn;

	public static ArrayList<String> buildToggle = new ArrayList<>();

	public static boolean canBuild(Player p){
		return buildToggle.contains(p.getName())&&p.getGameMode()== GameMode.CREATIVE;
	}

	public static void tpSpawn(Player p) {
		reset(p);
		p.teleport(spawn);
		ItemStack item = new ItemStack(Material.CHEST);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§6Enta§cGames");
		item.setItemMeta(meta);
		p.getInventory().setItem(4, item);
		item.setType(Material.COMMAND);
		meta.setDisplayName("§bCosmétiques");
		item.setItemMeta(meta);
		p.getInventory().setItem(7, item);

		p.setItemOnCursor(null);
	}

	public static void reset(Player p) {
		p.getInventory().clear();


		for(PotionEffect pe : p.getActivePotionEffects()){
			p.removePotionEffect(pe.getType());
		}
		p.setGlowing(false);
		p.setGliding(false);
		p.setMaxHealth(20);
		p.setHealth(20);
		p.setLevel(0);
		p.setFoodLevel(20);
	}

	public static boolean saveAllMoney() {
		if(Main.dev)return true;
		try{
			PreparedStatement ps;
			Main.sqlConnection.checkConnect();
			for(Map.Entry<UUID, Integer> e : new HashMap<>(MoneyUtils.moneyCache).entrySet()){
				ps = Main.sqlConnection.connection.prepareStatement("UPDATE entagames set money=? where uuid=?");
				ps.setInt(1, e.getValue());
				ps.setString(2, e.getKey().toString());
				ps.execute();
				if(Bukkit.getPlayer(e.getKey())==null)MoneyUtils.moneyCache.remove(e.getKey());
			}
			return true;
		}catch(SQLException e){
			e.printStackTrace();
			Main.sqlConnection.broadcastError();
		}
		return false;
	}

}

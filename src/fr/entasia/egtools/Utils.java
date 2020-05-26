package fr.entasia.egtools;

import fr.entasia.egtools.utils.Cosmetique;
import fr.entasia.egtools.utils.MoneyUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Utils {

	public static World world;
	public static Location spawn;
	public static HashMap<UUID, Cosmetique> actualCosm = new HashMap<>();

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
		p.getInventory().setItem(8, item);
	}

	public static void reset(Player p) {
		p.getInventory().clear();

//		Bukkit.broadcastMessage("DEBUG 2");

		for(PotionEffect pe : p.getActivePotionEffects()){
			Bukkit.broadcastMessage("Removing "+pe.getType());
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
		try{
			PreparedStatement ps;
			Main.sqlConnection.checkConnect();
			for(Map.Entry<UUID, Integer> e : new HashMap<>(MoneyUtils.MoneyCache).entrySet()){
				ps = Main.sqlConnection.connection.prepareStatement("UPDATE entagames set money=? where uuid=?");
				ps.setInt(1, e.getValue());
				ps.setString(2, e.getKey().toString());
				ps.execute();
				if(Bukkit.getPlayer(e.getKey())==null)MoneyUtils.MoneyCache.remove(e.getKey());
			}
			return true;
		}catch(SQLException e){
			e.printStackTrace();
			Main.sqlConnection.broadcastError();
		}
		return false;
	}


	public static boolean haveCosm(int id, UUID uuid){
		try {
			Main.sqlConnection.checkConnect();
			PreparedStatement ps;

			ps= Main.sqlConnection.connection.prepareStatement("SELECT cosm-id FROM eg-cosmetique where uuid=?"); // A STOCKER EN CACHE !!!
			ps.setString(1, uuid.toString());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				if(rs.getInt(1)==id){
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Main.sqlConnection.broadcastError();
		}
		return false;
	}

}

package fr.entasia.egtools;

import fr.entasia.apis.utils.PlayerUtils;
import fr.entasia.egtools.utils.MoneyUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EGUtils {

	public static World world;
	public static Location spawn;

	public static ArrayList<String> buildToggle = new ArrayList<>();

	public static boolean canBuild(Player p){
		return buildToggle.contains(p.getName())&&p.getGameMode()== GameMode.CREATIVE;
	}

	public static void tpSpawn(Player p) {
		PlayerUtils.hardReset(p);
		p.teleport(spawn);
		ItemStack item = new ItemStack(Material.CHEST);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§6Enta§cGames");
		item.setItemMeta(meta);
		p.getInventory().setItem(4, item);
		item.setType(Material.COMMAND_BLOCK);
		meta.setDisplayName("§bCosmétiques");
		item.setItemMeta(meta);
		p.getInventory().setItem(7, item);

		p.setItemOnCursor(null);
	}

	public static boolean saveAllMoney() {
		if(Main.dev)return true;
		try{
			PreparedStatement ps;
			Main.sql.checkConnect();
			for(Map.Entry<UUID, Integer> e : new HashMap<>(MoneyUtils.moneyCache).entrySet()){
				ps = Main.sql.connection.prepareStatement("UPDATE entagames set money=? where uuid=?");
				ps.setInt(1, e.getValue());
				ps.setString(2, e.getKey().toString());
				ps.execute();
				if(Bukkit.getPlayer(e.getKey())==null)MoneyUtils.moneyCache.remove(e.getKey());
			}
			return true;
		}catch(SQLException e){
			e.printStackTrace();
			Main.sql.broadcastError();
		}
		return false;
	}

}

package fr.entasia.egtools.utils;

import fr.entasia.egtools.Main;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.UUID;

public class MoneyUtils {

	public static HashMap<UUID, Integer> moneyCache = new HashMap<>();

	public static int getMoney(UUID uuid) {
		int a = moneyCache.getOrDefault(uuid, -1);
		if(a==-1){
			try{
				Main.sqlConnection.checkConnect();
				ResultSet rs = Main.sqlConnection.fastSelectUnsafe("SELECT money FROM entagames WHERE uuid = ?", uuid);
				if(rs.next()){
					a = rs.getInt(1);
					moneyCache.put(uuid, a);
					return a;
				}
			}catch(Exception e){
				e.printStackTrace();
				Main.sqlConnection.broadcastError();
			}
			return -1;
		}else return a;
	}

	public static void setMoney(UUID uuid, int coins){
		moneyCache.put(uuid, coins);
//		SQLConnection.checkConnect();
//		try{
//			SQLConnection.connection.prepareStatement("update stats set eg_money=" + coins + " WHERE uuid = '"+ p.getUniqueId() +"'").execute();
//		}catch(SQLException e){
//			e.printStackTrace();
//		}
	}

	public static void addMoney(UUID uuid, int coins){
		int a = moneyCache.getOrDefault(uuid, -2);
		if(a==-2)a = getMoney(uuid);
		a+=coins;
		moneyCache.put(uuid, a);
//		SQLConnection.checkConnect();
//		try{
//			SQLConnection.connection.prepareStatement("update stats set eg_money=eg_money+" + coins + " WHERE uuid = '"+ p.getUniqueId() +"'").execute();
//		}catch(SQLException e){
//			e.printStackTrace();
//		}
	}

	public static void removeMoney(UUID uuid, int coins){
		addMoney(uuid, -coins);
	}

//	public static String getTopMoney(int limit){
//		SQLConnection.checkConnect();
//		PreparedStatement ps;
//		StringBuilder br = new StringBuilder();
//		try {
//			ResultSet rs = SQLConnection.connection.prepareStatement("select name from  order by pvpgames_money DESC limit "+limit).executeQuery();
//			while(rs.next()){
//				br.append(rs.getString("name")+";"+getMoney(Bukkit.getPlayer(rs.getString("name")))+";");
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		String fini = br.toString().substring(0, br.length()-1);
//		return fini;
//	}

}

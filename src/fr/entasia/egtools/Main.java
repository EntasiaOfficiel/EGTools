package fr.entasia.egtools;

import fr.entasia.apis.sql.SQLConnection;
import fr.entasia.egtools.commands.*;
import fr.entasia.egtools.listeners.OtherListeners;
import fr.entasia.egtools.listeners.ProtectListeners;
import fr.entasia.egtools.utils.Task5m;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

	public static Main main;
	public static SQLConnection sqlConnection;
	public static boolean dev;




	public static void loadConfig() {
		Utils.world = Bukkit.getWorld(main.getConfig().getString("world"));
		String[] a = main.getConfig().getString("spawn").split(";");
		Utils.spawn = new Location(Utils.world, Double.parseDouble(a[0])+0.5,Double.parseDouble(a[1])+0.5,Double.parseDouble(a[2])+0.5);
	}
	@Override
	public void onEnable() {
		try{
			main = this;
			saveDefaultConfig();
			loadConfig();

			dev = getConfig().getBoolean("dev", false);
			if(getConfig().getString("sqluser")!=null)sqlConnection = new SQLConnection(getConfig().getString("sqluser"), "playerdata");

			getCommand("egtools").setExecutor(new EGToolsCmd());
			getCommand("spawn").setExecutor(new SpawnCmd());
			getCommand("money").setExecutor(new MoneyCmd());
			getCommand("eco").setExecutor(new EcoCmd());
			getCommand("buildtoggle").setExecutor(new BuildToggleCmd());

			getServer().getPluginManager().registerEvents(new OtherListeners(), this);
			getServer().getPluginManager().registerEvents(new ProtectListeners(), this);

			new Task5m().runTaskTimerAsynchronously(this, 1000, 6000); // 5 minutes = 300 secondes = 6000 ticks

			getLogger().info("Plugin activé !");
		}catch(Throwable e){
			e.printStackTrace();
			getLogger().info("LE SERVEUR VA S'ARRETER");
			Bukkit.getServer().shutdown();
		}
	}
}

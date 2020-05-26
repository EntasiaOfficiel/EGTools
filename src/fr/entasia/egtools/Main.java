package fr.entasia.egtools;

import fr.entasia.apis.sql.SQLConnection;
import fr.entasia.egtools.commands.EGTools;
import fr.entasia.egtools.commands.Eco;
import fr.entasia.egtools.commands.Spawn;
import fr.entasia.egtools.listeners.OtherListeners;
import fr.entasia.egtools.listeners.ProtectListeners;
import fr.entasia.egtools.utils.Task5m;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

	public static Main main;
	public static SQLConnection sqlConnection;
	public static boolean enableDev;




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

			enableDev = getConfig().getBoolean("dev", false);
			if(getConfig().getString("sqluser")!=null)sqlConnection = new SQLConnection(getConfig().getString("sqluser"), "playerdata");

			getCommand("egtools").setExecutor(new EGTools());
			getCommand("spawn").setExecutor(new Spawn());
			getCommand("eco").setExecutor(new Eco());

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

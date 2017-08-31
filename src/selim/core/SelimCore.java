package selim.core;

import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;

import selim.core.commands.CommandPluginVersion;
import selim.core.commands.CommandPluginVersion.TabCompleterPluginVersion;
import selim.core.commands.CommandSetupScoreboard;
import selim.core.commands.CommandSetupScoreboard.TabCompleterSetupScoreboard;
import selim.core.commands.CommandViewRecipe;
import selim.core.commands.CommandViewRecipe.TabCompleterViewRecipe;
import selim.core.events.GameTickEvent;
import selim.core.events.PluginsLoadedEvent;
import selim.core.leaderboards.ScoreboardManager;
import selim.core.util.RecipeUtils;
import selim.core.util.SemanticVersion;
import selim.metrics.Metrics;
import selim.metrics.Metrics.Graph;
import selim.versioncontrol.versionhandlers.IVersionHandler;

public class SelimCore extends SelimCorePlugin /* implements IEnergyPlugin */ {

	private static SemanticVersion VERSION;

	private static IVersionHandler vh;

	protected static PluginManager MANAGER;
	protected static SelimCore INSTANCE;
	protected static Logger LOGGER;
	// protected static MachineRegistry MACHINE_REGISTRY = new
	// MachineRegistry();

	public static IVersionHandler getVersionHandler() {
		return vh;
	}

	@Override
	public SemanticVersion getMinimumCoreSemVer() {
		return VERSION;
	}

	public SemanticVersion getVersion() {
		return VERSION;
	}

	@Override
	public void onEnable() {
		VERSION = SemanticVersion.valueOf(this.getDescription().getVersion());

		String packageName = this.getServer().getClass().getPackage().getName();
		String version = packageName.substring(packageName.lastIndexOf('.') + 1);
		try {
			final Class<?> clazz = Class
					.forName("selim.versioncontrol.versionhandlers." + version + ".VersionHandler");
			if (IVersionHandler.class.isAssignableFrom(clazz))
				vh = (IVersionHandler) clazz.getConstructor().newInstance();
		} catch (final Exception e) {
			e.printStackTrace();
			this.getLogger().severe("Could not find support for this CraftBukkit version.");
			this.getLogger().info("Bug the author on Twitter at @Selim_042.");
			this.getLogger().info("I know, Twitter. Really professional.");
			this.setEnabled(false);
			return;
		}
		this.getLogger().info("Loading support for " + version + ".");

		INSTANCE = this;
		LOGGER = this.getLogger();
		MANAGER = this.getServer().getPluginManager();
		MANAGER.registerEvents(new EventListener(), this);
		MANAGER.registerEvents(new ScoreboardManager(), this);
		Config.init(this.getConfig());
		this.getCommand("viewrecipe").setExecutor(new CommandViewRecipe());
		this.getCommand("viewrecipe").setTabCompleter(new TabCompleterViewRecipe());
		this.getCommand("pluginversion").setExecutor(new CommandPluginVersion());
		this.getCommand("pluginversion").setTabCompleter(new TabCompleterPluginVersion());
		this.getCommand("setupscoreboard").setExecutor(new CommandSetupScoreboard());
		this.getCommand("setupscoreboard").setTabCompleter(new TabCompleterSetupScoreboard());
		RecipeUtils.initRecipes();

		// MANAGER.registerEvents(new MachineEventListener(), this);
		registerGameTickEvent();

		Bukkit.getScheduler().runTask(this, new Runnable() {

			@Override
			public void run() {
				SelimCore.MANAGER.callEvent(new PluginsLoadedEvent());
			}
		});

		try {
			Metrics metrics = new Metrics(this);

			Graph pluginVersion = metrics.createGraph("Plugin Version");

			pluginVersion.addPlotter(new Metrics.Plotter(VERSION.toString()) {

				@Override
				public int getValue() {
					return 1;
				}

			});

			metrics.start();
		} catch (IOException e) {
			// Failed to submit the stats :-(
			e.printStackTrace();
		}
	}

	private GameTickEvent gametickevent = new GameTickEvent();

	private void registerGameTickEvent() {
		Bukkit.getScheduler().runTaskTimer(this, new Runnable() {

			@Override
			public void run() {
				Bukkit.getPluginManager().callEvent(gametickevent);
			}
		}, 1, 1);
	}

	@Override
	public void onDisable() {
		// try {
		// MachineHelper.saveMachines();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		INSTANCE = null;
		LOGGER = null;
		MANAGER = null;
		HandlerList.unregisterAll(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equals(INSTANCE.getName().toLowerCase() + "version"))
			sender.sendMessage("Current " + INSTANCE.getName() + " version: v"
					+ this.getFile().getName().replace(INSTANCE.getName() + "-", "").replace(".jar", "")
					+ ".");
		return true;
	}

	public static Logger getCoreLogger() {
		return LOGGER;
	}

	public static void debug(String str) {
		for (Player p : SelimCore.INSTANCE.getServer().getOnlinePlayers())
			if (p.isOp())
				p.sendMessage(str);
	}

	public static void debug(boolean bool) {
		debug(bool + "");
	}

	public static void debug(int num) {
		debug(num + "");
	}

	public static void debug(float num) {
		debug(num + "");
	}

	public static void debug(double num) {
		debug(num + "");
	}

	public static void debug(short num) {
		debug(num + "");
	}

	public static void debug(Object obj) {
		if (obj == null)
			return;
		debug(obj.toString());
	}

	public static void debug(Enum<?> obj) {
		if (obj == null)
			return;
		debug(obj.name());
	}

	@Override
	public int getSpigotResourceId() {
		return 42405;
	}

	// @Override
	// public void registerMachines(MachineRegistry registry) {
	// try {
	// registry.registerMachine(MachineFurnace.class);
	// } catch (DuplicateIDException e) {
	// e.printStackTrace();
	// }
	// }
}

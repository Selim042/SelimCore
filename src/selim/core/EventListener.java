package selim.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import com.comphenix.protocol.wrappers.nbt.NbtCompound;
import com.comphenix.protocol.wrappers.nbt.NbtFactory;

import selim.core.events.PluginsLoadedEvent;
import selim.core.games.Game;
import selim.core.games.GameSignManager;
import selim.core.util.RecipeUtils;
import selim.core.util.SemanticVersion;
import selim.core.util.VersionChecker;

public class EventListener implements Listener {

	private static final Random rand = new Random();
	private static final List<SelimCorePlugin> enabledPlugins = new ArrayList<SelimCorePlugin>();
	private static final List<SelimCorePlugin> disabledPlugins = new ArrayList<SelimCorePlugin>();

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPluginsLoaded(PluginsLoadedEvent event) {
		SemanticVersion coreVersion = SelimCore.INSTANCE.getVersion();
		for (Plugin p : SelimCore.MANAGER.getPlugins()) {
			if (!(p instanceof SelimCore) && p.isEnabled()) {
				if (p instanceof SelimCorePlugin) {
					SelimCorePlugin sp = (SelimCorePlugin) p;
					SemanticVersion pluginReqVersion;
					if (sp.getMinimumCoreVersion() == -1)
						pluginReqVersion = sp.getMinimumCoreSemVer();
					else
						pluginReqVersion = new SemanticVersion(sp.getMinimumCoreVersion());
					SelimCore.LOGGER.log(Level.INFO,
							"Checking " + p.getName() + " required version (" + pluginReqVersion + ")");
					if (pluginReqVersion.compareTo(coreVersion) != -1) {
						SelimCore.LOGGER.log(Level.SEVERE,
								"Disabled " + sp.getName() + ", requires at least SelimCore version "
										+ pluginReqVersion + ", " + coreVersion + " is supplied.");
						SelimCore.MANAGER.disablePlugin(sp);
						disabledPlugins.add(sp);
					} else
						enabledPlugins.add(sp);
				}
				// if (p instanceof IEnergyPlugin) {
				// SelimCore.LOGGER.log(Level.INFO, "Loading energy plugin: " +
				// p.getName() + ".");
				// try {
				// ((IEnergyPlugin)
				// p).registerMachines(SelimCore.MACHINE_REGISTRY);
				// } catch (Exception x) {
				// SelimCore.LOGGER.log(Level.SEVERE,
				// "Caught exception while loading energy plugin: " +
				// p.getName(), x);
				// }
				// }
			}
		}

		GameSignManager.loadGameSigns();
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if (player.isOp()) {
			SemanticVersion coreVersion = SelimCore.INSTANCE.getVersion();
			for (SelimCorePlugin sp : disabledPlugins) {
				SemanticVersion pluginReqVersion;
				if (sp.getMinimumCoreVersion() == -1)
					pluginReqVersion = sp.getMinimumCoreSemVer();
				else
					pluginReqVersion = new SemanticVersion(sp.getMinimumCoreVersion());
				player.sendMessage("[" + SelimCore.INSTANCE.getName() + "] " + sp.getName()
						+ " has been disabled as it requires SelimCore version " + pluginReqVersion
						+ " or higher.");
			}
			if (disabledPlugins.size() != 0)
				player.sendMessage("[" + SelimCore.INSTANCE.getName() + "] " + "Version " + coreVersion
						+ " is installed.");
			for (SelimCorePlugin sp : enabledPlugins)
				if (sp.getSpigotResourceId() != -1)
					VersionChecker.sendUpdateMessage(player, sp, sp.getSpigotResourceId());
		}
	}

	@EventHandler
	public void onInventoryEvent(InventoryClickEvent event) {
		if (event.getAction() != InventoryAction.CLONE_STACK
				&& event.getInventory().getName().equals(RecipeUtils.RECIPE_INV_NAME)
				&& (event.getSlotType() == SlotType.CRAFTING || event.getSlotType() == SlotType.RESULT))
			event.setCancelled(true);
	}

	@EventHandler
	public void onPrepareRecipe(CraftItemEvent event) {
		ItemStack result = event.getRecipe().getResult();
		NbtCompound nbt = (NbtCompound) NbtFactory.fromItemTag(result);
		if (nbt.containsKey("dif")) {
			try {
				nbt.getList("dif");
				nbt.put("dif", new int[] { rand.nextInt(), rand.nextInt(), rand.nextInt() });
				NbtFactory.setItemTag(result, nbt);
			} catch (IllegalArgumentException e) {
				return;
			}
		}
	}

	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event) {
		for (Game game : Game.getAllGames())
			game.removePlayer(event.getPlayer());
	}

}

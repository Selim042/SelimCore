package selim.core.machines;

import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.comphenix.protocol.wrappers.nbt.NbtCompound;
import com.comphenix.protocol.wrappers.nbt.NbtFactory;

import selim.core.events.GameTickEvent;
import selim.core.events.PluginsLoadedEvent;
import selim.core.machines.test.MachineFurnace;

public class MachineEventListener implements Listener {

	@EventHandler
	public void onServerLoaded(PluginsLoadedEvent event) {
		try {
			MachineHelper.loadMachines();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@EventHandler
	public void onGameTick(GameTickEvent event) {
		for (Machine m : MachineHelper.getMachineInstances()) {
			if (m instanceof ITickable)
				((ITickable) m).onUpdate();
			if (m instanceof IMachineGui && m instanceof IEnergyStorage) {
				IMachineGui mGui = (IMachineGui) m;
				IEnergyStorage mEn = (IEnergyStorage) m;
				Inventory inv = mGui.getGui();
				ItemStack energyBar = inv.getItem(inv.getSize() - 9);
				if (energyBar != null && energyBar.getType() == Material.DIAMOND_HOE) {
					NbtCompound nbt = (NbtCompound) NbtFactory.fromItemTag(energyBar);
					if (nbt.containsKey("energyBar")) {
						energyBar.setDurability(mEn.getEnergy() == 0 ? 0
								: (short) ((mEn.getMaxEnergy() / mEn.getEnergy()) * 1562));
						ItemMeta meta = energyBar.getItemMeta();
						meta.setDisplayName("" + energyBar.getDurability());
						energyBar.setItemMeta(meta);
						// inventory.setItem(inventory.getSize() - 9,
						// energyBar);
					}
				}
			}
		}
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (!event.getPlayer().isSneaking() && ((event.getAction().equals(Action.LEFT_CLICK_BLOCK)
				|| event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
				&& MachineHelper.getMachine(event.getClickedBlock().getLocation()) != null)) {
			Location location = event.getClickedBlock().getLocation();
			MachineHelper.getMachine(location).onClicked(event.getPlayer(), event.getAction());
			event.setCancelled(true);
		}
//		if (!event.hasItem()) {
//			Machine machine = MachineHelper.getMachine(event.getClickedBlock().getLocation());
//			if (machine == null) {
//				machine = new MachineFurnace();
//				MachineHelper.placeMachine(event.getClickedBlock().getLocation(), machine);
//				event.getClickedBlock().setType(machine.getBaseMaterial());
//			}
//			if (machine instanceof EnergyMachine)
//				((EnergyMachine) machine).addEnergy(100, false);
//		}
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		ItemStack clicked = event.getCurrentItem();
		Inventory inventory = event.getInventory();
		// if (inventory.getName().equals(myInventory.getName())) {
//		if (clicked.getType() == Material.DIRT) {
//			event.setCancelled(true);
//			player.closeInventory();
//			player.getInventory().addItem(new ItemStack(Material.DIRT, 1));
//		}
		// }
	}

	@EventHandler
	public void onInventoryInteract(InventoryClickEvent event) {
		if (MachineHelper.isStackFixed(event.getCurrentItem()))
			event.setCancelled(true);
	}

}

package selim.machines;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.logging.Level;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.comphenix.protocol.utility.MinecraftReflection;
import com.comphenix.protocol.wrappers.nbt.NbtCompound;
import com.comphenix.protocol.wrappers.nbt.NbtFactory;

import selim.core.Helper;
import selim.core.SelimCore;

public class MachineHelper {

	protected static final ItemStack ENERGY_BAR;
	private static final HashMap<Location, Machine> MACHINE_INSTANCES = new HashMap<Location, Machine>();

	static {
		ENERGY_BAR = SelimCore.getVersionHandler()
				.getCraftItemStack(new ItemStack(Material.DIAMOND_HOE));
		ItemMeta meta = ENERGY_BAR.getItemMeta();
		meta.setUnbreakable(true);
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
		ENERGY_BAR.setItemMeta(meta);
		NbtCompound barNbt = (NbtCompound) NbtFactory.fromItemTag(ENERGY_BAR);
		barNbt.put("energyBar", (byte) 1);
		barNbt.put("fixedGuiPart", (byte) 1);
		NbtFactory.setItemTag(ENERGY_BAR, barNbt);
	}

	public static Machine getMachine(Location location) {
		if (!MACHINE_INSTANCES.containsKey(location))
			return null;
		return MACHINE_INSTANCES.get(location);
	}

	public static Collection<Machine> getMachineInstances() {
		return MACHINE_INSTANCES.values();
	}

	public static void placeMachine(Location loc, Machine machine) {
		MACHINE_INSTANCES.put(loc, machine);
	}

	public static void addEnergyBar(EnergyMachine machine, Inventory inventory) {
		ItemStack energyBar = ENERGY_BAR.clone();
		energyBar.setDurability(machine.getEnergy() == 0 ? 0
				: (short) ((machine.getMaxEnergy() / machine.getEnergy()) * 1562));
		ItemMeta meta = energyBar.getItemMeta();
		meta.setDisplayName("" + energyBar.getDurability());
		energyBar.setItemMeta(meta);
		inventory.setItem(inventory.getSize() - 9, energyBar);
	}

	public static boolean isStackFixed(ItemStack stack) {
		if (!MinecraftReflection.isCraftItemStack(stack))
			return false;
		NbtCompound nbt = (NbtCompound) NbtFactory.fromItemTag(stack);
		return nbt.containsKey("fixedGuiPart") && nbt.getByte("fixedGuiPart") == 1;
	}

	public static void saveMachines() throws IOException {
		File dataFolder = new File(
				SelimCore.getPlugin(SelimCore.class).getDataFolder() + File.separator + "machineData");
		dataFolder.mkdirs();
		dataFolder.mkdir();
		File dataFile = new File(dataFolder + File.separator + "data.dat");
//		if (!dataFile.exists())
//			dataFile.createNewFile();
		NbtCompound data = NbtFactory.fromFile(dataFile.getAbsolutePath());
		for (Entry<Location, Machine> entry : MACHINE_INSTANCES.entrySet()) {
			NbtCompound nbt = NbtFactory.ofCompound("");
			nbt.put("data", entry.getValue().writeToNbt(NbtFactory.ofCompound("")));
			nbt.put("id", entry.getValue().getID());
			data.put(Helper.locationToString(entry.getKey()), nbt);
		}
		NbtFactory.toFile(data, dataFile.getAbsolutePath());
	}

	public static void loadMachines() throws IOException {
		File dataFolder = new File(
				SelimCore.getPlugin(SelimCore.class).getDataFolder() + File.separator + "machineData");
		dataFolder.mkdirs();
		dataFolder.mkdir();
		File dataFile = new File(dataFolder + File.separator + "data.dat");
		if (!dataFile.exists())
			return;
		NbtCompound data = NbtFactory.fromFile(dataFile.getAbsolutePath());
		for (String key : data.getKeys()) {
			NbtCompound nbt = data.getCompound(key);
			Class<? extends Machine> mClass = MachineRegistry.getMachineByID(nbt.getString("id"));
			try {
				Machine machine = mClass.newInstance();
				machine.readFromNbt(nbt.getCompound("data"));
				MACHINE_INSTANCES.put(Helper.locationFromString(key), machine);
			} catch (InstantiationException | IllegalAccessException e) {
				SelimCore.getCoreLogger().log(Level.SEVERE,
						"Machine classes must contain a public default constructor.", e);
			}
		}
	}

}

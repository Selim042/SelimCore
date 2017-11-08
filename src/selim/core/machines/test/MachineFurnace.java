package selim.core.machines.test;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.meta.ItemMeta;

import com.comphenix.protocol.wrappers.nbt.NbtCompound;

import selim.core.Helper;
import selim.core.machines.EnergyMachine;
import selim.core.machines.IEnergyStorage;
import selim.core.machines.IMachineGui;
import selim.core.machines.ITickable;
import selim.core.machines.Machine;
import selim.core.machines.MachineHelper;
import selim.core.util.NbtUtils;

public class MachineFurnace extends EnergyMachine implements IMachineGui, ITickable {

	private final static int ENERGY_TICK = 20;

	private ItemStack inputStack;
	private ItemStack outputStack;
	private int progress;
	private String guiName = "Electric Furnace";
	private Inventory inv = Bukkit.createInventory(null, 27, this.guiName);

	public MachineFurnace() {
		super("selim.electricFurnace");
		ItemStack testStack = new ItemStack(Material.BARRIER);
		ItemMeta meta = testStack.getItemMeta();
		testStack.setItemMeta(meta);
		inv.setItem(0, testStack);
		inv.setItem(4, this.inputStack);
		MachineHelper.addEnergyBar(this, inv);
		this.setBaseMaterial(Material.RED_SANDSTONE);
		this.setMaxEnergy(10000);
	}

	@Override
	public NbtCompound writeToNbt(NbtCompound nbt) {
		super.writeToNbt(nbt);
		nbt.put("inputStack", NbtUtils.nbtFromStack(inputStack));
		nbt.put("outputStack", NbtUtils.nbtFromStack(outputStack));
		nbt.put("progress", progress);
		return nbt;
	}

	@Override
	public void readFromNbt(NbtCompound nbt) {
		if (nbt.containsKey("inputStack"))
			this.inputStack = NbtUtils.stackFromNbt(nbt.getCompound("inputStack"));
		if (nbt.containsKey("outputStack"))
			this.outputStack = NbtUtils.stackFromNbt(nbt.getCompound("outputStack"));
		if (nbt.containsKey("progress"))
			this.progress = nbt.getInteger("progress");
	}

	@Override
	public void onClicked(Player player, Action clickType) {
		if (clickType.equals(Action.RIGHT_CLICK_BLOCK)) {
			player.sendMessage("energy: " + this.getEnergy());
			player.openInventory(inv);
		}
	}

	@Override
	public String getGuiName() {
		return this.guiName;
	}

	@Override
	public Inventory getGui() {
		return inv;
	}

	@Override
	public void onUpdate() {
		for (BlockFace face : BlockFace.values()) {
			Machine m = MachineHelper.getMachine(Helper.offsetLocation(this.location, face));
			if (m instanceof IEnergyStorage) {
				IEnergyStorage e = (IEnergyStorage) m;
				if (e.canRemoveEnergy())
					this.addEnergy(e.removeEnergy(this.maxAdd(), false), false);
			}
		}
		if (inputStack == null)
			return;
		for (Recipe r : Bukkit.getRecipesFor(inputStack)) {
			int removed = removeEnergy(ENERGY_TICK, true);
			if (r instanceof FurnaceRecipe && removed == ENERGY_TICK) {
				removeEnergy(ENERGY_TICK, false);
				if (progress >= 20)
					outputStack = r.getResult();
				progress++;
			}
		}
	}

	@Override
	public int maxRemove() {
		return 0;
	}

	@Override
	public int maxAdd() {
		return 80;
	}

}

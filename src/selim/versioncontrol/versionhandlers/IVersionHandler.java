package selim.versioncontrol.versionhandlers;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

public interface IVersionHandler {

	public String getMCVersion();

	public ItemStack getCraftItemStack(ItemStack stack);

	public boolean canBlockBePlaced(Block block, Location location, BlockFace face);

	public boolean doesItemExistForBlock(Block block);

	public boolean doesItemHaveSubtypes(ItemStack stack);

	public boolean doesItemHaveSubtypes(Block block);

	public boolean areBlockAndItemEqual(Block block, ItemStack stack);

	public String getItemName(ItemStack stack);

	public String getItemName(Block block);

	public String getItemName(MaterialData data);

}

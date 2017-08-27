package selim.versioncontrol.versionhandlers.v1_10_R2_unsupported;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.craftbukkit.v1_10_R1.block.CraftBlockState;
import org.bukkit.craftbukkit.v1_10_R1.inventory.CraftItemStack;
//import org.bukkit.craftbukkit.v1_10_R1.block.CraftBlockState;
//import org.bukkit.craftbukkit.v1_10_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import net.minecraft.server.v1_10_R1.BlockPosition;
import net.minecraft.server.v1_10_R1.EntityPlayer;
import net.minecraft.server.v1_10_R1.EnumDirection;
import net.minecraft.server.v1_10_R1.Item;
import net.minecraft.server.v1_10_R1.MinecraftServer;
import selim.versioncontrol.versionhandlers.IVersionHandler;

@SuppressWarnings("unused")
public class VersionHandler implements IVersionHandler {

	@Override
	public String getMCVersion() {
		return "1.10.2";
	}

	@Override
	public ItemStack getCraftItemStack(ItemStack stack) {
		return CraftItemStack.asCraftCopy(stack);
	}

	@Override
	public boolean canBlockBePlaced(Block block, Location location, BlockFace face) {
		return this.getVanillaBlock(block.getType()).canPlace(this.getVanillaWorld(location.getWorld()),
				this.getVanillaLocation(location), this.getVanillaFace(face));
	}

	@Override
	public boolean doesItemExistForBlock(Block block) {
		return Item.getItemOf(this.getVanillaBlock(block.getType())) == null;
	}

	@Override
	public boolean doesItemHaveSubtypes(ItemStack stack) {
		// TODO: Find the subtypes method
		// return CraftItemStack.asNMSCopy(stack).getItem().q();
		return false;
	}

	@Override
	public boolean doesItemHaveSubtypes(Block block) {
		return doesItemHaveSubtypes(block.getState().getData().toItemStack());
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean areBlockAndItemEqual(Block block, ItemStack stack) {
		return this.getVanillaItemStack(stack).getItem() == Item
				.getItemOf(this.getVanillaBlock(block.getType()))
				&& (doesItemHaveSubtypes(stack)
						? block.getData() == this.getVanillaItemStack(stack).getData()
						: block.getData() == -1);
	}

	@Override
	public String getItemName(ItemStack stack) {
		return getVanillaItemStack(stack).getName();
	}

	@Override
	public String getItemName(Block block) {
		return getItemName(block.getState().getData());
	}

	@Override
	public String getItemName(MaterialData data) {
		return getVanillaItemStack(data.toItemStack()).getName();
	}

	@SuppressWarnings("deprecation")
	private Material getBukkitMaterial(net.minecraft.server.v1_10_R1.Block block) {
		return Material.getMaterial(net.minecraft.server.v1_10_R1.Block.getId(block));
	}

	@SuppressWarnings("deprecation")
	private net.minecraft.server.v1_10_R1.Block getVanillaBlock(Material mat) {
		return net.minecraft.server.v1_10_R1.Block.getById(mat.getId());
	}

	private Player getBukkitPlayer(EntityPlayer player) {
		return Bukkit.getServer().getPlayer(player.getUniqueID());
	}

	@SuppressWarnings("deprecation")
	private EntityPlayer getVanillaPlayer(Player player) {
		for (EntityPlayer p : MinecraftServer.getServer().getPlayerList().players) {
			if (p.getUniqueID().equals(player.getUniqueId())) {
				return p;
			}
		}
		return null;
	}

	private World getBukkitWorld(net.minecraft.server.v1_10_R1.World world) {
		return (World) world.getWorld();
	}

	private net.minecraft.server.v1_10_R1.World getVanillaWorld(World world) {
		return getVanillaPlayer(world.getPlayers().get(0)).world;
	}

	private Location getBukkitLocation(World world, BlockPosition pos) {
		return new Location(world, pos.getX(), pos.getY(), pos.getZ());
	}

	private BlockPosition getVanillaLocation(Location loc) {
		return new BlockPosition(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
	}

	@SuppressWarnings("deprecation")
	private net.minecraft.server.v1_10_R1.IBlockData getVanillaBlockState(Block block) {
		return getVanillaBlock(block.getType()).fromLegacyData(block.getData());
	}

	private BlockFace getBukkitFace(EnumDirection dir) {
		for (BlockFace face : BlockFace.values())
			if (dir.name().equals(face.name()))
				return face;
		return null;
	}

	private EnumDirection getVanillaFace(BlockFace face) {
		if (face == null)
			return null;
		for (EnumDirection dir : EnumDirection.values())
			if (dir.name() != null && dir.name().equals(face.name()))
				return dir;
		return null;
	}

	private ItemStack getBukkitItemStack(net.minecraft.server.v1_10_R1.ItemStack stack) {
		return CraftItemStack.asBukkitCopy(stack);
	}

	private net.minecraft.server.v1_10_R1.ItemStack getVanillaItemStack(ItemStack stack) {
		return CraftItemStack.asNMSCopy(stack);
	}

	private BlockState getState(Block block, byte meta) {
		CraftBlockState state = new CraftBlockState(block);
		state.setRawData(meta);
		return state;
	}

}

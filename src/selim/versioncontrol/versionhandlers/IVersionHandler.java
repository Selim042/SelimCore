package selim.versioncontrol.versionhandlers;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator.ChunkData;
import org.bukkit.inventory.Inventory;
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

	public ChunkData getChunkData(World world);

    /**
     * Gets the next available NMS container id for the player
     * @param player The player to get the next container id of
     * @return The next available NMS container id0
     */
    int getNextContainerId(Player player);

    /**
     * Closes the current inventory for the player
     * @param player The player that needs their current inventory closed
     */
    void handleInventoryCloseEvent(Player player);

    /**
     * Sends PacketPlayOutOpenWindow to the player with the container id
     * @param player The player to send the packet to
     * @param containerId The container id to open
     */
    void sendPacketOpenWindow(Player player, int containerId);

    /**
     * Sends PacketPlayOutCloseWindow to the player with the contaienr id
     * @param player The player to send the packet to
     * @param containerId The container id to close
     */
    void sendPacketCloseWindow(Player player, int containerId);

    /**
     * Sets the NMS player's active container to the default one
     * @param player The player to set the active container of
     */
    void setActiveContainerDefault(Player player);

    /**
     * Sets the NMS player's active container to the one supplied
     * @param player The player to set the active container of
     * @param container The container to set as active
     */
    void setActiveContainer(Player player, Object container);

    /**
     * Sets the supplied windowId of the supplied Container
     * @param container The container to set the windowId of
     * @param containerId The new windowId
     */
    void setActiveContainerId(Object container, int containerId);

    /**
     * Adds a slot listener to the supplied container for the player
     * @param container The container to add the slot listener to
     * @param player The player to have as a listener
     */
    void addActiveContainerSlotListener(Object container, Player player);

    /**
     * Gets the {@link Inventory} wrapper of the supplied NMS container
     * @param container The NMS container to get the {@link Inventory} of
     * @return The inventory of the NMS container
     */
    Inventory toBukkitInventory(Object container);

    /**
     * Creates a new ContainerAnvil
     * @param player The player to get the container of
     * @return The Container instance
     */
    Object newContainerAnvil(Player player);

}

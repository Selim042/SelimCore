package selim.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.inventory.EquipmentSlot;

import com.comphenix.packetwrapper.WrapperPlayServerChat;
import com.comphenix.protocol.wrappers.WrappedChatComponent;

public class Helper {

	public static void sendActionbar(Player player, String message) {
		WrapperPlayServerChat packet = new WrapperPlayServerChat();
		packet.setMessage(WrappedChatComponent.fromText(message));
		packet.setPosition((byte) 2);
		packet.sendPacket(player);
	}

	public static String locationToString(Location loc) {
		return loc.getWorld().getUID() + ":" + loc.getX() + ":" + loc.getY() + ":" + loc.getZ();
	}

	public static Location locationFromString(String str) {
		String[] split = str.split(":");
		if (split.length != 4)
			return null;
		UUID uuid = UUID.fromString(split[0]);
		double x = Double.valueOf(split[1]);
		double y = Double.valueOf(split[2]);
		double z = Double.valueOf(split[3]);
		return new Location(worldFromUUID(uuid), x, y, z);
	}

	public static World worldFromUUID(UUID uuid) {
		for (World w : Bukkit.getWorlds())
			if (w.getUID().equals(uuid))
				return w;
		return null;
	}

	@SuppressWarnings("deprecation")
	public static void placeStates(Player player, List<BlockState> states, BlockFace face) {
		for (BlockState state : states) {
			if (canPlayerEdit(player, state.getLocation(), face)
					&& (player.getInventory().containsAtLeast(state.getData().toItemStack(), 1)
							|| player.getGameMode().equals(GameMode.CREATIVE))) {
				player.getWorld().getBlockAt(state.getLocation()).setType(state.getType());
				player.getWorld().getBlockAt(state.getLocation()).setData(state.getRawData());
			}
		}
	}

	public static boolean canPlayerEdit(Player player, Location loc, BlockFace face) {
		Block block = loc.getBlock();
		BlockPlaceEvent blockEvent = new BlockPlaceEvent(block, block.getState(),
				loc.getWorld().getBlockAt(offsetLocation(loc, face)),
				block.getState().getData().toItemStack(1), player, true, EquipmentSlot.HAND);
		SelimCore.MANAGER.callEvent(blockEvent);
		return !blockEvent.isCancelled();
	}

	public static Enchantment registerEnchantment(Class<? extends Enchantment> enchClass, int id) {
		try {
			Field f = Enchantment.class.getDeclaredField("acceptingNew");
			f.setAccessible(true);
			f.set(null, true);
		} catch (Exception e) {}
		try {
			Constructor<? extends Enchantment> constructor = enchClass.getConstructor(Integer.class);
			Enchantment ench = constructor.newInstance(id);
			Enchantment.registerEnchantment(ench);
			return ench;
		} catch (IllegalArgumentException e) {} catch (Exception e) {}
		return null;
	}

	public static Location offsetLocation(Location location, BlockFace face) {
		if (location == null)
			return null;
		location = location.clone();
		switch (face) {
		case DOWN:
			location.add(0, -1, 0);
			break;
		case EAST:
			location.add(1, 0, 0);
			break;
		case NORTH:
			location.add(0, 0, -1);
			break;
		case SOUTH:
			location.add(0, 0, 1);
			break;
		case UP:
			location.add(0, 1, 0);
			break;
		case WEST:
			location.add(-1, 0, 0);
			break;
		default:
			break;
		}
		return location;
	}

	public static PlayerAnimationEvent playerSwingArm(Player player) {
		PlayerAnimationEvent event = new PlayerAnimationEvent(player);
		SelimCore.MANAGER.callEvent(event);
		return event;
	}
}

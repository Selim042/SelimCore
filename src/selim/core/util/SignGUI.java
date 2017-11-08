package selim.core.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.BlockPosition;

/**
 * A sign gui, used for gathering a user's input
 * 
 * Obtained from <a
 * href=https://bukkit.org/threads/sign-gui-use-the-sign-interface-to-get-user-input.177030/>Bukkit
 * Forums</a> and modified to work with more modern versions of Minecraft and
 * SelimCore VersionHandlers.
 * 
 * @author nisovin
 */
public class SignGUI {

	protected ProtocolManager protocolManager;
	protected PacketAdapter packetListener;
	protected Map<UUID, SignGUIListener> listeners = new ConcurrentHashMap<UUID, SignGUIListener>();
	protected Map<UUID, Vector> signLocations = new ConcurrentHashMap<UUID, Vector>();
	protected Map<UUID, BlockState> placedSignLocations = new ConcurrentHashMap<UUID, BlockState>();

	public SignGUI(Plugin plugin) {
		protocolManager  = ProtocolLibrary.getProtocolManager();
		packetListener = new PacketListener(plugin);
		protocolManager.addPacketListener(packetListener);
	}

	public void open(Player player, SignGUIListener response) {
		open(player, (Location) null, response);
	}

	public void open(Player player, Location signLocation, SignGUIListener response) {
		int x = 0, y = 0, z = 0;
		if (signLocation != null) {
			x = signLocation.getBlockX();
			y = signLocation.getBlockY();
			z = signLocation.getBlockZ();
		}

		PacketContainer packet = protocolManager.createPacket(PacketType.Play.Server.OPEN_SIGN_EDITOR);
		packet.getBlockPositionModifier().write(0, new BlockPosition(x, y, z));

		try {
			protocolManager.sendServerPacket(player, packet);
			signLocations.put(player.getUniqueId(), new Vector(x, y, z));
			listeners.put(player.getUniqueId(), response);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public void open(Player player, String[] defaultText, SignGUIListener response) {
		List<PacketContainer> packets = new ArrayList<PacketContainer>();

		int x = 0, y = 0, z = 0;
		if (defaultText != null) {
			x = player.getLocation().getBlockX();
			z = player.getLocation().getBlockZ();

			Location loc = player.getLocation();
			loc.setY(0);
			placedSignLocations.put(player.getUniqueId(), loc.getBlock().getState());
			loc.getBlock().setType(Material.WALL_SIGN);
			Sign sign = (Sign) loc.getBlock().getState();
			for (int i = 0; i < defaultText.length && i < 4; i++)
				sign.setLine(i, defaultText[i]);
			sign.update();
			// PacketContainer teDataPacket = protocolManager
			// .createPacket(PacketType.Play.Server.TILE_ENTITY_DATA);
			// NbtCompound nbt = NbtFactory.ofCompound("");
			// nbt.put("x", x);
			// nbt.put("y", y);
			// nbt.put("z", z);
			// nbt.put("id", "minecraft:sign");
			// for (int i = 0; i < defaultText.length && i < 4; i++) {
			// String line = defaultText[i];
			// nbt.put("Text" + (i + 1), line);
			// }
			// teDataPacket.getBlockPositionModifier().write(0, new
			// BlockPosition(x, y, z));
			// // teDataPacket.getBytes().write(0, (byte) 9);
			// teDataPacket.getNbtModifier().write(0, nbt);
			// // teDataPacket.getIntegers().write(0, x).write(1, y).write(2,
			// // z).write(3, 63).write(4, 0);
			// packets.add(teDataPacket);
		}

		PacketContainer signOpenPacket = protocolManager
				.createPacket(PacketType.Play.Server.OPEN_SIGN_EDITOR);
		signOpenPacket.getBlockPositionModifier().write(0, new BlockPosition(x, y, z));
		packets.add(signOpenPacket);

		// if (defaultText != null) {
		// PacketContainer teDataPacket = protocolManager
		// .createPacket(PacketType.Play.Server.TILE_ENTITY_DATA);
		// teDataPacket.getBlockPositionModifier().write(0, new BlockPosition(x,
		// y, z));
		//// teDataPacket.getBytes().write(0, (byte) 9);
		// teDataPacket.getNbtModifier().write(0,
		// NbtFactory.ofWrapper(NbtType.TAG_END, ""));
		// packets.add(teDataPacket);
		// }

		try {
			for (PacketContainer packet : packets) {
				protocolManager.sendServerPacket(player, packet);
			}
			signLocations.put(player.getUniqueId(), new Vector(x, y, z));
			listeners.put(player.getUniqueId(), response);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public void destroy() {
		protocolManager.removePacketListener(packetListener);
		listeners.clear();
		signLocations.clear();
	}

	public static interface SignGUIListener {

		public void onSignDone(Player player, String[] lines);
	}

	public class PacketListener extends PacketAdapter {

		Plugin plugin;

		public PacketListener(Plugin plugin) {
			super(plugin, ListenerPriority.NORMAL, PacketType.Play.Client.UPDATE_SIGN);
			this.plugin = plugin;
		}

		@Override
		public void onPacketReceiving(PacketEvent event) {
			final Player player = event.getPlayer();
			Vector v = signLocations.remove(player.getUniqueId());
			if (v == null)
				return;
			BlockPosition pos = event.getPacket().getBlockPositionModifier().read(0);
			if (pos.getX() != v.getBlockX())
				return;
			if (pos.getY() != v.getBlockY())
				return;
			if (pos.getZ() != v.getBlockZ())
				return;

			final String[] lines = event.getPacket().getStringArrays().getValues().get(0);
			final SignGUIListener response = listeners.remove(event.getPlayer().getName());
			if (response != null) {
				event.setCancelled(true);
				Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

					public void run() {
						response.onSignDone(player, lines);
						BlockState state = placedSignLocations.remove(player.getUniqueId());
						if (state != null) {
							player.sendMessage("updating");
							state.update(true);
						}
					}
				}, 0);
			}
		}
	}

}
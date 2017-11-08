package selim.core.machines;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;

import com.comphenix.protocol.wrappers.nbt.NbtCompound;

public abstract class Machine {

	private boolean isDirty;
	private Material baseMaterial;
	private byte baseMeta;
	protected Location location;

	private final String id;

	public Machine(String id) {
		this.id = id;
	}

	public final void setBaseMaterial(Material material) {
		setBaseMaterial(material, (byte) 0);
	}

	public final void setBaseMaterial(Material material, byte meta) {
		this.baseMaterial = material;
		this.baseMeta = meta;
	}

	public final void setBaseMeta(byte meta) {
		this.baseMeta = meta;
	}

	public final Material getBaseMaterial() {
		return this.baseMaterial;
	}

	public final byte getBaseMeta() {
		return this.baseMeta;
	}

	public final Location getLocation() {
		return location;
	}

	public final void setLocation(Location location) {
		this.location = location;
	}

	public final void markDirty(boolean isDirty) {
		this.isDirty = isDirty;
	}

	public final boolean isDirty() {
		return this.isDirty;
	}

	public final String getID() {
		return this.id;
	}

	public NbtCompound writeToNbt(NbtCompound nbt) {
		this.markDirty(true);
		nbt.put("baseMaterial", this.baseMaterial.name());
		nbt.put("baseMeta", this.baseMeta);
		nbt.put("x", this.location.getBlockX());
		nbt.put("y", this.location.getBlockY());
		nbt.put("z", this.location.getBlockZ());
		nbt.put("world", this.location.getWorld().getName());
		return nbt;
	}

	public void readFromNbt(NbtCompound nbt) {
		this.baseMaterial = Material.valueOf(nbt.getString("baseMaterial"));
		this.baseMeta = nbt.getByte("baseMeta");
		this.location = new Location(Bukkit.getWorld(nbt.getString("world")), nbt.getInteger("x"),
				nbt.getInteger("y"), nbt.getInteger("z"));
	}

	public void onClicked(Player player, Action clickType) {}

}

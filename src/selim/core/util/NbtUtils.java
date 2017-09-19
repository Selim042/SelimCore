package selim.core.util;

import java.util.List;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.material.MaterialData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.comphenix.protocol.wrappers.nbt.NbtBase;
import com.comphenix.protocol.wrappers.nbt.NbtCompound;
import com.comphenix.protocol.wrappers.nbt.NbtFactory;
import com.comphenix.protocol.wrappers.nbt.NbtList;
import com.comphenix.protocol.wrappers.nbt.NbtType;

import selim.core.SelimCore;

public class NbtUtils {

	public static NbtCompound nbtFromStack(ItemStack stack) {
		if (stack == null || stack.getType() == Material.AIR || stack.getAmount() == 0) {
			NbtCompound nbt = NbtFactory.ofCompound("");
			return nbt;
		}
		stack = SelimCore.getVersionHandler().getCraftItemStack(stack);
		NbtCompound itemNbt = NbtFactory.ofCompound("");
		itemNbt.put("Count", (byte) stack.getAmount());
		itemNbt.put("Damage", stack.getDurability());
		itemNbt.put("id", StringIDHelper.getIDForMat(stack.getType()));
		itemNbt.put("tag", NbtFactory.fromItemTag(stack));
		return itemNbt;
	}

	public static ItemStack stackFromNbt(NbtCompound nbt) {
		if (!nbt.containsKey("id") || !nbt.containsKey("Count"))
			return null;
		ItemStack stack = SelimCore.getVersionHandler()
				.getCraftItemStack(new ItemStack(StringIDHelper.getMaterialForID(nbt.getString("id"))));
		stack.setAmount(nbt.getByte("Count"));
		if (nbt.containsKey("Damage"))
			stack.setDurability(nbt.getShort("Damage"));
		if (nbt.containsKey("tag"))
			NbtFactory.setItemTag(stack, nbt.getCompound("tag"));
		return stack;
	}

	public static NbtCompound nbtFromData(MaterialData data) {
		return nbtFromStack(data.toItemStack());
	}

	public static MaterialData dataFromNbt(NbtCompound nbt) {
		return stackFromNbt(nbt).getData();
	}

	public static NbtCompound merchantRecipeAsNbt(MerchantRecipe recipe) {
		NbtCompound recipeNbt = NbtFactory.ofCompound("");
		recipeNbt.put("rewardExp", recipe.hasExperienceReward() ? (byte) 1 : (byte) 0);
		recipeNbt.put("maxUses", recipe.getMaxUses());
		recipeNbt.put("uses", recipe.getUses());

		List<ItemStack> ingredients = recipe.getIngredients();
		ItemStack buy = ingredients.get(0);
		recipeNbt.put("buy", nbtFromStack(buy));
		if (ingredients.size() > 1) {
			ItemStack buyB = ingredients.get(1);
			if (buyB != null)
				recipeNbt.put("buy", nbtFromStack(buyB));
		}
		ItemStack sell = recipe.getResult();
		recipeNbt.put("sell", nbtFromStack(sell));
		return recipeNbt;
	}

	public static NbtCompound nbtFromEffect(PotionEffect effect) {
		NbtCompound effectNbt = NbtFactory.ofCompound("");
		effectNbt.put("effect", StringIDHelper.getIDForPotion(effect.getType()));
		effectNbt.put("duration", effect.getDuration());
		effectNbt.put("amplifier", effect.getAmplifier());
		effectNbt.put("ambient", effect.isAmbient() ? (byte) 1 : (byte) 0);
		effectNbt.put("particles", effect.hasParticles() ? (byte) 1 : (byte) 0);
		Color color = effect.getColor();
		if (color != null) {
			NbtCompound colorNbt = NbtFactory.ofCompound("");
			colorNbt.put("red", color.getRed());
			colorNbt.put("green", color.getGreen());
			colorNbt.put("blue", color.getBlue());
			effectNbt.put("color", colorNbt);
		}
		return effectNbt;
	}

	public static PotionEffect effectFromNbt(NbtCompound nbt) {
		if (!nbt.containsKey("effect") || !nbt.containsKey("duration") || !nbt.containsKey("amplifier"))
			return null;
		PotionEffectType effect = StringIDHelper.getPotionForID(nbt.getString("effect"));
		int duration = nbt.getInteger("duration");
		int amplifier = nbt.getInteger("amplifier");
		boolean ambient = false;
		boolean particles = true;
		if (nbt.containsKey("ambient"))
			ambient = nbt.getByte("ambient") == (byte) 1 ? true : false;
		if (nbt.containsKey("particles"))
			particles = nbt.getByte("particles") == (byte) 1 ? true : false;
		if (!nbt.containsKey("color"))
			return new PotionEffect(effect, duration, amplifier, ambient, particles);
		NbtCompound colorNbt = nbt.getCompound("color");
		int red = colorNbt.getInteger("red");
		int green = colorNbt.getInteger("green");
		int blue = colorNbt.getInteger("blue");
		return new PotionEffect(effect, duration, amplifier, ambient, particles,
				Color.fromRGB(red, green, blue));
	}

	public static NbtType getKeyType(final NbtCompound nbt, final String key) {
		return nbt.containsKey(key) ? nbt.getValue(key).getType() : null;
	}

	public static NbtType getNBTBaseType(final NbtBase<?> base) {
		return base.getType();
	}

	public static void nbtToStringList(List<String> output, final NbtCompound nbt) {
		nbtToStringList(output, nbt, "", null);
	}

	public static void nbtToStringList(List<String> output, final NbtCompound nbt, final String name) {
		nbtToStringList(output, nbt, "", name);
	}

	private static void addNBTKey(List<String> output, final NbtCompound nbt, final String indent,
			final String key) {
		switch (getKeyType(nbt, key)) {
		case TAG_END:
			break;
		case TAG_BYTE:
			output.add(" " + indent + key + ": " + nbt.getByte(key) + "b,");
			break;
		case TAG_SHORT:
			output.add(" " + indent + key + ": " + nbt.getShort(key) + "s,");
			break;
		case TAG_INT:
			output.add(" " + indent + key + ": " + nbt.getInteger(key) + ",");
			break;
		case TAG_LONG:
			output.add(" " + indent + key + ": " + nbt.getLong(key) + "L,");
			break;
		case TAG_FLOAT:
			output.add(" " + indent + key + ": " + nbt.getFloat(key) + ",");
			break;
		case TAG_DOUBLE:
			output.add(" " + indent + key + ": " + nbt.getDouble(key) + ",");
			break;
		case TAG_BYTE_ARRAY:
			output.add(" " + indent + key + ": [");
			byte[] bytes = nbt.getByteArray(key);
			for (byte b : bytes) {
				output.add("  " + indent + b + ",");
			}
			output.add(" " + indent + "],");
			break;
		case TAG_STRING:
			output.add(" " + indent + key + ": \"" + nbt.getString(key) + "\",");
			break;
		case TAG_LIST:
			NbtList<?> tagList = (NbtList<?>) nbt.getList(key);
			if (tagList != null && tagList.size() != 0) {
				output.add(" " + indent + key + ": [");
				for (int i = 0; i < tagList.size(); i++) {
					addNBTBase(output, tagList.getValue().get(i), "  " + indent, String.valueOf(i));
				}
				output.add(" " + indent + "],");
				break;
			}
			break;
		case TAG_COMPOUND:
			nbtToStringList(output, nbt.getCompound(key), " " + indent, key);
			break;
		case TAG_INT_ARRAY:
			output.add(" " + indent + key + ": [");
			int[] ints = nbt.getIntegerArray(key);
			for (int i : ints) {
				output.add("  \"" + indent + i + "\",");
			}
			output.add(" " + indent + "],");
			break;
		}
	}

	private static void addNBTBase(List<String> output, final NbtBase<?> base, final String indent,
			final String name) {
		switch (getNBTBaseType(base)) {
		case TAG_END:
			break;
		case TAG_BYTE:
			output.add(" " + indent + name + ": " + (byte) base.getValue() + "b,");
			break;
		case TAG_SHORT:
			output.add(" " + indent + name + ": " + (short) base.getValue() + "s,");
			break;
		case TAG_INT:
			output.add(" " + indent + name + ": " + (int) base.getValue() + ",");
			break;
		case TAG_LONG:
			output.add(" " + indent + name + ": " + (long) base.getValue() + "L,");
			break;
		case TAG_FLOAT:
			output.add(" " + indent + name + ": " + (float) base.getValue() + ",");
			break;
		case TAG_DOUBLE:
			output.add(" " + indent + name + ": " + (double) base.getValue() + ",");
			break;
		case TAG_BYTE_ARRAY:
			output.add(" " + indent + name + ": [");
			byte[] bytes = (byte[]) base.getValue();
			for (byte b : bytes) {
				output.add("  " + indent + b + ",");
			}
			output.add(" " + indent + "],");
			break;
		case TAG_STRING:
			output.add(" " + indent + name + ": \"" + (String) (base.getValue()) + "\",");
			break;
		case TAG_LIST:
			NbtList<?> tagList = (NbtList<?>) base;
			if (tagList != null) {
				output.add(" " + indent + name + ": [");
				for (int i = 0; i < tagList.size(); i++) {
					addNBTBase(output, (NbtBase<?>) tagList.getValue(i), "  " + indent,
							String.valueOf(i));
				}
				output.add(" " + indent + "],");
			}
			break;
		case TAG_COMPOUND:
			nbtToStringList(output, (NbtCompound) base, " " + indent, name);
			break;
		case TAG_INT_ARRAY:
			output.add(" " + indent + name + ": [");
			int[] ints = (int[]) base.getValue();
			for (int i : ints) {
				output.add("  " + indent + i);
			}
			output.add(" " + indent + "],");
			break;
		}
	}

	public static void nbtToStringList(List<String> output, final NbtCompound nbt, final String indent,
			final String name) {
		if (name != null && !name.equals(""))
			output.add(indent + name + ": {");
		else
			output.add(indent + "{");
		for (String key : nbt.getKeys()) {
			addNBTKey(output, nbt, indent, key);
		}
		output.add(indent + "},");
	}

}

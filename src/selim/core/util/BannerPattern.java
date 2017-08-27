package selim.core.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.comphenix.protocol.wrappers.nbt.NbtCompound;
import com.comphenix.protocol.wrappers.nbt.NbtFactory;

import selim.core.SelimCore;

public class BannerPattern {

	private final DyeColor color;
	private final EnumBannerPattern pattern;

	public BannerPattern(DyeColor color, EnumBannerPattern pattern) {
		this.color = color;
		this.pattern = pattern;
	}

	public DyeColor getColor() {
		return this.color;
	}

	public EnumBannerPattern getPattern() {
		return this.pattern;
	}

	@SuppressWarnings("deprecation")
	public NbtCompound toNbt() {
		NbtCompound nbt = NbtFactory.ofCompound("");
		nbt.put("Color", color.getDyeData());
		nbt.put("Pattern", pattern.code);
		return nbt;
	}

	@SuppressWarnings("deprecation")
	public static BannerPattern fromNbt(NbtCompound nbt) {
		if (!nbt.containsKey("Color") || !nbt.containsKey("Pattern"))
			return null;
		DyeColor color = DyeColor.getByDyeData(nbt.getByte("Color"));
		EnumBannerPattern pattern = EnumBannerPattern.getFromCode(nbt.getString("Pattern"));
		return new BannerPattern(color, pattern);
	}

	@SuppressWarnings("deprecation")
	public static ItemStack toBanner(BannerPattern... bannerPatterns) {
		if (bannerPatterns.length == 0)
			return null;
		BannerPattern initial = bannerPatterns[0];
		ItemStack stack;
		if (initial.pattern == EnumBannerPattern.BASE) {
			stack = SelimCore.getVersionHandler()
					.getCraftItemStack(new ItemStack(Material.BANNER, initial.color.getDyeData()));
			bannerPatterns = Arrays.copyOfRange(bannerPatterns, 1, bannerPatterns.length);
		} else
			stack = SelimCore.getVersionHandler()
					.getCraftItemStack(new ItemStack(Material.BANNER, DyeColor.WHITE.getDyeData()));
		NbtCompound itemNbt = (NbtCompound) NbtFactory.fromItemTag(stack);
		NbtCompound blockEntityNbt = NbtFactory.ofCompound("");
		List<NbtCompound> bannerNbt = new ArrayList<NbtCompound>();
		for (BannerPattern p : bannerPatterns)
			bannerNbt.add(p.toNbt());
		blockEntityNbt.put(NbtFactory.ofList("Patterns", bannerNbt));
		itemNbt.put("BlockEntityTag", blockEntityNbt);
		NbtFactory.setItemTag(stack, itemNbt);
		return stack;
	}

	@SuppressWarnings("deprecation")
	public static ItemStack toShield(BannerPattern... bannerPatterns) {
		if (bannerPatterns.length == 0)
			return null;
		BannerPattern initial = bannerPatterns[0];
		ItemStack stack = SelimCore.getVersionHandler()
				.getCraftItemStack(new ItemStack(Material.SHIELD));
		NbtCompound itemNbt = (NbtCompound) NbtFactory.fromItemTag(stack);
		NbtCompound blockEntityNbt = NbtFactory.ofCompound("");
		if (initial.pattern == EnumBannerPattern.BASE) {
			bannerPatterns = Arrays.copyOfRange(bannerPatterns, 1, bannerPatterns.length);
			blockEntityNbt.put("Base", (int) initial.color.getDyeData());
		}
		List<NbtCompound> bannerNbt = new ArrayList<NbtCompound>();
		for (BannerPattern p : bannerPatterns)
			bannerNbt.add(p.toNbt());
		blockEntityNbt.put(NbtFactory.ofList("Patterns", bannerNbt));
		itemNbt.put("BlockEntityTag", blockEntityNbt);
		NbtFactory.setItemTag(stack, itemNbt);
		return stack;
	}

	public static enum EnumBannerPattern {
		BASE("b"),
		BOTTOM_STRIPE("bs"),
		TOP_STRIPE("ts"),
		LEFT_STRIPE("ls"),
		RIGHT_STRIPE("rs"),
		CENTER_STRIPE("cs"),
		MIDDLE_STRIPE("ms"),
		DOWN_RIGHT_STRIPE("drs"),
		DOWN_LEFT_STRIPE("dls"),
		SMALL_STRIPES("ss"),
		DIAGONAL_CROSS("cr"),
		SQUARE_CROSS("sc"),
		LEFT_DIAGONAL("ld"),
		RIGHT_UPSIDE_DIAGONAL("rud"),
		LEFT_UPSIDE_DIAGONAL("lud"),
		RIGHT_DIAGONAL("rd"),
		VERTICAL_HALF_LEFT("vh"),
		VERTICAL_HALF_RIGHT("vhr"),
		HORIZONTAL_HALF_TOP("hh"),
		HORITZONTAL_HALF_BOTTOM("hhb"),
		BOTTOM_LEFT("bl"),
		BOTTOM_RIGHT("br"),
		TOP_LEFT("tl"),
		TOP_RIGHT("tr"),
		BOTTOM_TRIANGLE("bt"),
		TOP_TRIANGLE("tt"),
		BOTTOM_TRIANGLE_SAW("bts"),
		TOP_TRIANGLE_SAW("tts"),
		MIDDLE_CIRCLE("mc"),
		MIDDLE_RHOMBUS("mr"),
		BORDER("bo"),
		CURLY_BORDER("cbo"),
		BRICK("bri"),
		GRADIENT("gra"),
		REVERSE_GRADIENT("gru"),
		CREEPER("cre"),
		SKULL("sku"),
		FLOWER("flo"),
		MOJANG("moj");

		private final String code;

		EnumBannerPattern(String code) {
			this.code = code;
		}

		public String getCode() {
			return this.code;
		}

		public static EnumBannerPattern getFromCode(String code) {
			for (EnumBannerPattern pattern : EnumBannerPattern.values())
				if (pattern.code.equals(code))
					return pattern;
			return null;
		}
	}
}

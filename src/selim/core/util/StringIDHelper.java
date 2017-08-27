package selim.core.util;

import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

public class StringIDHelper {

	private static HashMap<Material, String> MATERIAL_IDS = new HashMap<Material, String>();
	private static HashMap<EntityType, String> ENTITY_IDS = new HashMap<EntityType, String>();

	static {
		for (Material mat : Material.values()) {
			switch (mat) {
			case ACACIA_DOOR:
				MATERIAL_IDS.put(mat, "minecraft:acacia_door");
				break;
			case ACACIA_DOOR_ITEM:
				MATERIAL_IDS.put(mat, "minecraft:acacia_door");
				break;
			case ACACIA_FENCE:
				MATERIAL_IDS.put(mat, "minecraft:acacia_fence");
				break;
			case ACACIA_FENCE_GATE:
				MATERIAL_IDS.put(mat, "minecraft:acacia_fence_gate");
				break;
			case ACACIA_STAIRS:
				MATERIAL_IDS.put(mat, "minecraft:acacia_stairs");
				break;
			case ACTIVATOR_RAIL:
				MATERIAL_IDS.put(mat, "minecraft:activator_rail");
				break;
			case AIR:
				MATERIAL_IDS.put(mat, "minecraft:air");
				break;
			case ANVIL:
				MATERIAL_IDS.put(mat, "minecraft:anvil");
				break;
			case APPLE:
				MATERIAL_IDS.put(mat, "minecraft:apple");
				break;
			case ARMOR_STAND:
				MATERIAL_IDS.put(mat, "minecraft:armor_stand");
				break;
			case ARROW:
				MATERIAL_IDS.put(mat, "minecraft:arrow");
				break;
			case BAKED_POTATO:
				MATERIAL_IDS.put(mat, "minecraft:baked_potato");
				break;
			case BANNER:
				MATERIAL_IDS.put(mat, "minecraft:banner");
				break;
			case BARRIER:
				MATERIAL_IDS.put(mat, "minecraft:barrier");
				break;
			case BEACON:
				MATERIAL_IDS.put(mat, "minecraft:beacon");
				break;
			case BED:
				MATERIAL_IDS.put(mat, "minecraft:bed");
				break;
			case BEDROCK:
				MATERIAL_IDS.put(mat, "minecraft:bedrock");
				break;
			case BED_BLOCK:
				MATERIAL_IDS.put(mat, "minecraft:bed");
				break;
			case BEETROOT:
				MATERIAL_IDS.put(mat, "minecraft:beetroot");
				break;
			case BEETROOT_BLOCK:
				MATERIAL_IDS.put(mat, "minecraft:beetroot");
				break;
			case BEETROOT_SEEDS:
				MATERIAL_IDS.put(mat, "minecraft:beetroot_seeds");
				break;
			case BEETROOT_SOUP:
				MATERIAL_IDS.put(mat, "minecraft:beetroot_soup");
				break;
			case BIRCH_DOOR:
				MATERIAL_IDS.put(mat, "minecraft:birch_door");
				break;
			case BIRCH_DOOR_ITEM:
				MATERIAL_IDS.put(mat, "minecraft:birch_door");
				break;
			case BIRCH_FENCE:
				MATERIAL_IDS.put(mat, "minecraft:birch_fence");
				break;
			case BIRCH_FENCE_GATE:
				MATERIAL_IDS.put(mat, "minecraft:birch_fence_gate");
				break;
			case BIRCH_WOOD_STAIRS:
				MATERIAL_IDS.put(mat, "minecraft:birch_stairs");
				break;
			case BLACK_SHULKER_BOX:
				MATERIAL_IDS.put(mat, "minecraft:black_shulker_box");
				break;
			case BLAZE_POWDER:
				MATERIAL_IDS.put(mat, "minecraft:blaze_powder");
				break;
			case BLAZE_ROD:
				MATERIAL_IDS.put(mat, "minecraft:blaze_rod");
				break;
			case BLUE_SHULKER_BOX:
				MATERIAL_IDS.put(mat, "minecraft:blue_shulker_box");
				break;
			case BOAT:
				MATERIAL_IDS.put(mat, "minecraft:boat");
				break;
			case BOAT_ACACIA:
				MATERIAL_IDS.put(mat, "minecraft:acacia_boat");
				break;
			case BOAT_BIRCH:
				MATERIAL_IDS.put(mat, "minecraft:birch_boat");
				break;
			case BOAT_DARK_OAK:
				MATERIAL_IDS.put(mat, "minecraft:dark_oak_boat");
				break;
			case BOAT_JUNGLE:
				MATERIAL_IDS.put(mat, "minecraft:jungle_boat");
				break;
			case BOAT_SPRUCE:
				MATERIAL_IDS.put(mat, "minecraft:spruce_boat");
				break;
			case BONE:
				MATERIAL_IDS.put(mat, "minecraft:bone");
				break;
			case BONE_BLOCK:
				MATERIAL_IDS.put(mat, "minecraft:bone_block");
				break;
			case BOOK:
				MATERIAL_IDS.put(mat, "minecraft:book");
				break;
			case BOOKSHELF:
				MATERIAL_IDS.put(mat, "minecraft:bookshelf");
				break;
			case BOOK_AND_QUILL:
				MATERIAL_IDS.put(mat, "minecraft:writable_book");
				break;
			case BOW:
				MATERIAL_IDS.put(mat, "minecraft:bow");
				break;
			case BOWL:
				MATERIAL_IDS.put(mat, "minecraft:bowl");
				break;
			case BREAD:
				MATERIAL_IDS.put(mat, "minecraft:bread");
				break;
			case BREWING_STAND:
				MATERIAL_IDS.put(mat, "minecraft:brewing_stand");
				break;
			case BREWING_STAND_ITEM:
				MATERIAL_IDS.put(mat, "minecraft:brewing_stand");
				break;
			case BRICK:
				MATERIAL_IDS.put(mat, "minecraft:brick_block");
				break;
			case BRICK_STAIRS:
				MATERIAL_IDS.put(mat, "minecraft:brick_stairs");
				break;
			case BROWN_MUSHROOM:
				MATERIAL_IDS.put(mat, "minecraft:brown_mushroom");
				break;
			case BROWN_SHULKER_BOX:
				MATERIAL_IDS.put(mat, "minecraft:brown_shulker_box");
				break;
			case BUCKET:
				MATERIAL_IDS.put(mat, "minecraft:bucket");
				break;
			case BURNING_FURNACE:
				MATERIAL_IDS.put(mat, "minecraft:lit_furnace");
				break;
			case CACTUS:
				MATERIAL_IDS.put(mat, "minecraft:cactus");
				break;
			case CAKE:
				MATERIAL_IDS.put(mat, "minecraft:cake");
				break;
			case CAKE_BLOCK:
				MATERIAL_IDS.put(mat, "minecraft:cake");
				break;
			case CARPET:
				MATERIAL_IDS.put(mat, "minecraft:carpet");
				break;
			case CARROT:
				MATERIAL_IDS.put(mat, "minecraft:carrot");
				break;
			case CARROT_ITEM:
				MATERIAL_IDS.put(mat, "minecraft:carrot");
				break;
			case CARROT_STICK:
				MATERIAL_IDS.put(mat, "minecraft:carrot_on_a_stick");
				break;
			case CAULDRON:
				MATERIAL_IDS.put(mat, "minecraft:cauldron");
				break;
			case CAULDRON_ITEM:
				MATERIAL_IDS.put(mat, "minecraft:cauldron");
				break;
			case CHAINMAIL_BOOTS:
				MATERIAL_IDS.put(mat, "minecraft:chainmail_boots");
				break;
			case CHAINMAIL_CHESTPLATE:
				MATERIAL_IDS.put(mat, "minecraft:chainmail_chestplate");
				break;
			case CHAINMAIL_HELMET:
				MATERIAL_IDS.put(mat, "minecraft:chainmail_helmet");
				break;
			case CHAINMAIL_LEGGINGS:
				MATERIAL_IDS.put(mat, "minecraft:chainmail_leggings");
				break;
			case CHEST:
				MATERIAL_IDS.put(mat, "minecraft:chest");
				break;
			case CHORUS_FLOWER:
				MATERIAL_IDS.put(mat, "minecraft:chorus_flower");
				break;
			case CHORUS_FRUIT:
				MATERIAL_IDS.put(mat, "minecraft:chorus_fruit");
				break;
			case CHORUS_FRUIT_POPPED:
				MATERIAL_IDS.put(mat, "minecraft:chorus_fruit_popped");
				break;
			case CHORUS_PLANT:
				MATERIAL_IDS.put(mat, "minecraft:chorus_plant");
				break;
			case CLAY:
				MATERIAL_IDS.put(mat, "minecraft:clay");
				break;
			case CLAY_BALL:
				MATERIAL_IDS.put(mat, "minecraft:clay_ball");
				break;
			case CLAY_BRICK:
				MATERIAL_IDS.put(mat, "minecraft:brick");
				break;
			case COAL:
				MATERIAL_IDS.put(mat, "minecraft:coal");
				break;
			case COAL_BLOCK:
				MATERIAL_IDS.put(mat, "minecraft:coal_block");
				break;
			case COAL_ORE:
				MATERIAL_IDS.put(mat, "minecraft:coal_ore");
				break;
			case COBBLESTONE:
				MATERIAL_IDS.put(mat, "minecraft:cobblestone");
				break;
			case COBBLESTONE_STAIRS:
				MATERIAL_IDS.put(mat, "minecraft:stone_stairs");
				break;
			case COBBLE_WALL:
				MATERIAL_IDS.put(mat, "minecraft:cobblestone_wall");
				break;
			case COCOA:
				MATERIAL_IDS.put(mat, "minecraft:cocoa");
				break;
			case COMMAND:
				MATERIAL_IDS.put(mat, "minecraft:command_block");
				break;
			case COMMAND_CHAIN:
				MATERIAL_IDS.put(mat, "minecraft:chain_command_block");
				break;
			case COMMAND_MINECART:
				MATERIAL_IDS.put(mat, "minecraft:command_block_minecart");
				break;
			case COMMAND_REPEATING:
				MATERIAL_IDS.put(mat, "minecraft:repeating_command_block");
				break;
			case COMPASS:
				MATERIAL_IDS.put(mat, "minecraft:compass");
				break;
			case COOKED_BEEF:
				MATERIAL_IDS.put(mat, "minecraft:cooked_beef");
				break;
			case COOKED_CHICKEN:
				MATERIAL_IDS.put(mat, "minecraft:cooked_chicken");
				break;
			case COOKED_FISH:
				MATERIAL_IDS.put(mat, "minecraft:cooked_fish");
				break;
			case COOKED_MUTTON:
				MATERIAL_IDS.put(mat, "minecraft:cooked_mutton");
				break;
			case COOKED_RABBIT:
				MATERIAL_IDS.put(mat, "minecraft:cooked_rabbit");
				break;
			case COOKIE:
				MATERIAL_IDS.put(mat, "minecraft:cookie");
				break;
			case CROPS:
				MATERIAL_IDS.put(mat, "minecraft:wheat");
				break;
			case CYAN_SHULKER_BOX:
				MATERIAL_IDS.put(mat, "minecraft:cyan_shulker_box");
				break;
			case DARK_OAK_DOOR:
				MATERIAL_IDS.put(mat, "minecraft:dark_oak_door");
				break;
			case DARK_OAK_DOOR_ITEM:
				MATERIAL_IDS.put(mat, "minecraft:dark_oak_door");
				break;
			case DARK_OAK_FENCE:
				MATERIAL_IDS.put(mat, "minecraft:dark_oak_fence");
				break;
			case DARK_OAK_FENCE_GATE:
				MATERIAL_IDS.put(mat, "minecraft:dark_oak_fence_gate");
				break;
			case DARK_OAK_STAIRS:
				MATERIAL_IDS.put(mat, "minecraft:dark_oak_stairs");
				break;
			case DAYLIGHT_DETECTOR:
				MATERIAL_IDS.put(mat, "minecraft:daylight_detector");
				break;
			case DAYLIGHT_DETECTOR_INVERTED:
				MATERIAL_IDS.put(mat, "minecraft:daylight_detector_inverted");
				break;
			case DEAD_BUSH:
				MATERIAL_IDS.put(mat, "minecraft:deadbush");
				break;
			case DETECTOR_RAIL:
				MATERIAL_IDS.put(mat, "minecraft:detector_rail");
				break;
			case DIAMOND:
				MATERIAL_IDS.put(mat, "minecraft:diamond");
				break;
			case DIAMOND_AXE:
				MATERIAL_IDS.put(mat, "minecraft:diamond_axe");
				break;
			case DIAMOND_BARDING:
				MATERIAL_IDS.put(mat, "minecraft:diamond_horse_armor");
				break;
			case DIAMOND_BLOCK:
				MATERIAL_IDS.put(mat, "minecraft:diamond_block");
				break;
			case DIAMOND_BOOTS:
				MATERIAL_IDS.put(mat, "minecraft:diamond_boots");
				break;
			case DIAMOND_CHESTPLATE:
				MATERIAL_IDS.put(mat, "minecraft:diamond_chestplate");
				break;
			case DIAMOND_HELMET:
				MATERIAL_IDS.put(mat, "minecraft:diamond_helmet");
				break;
			case DIAMOND_HOE:
				MATERIAL_IDS.put(mat, "minecraft:diamond_hoe");
				break;
			case DIAMOND_LEGGINGS:
				MATERIAL_IDS.put(mat, "minecraft:diamond_leggings");
				break;
			case DIAMOND_ORE:
				MATERIAL_IDS.put(mat, "minecraft:diamond_ore");
				break;
			case DIAMOND_PICKAXE:
				MATERIAL_IDS.put(mat, "minecraft:diamond_pickaxe");
				break;
			case DIAMOND_SPADE:
				MATERIAL_IDS.put(mat, "minecraft:diamond_shovel");
				break;
			case DIAMOND_SWORD:
				MATERIAL_IDS.put(mat, "minecraft:diamond_sword");
				break;
			case DIODE:
				MATERIAL_IDS.put(mat, "minecraft:repeater");
				break;
			case DIODE_BLOCK_OFF:
				MATERIAL_IDS.put(mat, "minecraft:unpowered_repeater");
				break;
			case DIODE_BLOCK_ON:
				MATERIAL_IDS.put(mat, "minecraft:powered_repeater");
				break;
			case DIRT:
				MATERIAL_IDS.put(mat, "minecraft:dirt");
				break;
			case DISPENSER:
				MATERIAL_IDS.put(mat, "minecraft:dispenser");
				break;
			case DOUBLE_PLANT:
				MATERIAL_IDS.put(mat, "minecraft:double_plant");
				break;
			case DOUBLE_STEP:
				MATERIAL_IDS.put(mat, "minecraft:double_stone_slab");
				break;
			case DOUBLE_STONE_SLAB2:
				MATERIAL_IDS.put(mat, "minecraft:double_stone_slab2");
				break;
			case DRAGONS_BREATH:
				MATERIAL_IDS.put(mat, "minecraft:dragon_breath");
				break;
			case DRAGON_EGG:
				MATERIAL_IDS.put(mat, "minecraft:dragon_egg");
				break;
			case DROPPER:
				MATERIAL_IDS.put(mat, "minecraft:dropper");
				break;
			case EGG:
				MATERIAL_IDS.put(mat, "minecraft:egg");
				break;
			case ELYTRA:
				MATERIAL_IDS.put(mat, "minecraft:elytra");
				break;
			case EMERALD:
				MATERIAL_IDS.put(mat, "minecraft:emerald");
				break;
			case EMERALD_BLOCK:
				MATERIAL_IDS.put(mat, "minecraft:emerald_block");
				break;
			case EMERALD_ORE:
				MATERIAL_IDS.put(mat, "minecraft:emerald_ore");
				break;
			case EMPTY_MAP:
				MATERIAL_IDS.put(mat, "minecraft:map");
				break;
			case ENCHANTED_BOOK:
				MATERIAL_IDS.put(mat, "minecraft:enchanted_book");
				break;
			case ENCHANTMENT_TABLE:
				MATERIAL_IDS.put(mat, "minecraft:enchanting_table");
				break;
			case ENDER_CHEST:
				MATERIAL_IDS.put(mat, "minecraft:ender_chest");
				break;
			case ENDER_PEARL:
				MATERIAL_IDS.put(mat, "minecraft:ender_pearl");
				break;
			case ENDER_PORTAL:
				MATERIAL_IDS.put(mat, "minecraft:end_portal");
				break;
			case ENDER_PORTAL_FRAME:
				MATERIAL_IDS.put(mat, "minecraft:end_portal_frame");
				break;
			case ENDER_STONE:
				MATERIAL_IDS.put(mat, "minecraft:end_stone");
				break;
			case END_BRICKS:
				MATERIAL_IDS.put(mat, "minecraft:end_bricks");
				break;
			case END_CRYSTAL:
				MATERIAL_IDS.put(mat, "minecraft:end_crystal");
				break;
			case END_GATEWAY:
				MATERIAL_IDS.put(mat, "minecraft:end_gateway");
				break;
			case END_ROD:
				MATERIAL_IDS.put(mat, "minecraft:end_rod");
				break;
			case EXPLOSIVE_MINECART:
				MATERIAL_IDS.put(mat, "minecraft:tnt_minecart");
				break;
			case EXP_BOTTLE:
				MATERIAL_IDS.put(mat, "minecraft:experience_bottle");
				break;
			case EYE_OF_ENDER:
				MATERIAL_IDS.put(mat, "minecraft:ender_eye");
				break;
			case FEATHER:
				MATERIAL_IDS.put(mat, "minecraft:feather");
				break;
			case FENCE:
				MATERIAL_IDS.put(mat, "minecraft:fence");
				break;
			case FENCE_GATE:
				MATERIAL_IDS.put(mat, "minecraft:fence_gate");
				break;
			case FERMENTED_SPIDER_EYE:
				MATERIAL_IDS.put(mat, "minecraft:fermented_spider_eye");
				break;
			case FIRE:
				MATERIAL_IDS.put(mat, "minecraft:fire");
				break;
			case FIREBALL:
				MATERIAL_IDS.put(mat, "minecraft:fire_charge");
				break;
			case FIREWORK:
				MATERIAL_IDS.put(mat, "minecraft:fireworks");
				break;
			case FIREWORK_CHARGE:
				MATERIAL_IDS.put(mat, "minecraft:firework_charge");
				break;
			case FISHING_ROD:
				MATERIAL_IDS.put(mat, "minecraft:fishing_rod");
				break;
			case FLINT:
				MATERIAL_IDS.put(mat, "minecraft:flint");
				break;
			case FLINT_AND_STEEL:
				MATERIAL_IDS.put(mat, "minecraft:flint_and_steel");
				break;
			case FLOWER_POT:
				MATERIAL_IDS.put(mat, "minecraft:flower_pot");
				break;
			case FLOWER_POT_ITEM:
				MATERIAL_IDS.put(mat, "minecraft:flower_pot");
				break;
			case FROSTED_ICE:
				MATERIAL_IDS.put(mat, "minecraft:frosted_ice");
				break;
			case FURNACE:
				MATERIAL_IDS.put(mat, "minecraft:furnace");
				break;
			case GHAST_TEAR:
				MATERIAL_IDS.put(mat, "minecraft:ghast_tear");
				break;
			case GLASS:
				MATERIAL_IDS.put(mat, "minecraft:glass");
				break;
			case GLASS_BOTTLE:
				MATERIAL_IDS.put(mat, "minecraft:glass_bottle");
				break;
			case GLOWING_REDSTONE_ORE:
				MATERIAL_IDS.put(mat, "minecraft:lit_redstone_ore");
				break;
			case GLOWSTONE:
				MATERIAL_IDS.put(mat, "minecraft:glowstone");
				break;
			case GLOWSTONE_DUST:
				MATERIAL_IDS.put(mat, "minecraft:glowstone_dust");
				break;
			case GOLDEN_APPLE:
				MATERIAL_IDS.put(mat, "minecraft:golden_apple");
				break;
			case GOLDEN_CARROT:
				MATERIAL_IDS.put(mat, "minecraft:golden_carrot");
				break;
			case GOLD_AXE:
				MATERIAL_IDS.put(mat, "minecraft:golden_axe");
				break;
			case GOLD_BARDING:
				MATERIAL_IDS.put(mat, "minecraft:golden_horse_armor");
				break;
			case GOLD_BLOCK:
				MATERIAL_IDS.put(mat, "minecraft:gold_block");
				break;
			case GOLD_BOOTS:
				MATERIAL_IDS.put(mat, "minecraft:golden_boots");
				break;
			case GOLD_CHESTPLATE:
				MATERIAL_IDS.put(mat, "minecraft:golden_chestplate");
				break;
			case GOLD_HELMET:
				MATERIAL_IDS.put(mat, "minecraft:golden_helmet");
				break;
			case GOLD_HOE:
				MATERIAL_IDS.put(mat, "minecraft:golden_hoe");
				break;
			case GOLD_INGOT:
				MATERIAL_IDS.put(mat, "minecraft:gold_ingot");
				break;
			case GOLD_LEGGINGS:
				MATERIAL_IDS.put(mat, "minecraft:golden_leggings");
				break;
			case GOLD_NUGGET:
				MATERIAL_IDS.put(mat, "minecraft:gold_nugget");
				break;
			case GOLD_ORE:
				MATERIAL_IDS.put(mat, "minecraft:gold_ore");
				break;
			case GOLD_PICKAXE:
				MATERIAL_IDS.put(mat, "minecraft:golden_pickaxe");
				break;
			case GOLD_PLATE:
				MATERIAL_IDS.put(mat, "minecraft:light_weighted_pressure_plate");
				break;
			case GOLD_RECORD:
				MATERIAL_IDS.put(mat, "minecraft:record_13");
				break;
			case GOLD_SPADE:
				MATERIAL_IDS.put(mat, "minecraft:golden_shovel");
				break;
			case GOLD_SWORD:
				MATERIAL_IDS.put(mat, "minecraft:golden_sword");
				break;
			case GRASS:
				MATERIAL_IDS.put(mat, "minecraft:grass");
				break;
			case GRASS_PATH:
				MATERIAL_IDS.put(mat, "minecraft:grass_path");
				break;
			case GRAVEL:
				MATERIAL_IDS.put(mat, "minecraft:gravel");
				break;
			case GRAY_SHULKER_BOX:
				MATERIAL_IDS.put(mat, "minecraft:gray_shulker_box");
				break;
			case GREEN_RECORD:
				MATERIAL_IDS.put(mat, "minecraft:record_cat");
				break;
			case GREEN_SHULKER_BOX:
				MATERIAL_IDS.put(mat, "minecraft:green_shulker_box");
				break;
			case GRILLED_PORK:
				MATERIAL_IDS.put(mat, "minecraft:cooked_porkchop");
				break;
			case HARD_CLAY:
				MATERIAL_IDS.put(mat, "minecraft:hardened_clay");
				break;
			case HAY_BLOCK:
				MATERIAL_IDS.put(mat, "minecraft:hay_block");
				break;
			case HOPPER:
				MATERIAL_IDS.put(mat, "minecraft:hopper");
				break;
			case HOPPER_MINECART:
				MATERIAL_IDS.put(mat, "minecraft:hopper_minecart");
				break;
			case HUGE_MUSHROOM_1:
				MATERIAL_IDS.put(mat, "minecraft:brown_mushroom_block");
				break;
			case HUGE_MUSHROOM_2:
				MATERIAL_IDS.put(mat, "minecraft:red_mushroom_block");
				break;
			case ICE:
				MATERIAL_IDS.put(mat, "minecraft:ice");
				break;
			case INK_SACK:
				MATERIAL_IDS.put(mat, "minecraft:dye");
				break;
			case IRON_AXE:
				MATERIAL_IDS.put(mat, "minecraft:iron_axe");
				break;
			case IRON_BARDING:
				MATERIAL_IDS.put(mat, "minecraft:iron_horse_armor");
				break;
			case IRON_BLOCK:
				MATERIAL_IDS.put(mat, "minecraft:iron_block");
				break;
			case IRON_BOOTS:
				MATERIAL_IDS.put(mat, "minecraft:iron_boots");
				break;
			case IRON_CHESTPLATE:
				MATERIAL_IDS.put(mat, "minecraft:iron_chestplate");
				break;
			case IRON_DOOR:
				MATERIAL_IDS.put(mat, "minecraft:iron_door");
				break;
			case IRON_DOOR_BLOCK:
				MATERIAL_IDS.put(mat, "minecraft:iron_door");
				break;
			case IRON_FENCE:
				MATERIAL_IDS.put(mat, "minecraft:iron_bars");
				break;
			case IRON_HELMET:
				MATERIAL_IDS.put(mat, "minecraft:iron_helmet");
				break;
			case IRON_HOE:
				MATERIAL_IDS.put(mat, "minecraft:iron_hoe");
				break;
			case IRON_INGOT:
				MATERIAL_IDS.put(mat, "minecraft:iron_ingot");
				break;
			case IRON_LEGGINGS:
				MATERIAL_IDS.put(mat, "minecraft:iron_leggings");
				break;
			case IRON_NUGGET:
				MATERIAL_IDS.put(mat, "minecraft:iron_nugget");
				break;
			case IRON_ORE:
				MATERIAL_IDS.put(mat, "minecraft:iron_ore");
				break;
			case IRON_PICKAXE:
				MATERIAL_IDS.put(mat, "minecraft:iron_pickaxe");
				break;
			case IRON_PLATE:
				MATERIAL_IDS.put(mat, "minecraft:heavy_weighted_pressure_plate");
				break;
			case IRON_SPADE:
				MATERIAL_IDS.put(mat, "minecraft:iron_shovel");
				break;
			case IRON_SWORD:
				MATERIAL_IDS.put(mat, "minecraft:iron_sword");
				break;
			case IRON_TRAPDOOR:
				MATERIAL_IDS.put(mat, "minecraft:iron_trapdoor");
				break;
			case ITEM_FRAME:
				MATERIAL_IDS.put(mat, "minecraft:item_frame");
				break;
			case JACK_O_LANTERN:
				MATERIAL_IDS.put(mat, "minecraft:lit_pumpkin");
				break;
			case JUKEBOX:
				MATERIAL_IDS.put(mat, "minecraft:jukebox");
				break;
			case JUNGLE_DOOR:
				MATERIAL_IDS.put(mat, "minecraft:jungle_door");
				break;
			case JUNGLE_DOOR_ITEM:
				MATERIAL_IDS.put(mat, "minecraft:jungle_door");
				break;
			case JUNGLE_FENCE:
				MATERIAL_IDS.put(mat, "minecraft:jungle_fence");
				break;
			case JUNGLE_FENCE_GATE:
				MATERIAL_IDS.put(mat, "minecraft:jungle_fence_gate");
				break;
			case JUNGLE_WOOD_STAIRS:
				MATERIAL_IDS.put(mat, "minecraft:jungle_stairs");
				break;
			case LADDER:
				MATERIAL_IDS.put(mat, "minecraft:ladder");
				break;
			case LAPIS_BLOCK:
				MATERIAL_IDS.put(mat, "minecraft:lapis_block");
				break;
			case LAPIS_ORE:
				MATERIAL_IDS.put(mat, "minecraft:lapis_ore");
				break;
			case LAVA:
				MATERIAL_IDS.put(mat, "minecraft:lava");
				break;
			case LAVA_BUCKET:
				MATERIAL_IDS.put(mat, "minecraft:lava_bucket");
				break;
			case LEASH:
				MATERIAL_IDS.put(mat, "minecraft:lead");
				break;
			case LEATHER:
				MATERIAL_IDS.put(mat, "minecraft:leather");
				break;
			case LEATHER_BOOTS:
				MATERIAL_IDS.put(mat, "minecraft:leather_boots");
				break;
			case LEATHER_CHESTPLATE:
				MATERIAL_IDS.put(mat, "minecraft:leather_chestplate");
				break;
			case LEATHER_HELMET:
				MATERIAL_IDS.put(mat, "minecraft:leather_helmet");
				break;
			case LEATHER_LEGGINGS:
				MATERIAL_IDS.put(mat, "minecraft:leather_leggings");
				break;
			case LEAVES:
				MATERIAL_IDS.put(mat, "minecraft:leaves");
				break;
			case LEAVES_2:
				MATERIAL_IDS.put(mat, "minecraft:leaves2");
				break;
			case LEVER:
				MATERIAL_IDS.put(mat, "minecraft:lever");
				break;
			case LIGHT_BLUE_SHULKER_BOX:
				MATERIAL_IDS.put(mat, "minecraft:light_blue_shulker_box");
				break;
			case LIME_SHULKER_BOX:
				MATERIAL_IDS.put(mat, "minecraft:lime_shulker_box");
				break;
			case LINGERING_POTION:
				MATERIAL_IDS.put(mat, "minecraft:lingering_potion");
				break;
			case LOG:
				MATERIAL_IDS.put(mat, "minecraft:log");
				break;
			case LOG_2:
				MATERIAL_IDS.put(mat, "minecraft:log2");
				break;
			case LONG_GRASS:
				MATERIAL_IDS.put(mat, "minecraft:tallgrass");
				break;
			case MAGENTA_SHULKER_BOX:
				MATERIAL_IDS.put(mat, "minecraft:magenta_shulker_box");
				break;
			case MAGMA:
				MATERIAL_IDS.put(mat, "minecraft:magma");
				break;
			case MAGMA_CREAM:
				MATERIAL_IDS.put(mat, "minecraft:magma_cream");
				break;
			case MAP:
				MATERIAL_IDS.put(mat, "minecraft:filled_map");
				break;
			case MELON:
				MATERIAL_IDS.put(mat, "minecraft:melon");
				break;
			case MELON_BLOCK:
				MATERIAL_IDS.put(mat, "minecraft:melon_block");
				break;
			case MELON_SEEDS:
				MATERIAL_IDS.put(mat, "minecraft:melon_seeds");
				break;
			case MELON_STEM:
				MATERIAL_IDS.put(mat, "minecraft:melon_stem");
				break;
			case MILK_BUCKET:
				MATERIAL_IDS.put(mat, "minecraft:milk_bucket");
				break;
			case MINECART:
				MATERIAL_IDS.put(mat, "minecraft:minecart");
				break;
			case MOB_SPAWNER:
				MATERIAL_IDS.put(mat, "minecraft:mob_spawner");
				break;
			case MONSTER_EGG:
				MATERIAL_IDS.put(mat, "minecraft:spawn_egg");
				break;
			case MONSTER_EGGS:
				MATERIAL_IDS.put(mat, "minecraft:monster_egg");
				break;
			case MOSSY_COBBLESTONE:
				MATERIAL_IDS.put(mat, "minecraft:mossy_cobblestone");
				break;
			case MUSHROOM_SOUP:
				MATERIAL_IDS.put(mat, "minecraft:mushroom_stew");
				break;
			case MUTTON:
				MATERIAL_IDS.put(mat, "minecraft:mutton");
				break;
			case MYCEL:
				MATERIAL_IDS.put(mat, "minecraft:mycelium");
				break;
			case NAME_TAG:
				MATERIAL_IDS.put(mat, "minecraft:name_tag");
				break;
			case NETHERRACK:
				MATERIAL_IDS.put(mat, "minecraft:netherrack");
				break;
			case NETHER_BRICK:
				MATERIAL_IDS.put(mat, "minecraft:nether_brick");
				break;
			case NETHER_BRICK_ITEM:
				MATERIAL_IDS.put(mat, "minecraft:netherbrick");
				break;
			case NETHER_BRICK_STAIRS:
				MATERIAL_IDS.put(mat, "minecraft:nether_brick_stairs");
				break;
			case NETHER_FENCE:
				MATERIAL_IDS.put(mat, "minecraft:nether_brick_fence");
				break;
			case NETHER_STALK:
				MATERIAL_IDS.put(mat, "minecraft:nether_wart");
				break;
			case NETHER_STAR:
				MATERIAL_IDS.put(mat, "minecraft:nether_star");
				break;
			case NETHER_WARTS:
				MATERIAL_IDS.put(mat, "minecraft:nether_wart");
				break;
			case NETHER_WART_BLOCK:
				MATERIAL_IDS.put(mat, "minecraft:nether_wart_block");
				break;
			case NOTE_BLOCK:
				MATERIAL_IDS.put(mat, "minecraft:noteblock");
				break;
			case OBSERVER:
				MATERIAL_IDS.put(mat, "minecraft:observer");
				break;
			case OBSIDIAN:
				MATERIAL_IDS.put(mat, "minecraft:obsidian");
				break;
			case ORANGE_SHULKER_BOX:
				MATERIAL_IDS.put(mat, "minecraft:orange_shulker_box");
				break;
			case PACKED_ICE:
				MATERIAL_IDS.put(mat, "minecraft:packed_ice");
				break;
			case PAINTING:
				MATERIAL_IDS.put(mat, "minecraft:painting");
				break;
			case PAPER:
				MATERIAL_IDS.put(mat, "minecraft:paper");
				break;
			case PINK_SHULKER_BOX:
				MATERIAL_IDS.put(mat, "minecraft:pink_shulker_box");
				break;
			case PISTON_BASE:
				MATERIAL_IDS.put(mat, "minecraft:piston");
				break;
			case PISTON_EXTENSION:
				MATERIAL_IDS.put(mat, "minecraft:piston_head");
				break;
			case PISTON_MOVING_PIECE:
				MATERIAL_IDS.put(mat, "minecraft:piston_extension");
				break;
			case PISTON_STICKY_BASE:
				MATERIAL_IDS.put(mat, "minecraft:sticky_piston");
				break;
			case POISONOUS_POTATO:
				MATERIAL_IDS.put(mat, "minecraft:poisonous_potato");
				break;
			case PORK:
				MATERIAL_IDS.put(mat, "minecraft:porkchop");
				break;
			case PORTAL:
				MATERIAL_IDS.put(mat, "minecraft:portal");
				break;
			case POTATO:
				MATERIAL_IDS.put(mat, "minecraft:potato");
				break;
			case POTATO_ITEM:
				MATERIAL_IDS.put(mat, "minecraft:potato");
				break;
			case POTION:
				MATERIAL_IDS.put(mat, "minecraft:potion");
				break;
			case POWERED_MINECART:
				MATERIAL_IDS.put(mat, "minecraft:furnace_minecart");
				break;
			case POWERED_RAIL:
				MATERIAL_IDS.put(mat, "minecraft:golden_rail");
				break;
			case PRISMARINE:
				MATERIAL_IDS.put(mat, "minecraft:prismarine");
				break;
			case PRISMARINE_CRYSTALS:
				MATERIAL_IDS.put(mat, "minecraft:prismarine_crystals");
				break;
			case PRISMARINE_SHARD:
				MATERIAL_IDS.put(mat, "minecraft:prismarine_shard");
				break;
			case PUMPKIN:
				MATERIAL_IDS.put(mat, "minecraft:pumpkin");
				break;
			case PUMPKIN_PIE:
				MATERIAL_IDS.put(mat, "minecraft:pumpkin_pie");
				break;
			case PUMPKIN_SEEDS:
				MATERIAL_IDS.put(mat, "minecraft:pumpkin_seeds");
				break;
			case PUMPKIN_STEM:
				MATERIAL_IDS.put(mat, "minecraft:pumpkin_stem");
				break;
			case PURPLE_SHULKER_BOX:
				MATERIAL_IDS.put(mat, "minecraft:purple_shulker_box");
				break;
			case PURPUR_BLOCK:
				MATERIAL_IDS.put(mat, "minecraft:purpur_block");
				break;
			case PURPUR_DOUBLE_SLAB:
				MATERIAL_IDS.put(mat, "minecraft:purpur_double_slab");
				break;
			case PURPUR_PILLAR:
				MATERIAL_IDS.put(mat, "minecraft:purpur_pillar");
				break;
			case PURPUR_SLAB:
				MATERIAL_IDS.put(mat, "minecraft:purpur_slab");
				break;
			case PURPUR_STAIRS:
				MATERIAL_IDS.put(mat, "minecraft:purpur_stairs");
				break;
			case QUARTZ:
				MATERIAL_IDS.put(mat, "minecraft:quartz");
				break;
			case QUARTZ_BLOCK:
				MATERIAL_IDS.put(mat, "minecraft:quartz_block");
				break;
			case QUARTZ_ORE:
				MATERIAL_IDS.put(mat, "minecraft:quartz_ore");
				break;
			case QUARTZ_STAIRS:
				MATERIAL_IDS.put(mat, "minecraft:quartz_stairs");
				break;
			case RABBIT:
				MATERIAL_IDS.put(mat, "minecraft:rabbit");
				break;
			case RABBIT_FOOT:
				MATERIAL_IDS.put(mat, "minecraft:rabbit_foot");
				break;
			case RABBIT_HIDE:
				MATERIAL_IDS.put(mat, "minecraft:rabbit_hide");
				break;
			case RABBIT_STEW:
				MATERIAL_IDS.put(mat, "minecraft:rabbit_stew");
				break;
			case RAILS:
				MATERIAL_IDS.put(mat, "minecraft:rail");
				break;
			case RAW_BEEF:
				MATERIAL_IDS.put(mat, "minecraft:beef");
				break;
			case RAW_CHICKEN:
				MATERIAL_IDS.put(mat, "minecraft:chicken");
				break;
			case RAW_FISH:
				MATERIAL_IDS.put(mat, "minecraft:fish");
				break;
			case RECORD_10:
				MATERIAL_IDS.put(mat, "minecraft:record_ward");
				break;
			case RECORD_11:
				MATERIAL_IDS.put(mat, "minecraft:record_11");
				break;
			case RECORD_12:
				MATERIAL_IDS.put(mat, "minecraft:record_wait");
				break;
			case RECORD_3:
				MATERIAL_IDS.put(mat, "minecraft:record_blocks");
				break;
			case RECORD_4:
				MATERIAL_IDS.put(mat, "minecraft:record_chirp");
				break;
			case RECORD_5:
				MATERIAL_IDS.put(mat, "minecraft:record_far");
				break;
			case RECORD_6:
				MATERIAL_IDS.put(mat, "minecraft:record_mall");
				break;
			case RECORD_7:
				MATERIAL_IDS.put(mat, "minecraft:record_mellohi");
				break;
			case RECORD_8:
				MATERIAL_IDS.put(mat, "minecraft:record_stal");
				break;
			case RECORD_9:
				MATERIAL_IDS.put(mat, "minecraft:record_strad");
				break;
			case REDSTONE:
				MATERIAL_IDS.put(mat, "minecraft:redstone");
				break;
			case REDSTONE_BLOCK:
				MATERIAL_IDS.put(mat, "minecraft:redstone_block");
				break;
			case REDSTONE_COMPARATOR:
				MATERIAL_IDS.put(mat, "minecraft:comparator");
				break;
			case REDSTONE_COMPARATOR_OFF:
				MATERIAL_IDS.put(mat, "minecraft:unpowered_comparator");
				break;
			case REDSTONE_COMPARATOR_ON:
				MATERIAL_IDS.put(mat, "minecraft:powered_comparator");
				break;
			case REDSTONE_LAMP_OFF:
				MATERIAL_IDS.put(mat, "minecraft:redstone_lamp");
				break;
			case REDSTONE_LAMP_ON:
				MATERIAL_IDS.put(mat, "minecraft:lit_redstone_lamp");
				break;
			case REDSTONE_ORE:
				MATERIAL_IDS.put(mat, "minecraft:redstone_ore");
				break;
			case REDSTONE_TORCH_OFF:
				MATERIAL_IDS.put(mat, "minecraft:unlit_redstone_torch");
				break;
			case REDSTONE_TORCH_ON:
				MATERIAL_IDS.put(mat, "minecraft:redstone_torch");
				break;
			case REDSTONE_WIRE:
				MATERIAL_IDS.put(mat, "minecraft:redstone_wire");
				break;
			case RED_MUSHROOM:
				MATERIAL_IDS.put(mat, "minecraft:red_mushroom");
				break;
			case RED_NETHER_BRICK:
				MATERIAL_IDS.put(mat, "minecraft:red_nether_brick");
				break;
			case RED_ROSE:
				MATERIAL_IDS.put(mat, "minecraft:red_flower");
				break;
			case RED_SANDSTONE:
				MATERIAL_IDS.put(mat, "minecraft:red_sandstone");
				break;
			case RED_SANDSTONE_STAIRS:
				MATERIAL_IDS.put(mat, "minecraft:red_sandstone_stairs");
				break;
			case RED_SHULKER_BOX:
				MATERIAL_IDS.put(mat, "minecraft:red_shulker_box");
				break;
			case ROTTEN_FLESH:
				MATERIAL_IDS.put(mat, "minecraft:rotten_flesh");
				break;
			case SADDLE:
				MATERIAL_IDS.put(mat, "minecraft:saddle");
				break;
			case SAND:
				MATERIAL_IDS.put(mat, "minecraft:sand");
				break;
			case SANDSTONE:
				MATERIAL_IDS.put(mat, "minecraft:sandstone");
				break;
			case SANDSTONE_STAIRS:
				MATERIAL_IDS.put(mat, "minecraft:sandstone_stairs");
				break;
			case SAPLING:
				MATERIAL_IDS.put(mat, "minecraft:sapling");
				break;
			case SEA_LANTERN:
				MATERIAL_IDS.put(mat, "minecraft:sea_lantern");
				break;
			case SEEDS:
				MATERIAL_IDS.put(mat, "minecraft:wheat_seeds");
				break;
			case SHEARS:
				MATERIAL_IDS.put(mat, "minecraft:shears");
				break;
			case SHIELD:
				MATERIAL_IDS.put(mat, "minecraft:shield");
				break;
			case SHULKER_SHELL:
				MATERIAL_IDS.put(mat, "minecraft:shulker_shell");
				break;
			case SIGN:
				MATERIAL_IDS.put(mat, "minecraft:sign");
				break;
			case SIGN_POST:
				MATERIAL_IDS.put(mat, "minecraft:standing_sign");
				break;
			case SILVER_SHULKER_BOX:
				MATERIAL_IDS.put(mat, "minecraft:light_gray_shulker_box");
				break;
			case SKULL:
				MATERIAL_IDS.put(mat, "minecraft:skull");
				break;
			case SKULL_ITEM:
				MATERIAL_IDS.put(mat, "minecraft:skull");
				break;
			case SLIME_BALL:
				MATERIAL_IDS.put(mat, "minecraft:slime_ball");
				break;
			case SLIME_BLOCK:
				MATERIAL_IDS.put(mat, "minecraft:slime");
				break;
			case SMOOTH_BRICK:
				MATERIAL_IDS.put(mat, "minecraft:stonebrick");
				break;
			case SMOOTH_STAIRS:
				MATERIAL_IDS.put(mat, "minecraft:stone_brick_stairs");
				break;
			case SNOW:
				MATERIAL_IDS.put(mat, "minecraft:snow_layer");
				break;
			case SNOW_BALL:
				MATERIAL_IDS.put(mat, "minecraft:snowball");
				break;
			case SNOW_BLOCK:
				MATERIAL_IDS.put(mat, "minecraft:snow");
				break;
			case SOIL:
				MATERIAL_IDS.put(mat, "minecraft:farmland");
				break;
			case SOUL_SAND:
				MATERIAL_IDS.put(mat, "minecraft:soul_sand");
				break;
			case SPECKLED_MELON:
				MATERIAL_IDS.put(mat, "minecraft:speckled_melon");
				break;
			case SPECTRAL_ARROW:
				MATERIAL_IDS.put(mat, "minecraft:spectral_arrow");
				break;
			case SPIDER_EYE:
				MATERIAL_IDS.put(mat, "minecraft:spider_eye");
				break;
			case SPLASH_POTION:
				MATERIAL_IDS.put(mat, "minecraft:splash_potion");
				break;
			case SPONGE:
				MATERIAL_IDS.put(mat, "minecraft:sponge");
				break;
			case SPRUCE_DOOR:
				MATERIAL_IDS.put(mat, "minecraft:spruce_door");
				break;
			case SPRUCE_DOOR_ITEM:
				MATERIAL_IDS.put(mat, "minecraft:spruce_door");
				break;
			case SPRUCE_FENCE:
				MATERIAL_IDS.put(mat, "minecraft:spruce_fence");
				break;
			case SPRUCE_FENCE_GATE:
				MATERIAL_IDS.put(mat, "minecraft:spruce_fence_gate");
				break;
			case SPRUCE_WOOD_STAIRS:
				MATERIAL_IDS.put(mat, "minecraft:spruce_stairs");
				break;
			case STAINED_CLAY:
				MATERIAL_IDS.put(mat, "minecraft:stained_hardened_clay");
				break;
			case STAINED_GLASS:
				MATERIAL_IDS.put(mat, "minecraft:stained_glass");
				break;
			case STAINED_GLASS_PANE:
				MATERIAL_IDS.put(mat, "minecraft:stained_glass_pane");
				break;
			case STANDING_BANNER:
				MATERIAL_IDS.put(mat, "minecraft:standing_banner");
				break;
			case STATIONARY_LAVA:
				MATERIAL_IDS.put(mat, "minecraft:lava");
				break;
			case STATIONARY_WATER:
				MATERIAL_IDS.put(mat, "minecraft:water");
				break;
			case STEP:
				MATERIAL_IDS.put(mat, "minecraft:stone_slab");
				break;
			case STICK:
				MATERIAL_IDS.put(mat, "minecraft:stick");
				break;
			case STONE:
				MATERIAL_IDS.put(mat, "minecraft:stone");
				break;
			case STONE_AXE:
				MATERIAL_IDS.put(mat, "minecraft:stone_axe");
				break;
			case STONE_BUTTON:
				MATERIAL_IDS.put(mat, "minecraft:stone_button");
				break;
			case STONE_HOE:
				MATERIAL_IDS.put(mat, "minecraft:stone_hoe");
				break;
			case STONE_PICKAXE:
				MATERIAL_IDS.put(mat, "minecraft:stone_pickaxe");
				break;
			case STONE_PLATE:
				MATERIAL_IDS.put(mat, "minecraft:stone_pressure_plate");
				break;
			case STONE_SLAB2:
				MATERIAL_IDS.put(mat, "minecraft:stone_slab2");
				break;
			case STONE_SPADE:
				MATERIAL_IDS.put(mat, "minecraft:stone_shovel");
				break;
			case STONE_SWORD:
				MATERIAL_IDS.put(mat, "minecraft:stone_sword");
				break;
			case STORAGE_MINECART:
				MATERIAL_IDS.put(mat, "minecraft:chest_minecart");
				break;
			case STRING:
				MATERIAL_IDS.put(mat, "minecraft:string");
				break;
			case STRUCTURE_BLOCK:
				MATERIAL_IDS.put(mat, "minecraft:structure_block");
				break;
			case STRUCTURE_VOID:
				MATERIAL_IDS.put(mat, "minecraft:structure_void");
				break;
			case SUGAR:
				MATERIAL_IDS.put(mat, "minecraft:sugar");
				break;
			case SUGAR_CANE:
				MATERIAL_IDS.put(mat, "minecraft:reeds");
				break;
			case SUGAR_CANE_BLOCK:
				MATERIAL_IDS.put(mat, "minecraft:reeds");
				break;
			case SULPHUR:
				MATERIAL_IDS.put(mat, "minecraft:gunpowder");
				break;
			case THIN_GLASS:
				MATERIAL_IDS.put(mat, "minecraft:glass_pane");
				break;
			case TIPPED_ARROW:
				MATERIAL_IDS.put(mat, "minecraft:tipped_arrow");
				break;
			case TNT:
				MATERIAL_IDS.put(mat, "minecraft:tnt");
				break;
			case TORCH:
				MATERIAL_IDS.put(mat, "minecraft:torch");
				break;
			case TOTEM:
				MATERIAL_IDS.put(mat, "minecraft:totem_of_undying");
				break;
			case TRAPPED_CHEST:
				MATERIAL_IDS.put(mat, "minecraft:trapped_chest");
				break;
			case TRAP_DOOR:
				MATERIAL_IDS.put(mat, "minecraft:trapdoor");
				break;
			case TRIPWIRE:
				MATERIAL_IDS.put(mat, "minecraft:tripwire");
				break;
			case TRIPWIRE_HOOK:
				MATERIAL_IDS.put(mat, "minecraft:tripwire_hook");
				break;
			case VINE:
				MATERIAL_IDS.put(mat, "minecraft:vine");
				break;
			case WALL_BANNER:
				MATERIAL_IDS.put(mat, "minecraft:wall_banner");
				break;
			case WALL_SIGN:
				MATERIAL_IDS.put(mat, "minecraft:wall_sign");
				break;
			case WATCH:
				MATERIAL_IDS.put(mat, "minecraft:clock");
				break;
			case WATER:
				MATERIAL_IDS.put(mat, "minecraft:flowing_water");
				break;
			case WATER_BUCKET:
				MATERIAL_IDS.put(mat, "minecraft:water_bucket");
				break;
			case WATER_LILY:
				MATERIAL_IDS.put(mat, "minecraft:waterlily");
				break;
			case WEB:
				MATERIAL_IDS.put(mat, "minecraft:web");
				break;
			case WHEAT:
				MATERIAL_IDS.put(mat, "minecraft:wheat");
				break;
			case WHITE_SHULKER_BOX:
				MATERIAL_IDS.put(mat, "minecraft:white_shulker_box");
				break;
			case WOOD:
				MATERIAL_IDS.put(mat, "minecraft:log");
				break;
			case WOODEN_DOOR:
				MATERIAL_IDS.put(mat, "minecraft:wooden_door");
				break;
			case WOOD_AXE:
				MATERIAL_IDS.put(mat, "minecraft:wooden_axe");
				break;
			case WOOD_BUTTON:
				MATERIAL_IDS.put(mat, "minecraft:wooden_button");
				break;
			case WOOD_DOOR:
				MATERIAL_IDS.put(mat, "minecraft:wooden_door");
				break;
			case WOOD_DOUBLE_STEP:
				MATERIAL_IDS.put(mat, "minecraft:double_wooden_slab");
				break;
			case WOOD_HOE:
				MATERIAL_IDS.put(mat, "minecraft:wooden_hoe");
				break;
			case WOOD_PICKAXE:
				MATERIAL_IDS.put(mat, "minecraft:wooden_pickaxe");
				break;
			case WOOD_PLATE:
				MATERIAL_IDS.put(mat, "minecraft:wooden_pressure_plate");
				break;
			case WOOD_SPADE:
				MATERIAL_IDS.put(mat, "minecraft:wooden_shovel");
				break;
			case WOOD_STAIRS:
				MATERIAL_IDS.put(mat, "minecraft:oak_stairs");
				break;
			case WOOD_STEP:
				MATERIAL_IDS.put(mat, "minecraft:wooden_slab");
				break;
			case WOOD_SWORD:
				MATERIAL_IDS.put(mat, "minecraft:wooden_sword");
				break;
			case WOOL:
				MATERIAL_IDS.put(mat, "minecraft:wool");
				break;
			case WORKBENCH:
				MATERIAL_IDS.put(mat, "minecraft:crafting_table");
				break;
			case WRITTEN_BOOK:
				MATERIAL_IDS.put(mat, "minecraft:written_book");
				break;
			case YELLOW_FLOWER:
				MATERIAL_IDS.put(mat, "minecraft:yellow_flower");
				break;
			case YELLOW_SHULKER_BOX:
				MATERIAL_IDS.put(mat, "minecraft:yellow_shulker_box");
				break;
			}
		}
	}

	static {
		for (EntityType entity : EntityType.values()) {
			switch (entity) {
			case AREA_EFFECT_CLOUD:
				ENTITY_IDS.put(entity, "minecraft:area_effect_cloud");
				break;
			case ARMOR_STAND:
				ENTITY_IDS.put(entity, "minecraft:armor_stand");
				break;
			case ARROW:
				ENTITY_IDS.put(entity, "minecraft:arrow");
				break;
			case BAT:
				ENTITY_IDS.put(entity, "minecraft:bat");
				break;
			case BLAZE:
				ENTITY_IDS.put(entity, "minecraft:blaze");
				break;
			case BOAT:
				ENTITY_IDS.put(entity, "minecraft:boat");
				break;
			case CAVE_SPIDER:
				ENTITY_IDS.put(entity, "minecraft:cave_spider");
				break;
			case CHICKEN:
				ENTITY_IDS.put(entity, "minecraft:chicken");
				break;
			case COMPLEX_PART:
				// ENTITY_IDS.put(entity, "minecraft:");
				break;
			case COW:
				ENTITY_IDS.put(entity, "minecraft:cow");
				break;
			case CREEPER:
				ENTITY_IDS.put(entity, "minecraft:creeper");
				break;
			case DONKEY:
				ENTITY_IDS.put(entity, "minecraft:donkey");
				break;
			case DRAGON_FIREBALL:
				ENTITY_IDS.put(entity, "minecraft:dragon_fireball");
				break;
			case DROPPED_ITEM:
				ENTITY_IDS.put(entity, "minecraft:item");
				break;
			case EGG:
				ENTITY_IDS.put(entity, "minecraft:egg");
				break;
			case ELDER_GUARDIAN:
				ENTITY_IDS.put(entity, "minecraft:elder_guardian");
				break;
			case ENDERMAN:
				ENTITY_IDS.put(entity, "minecraft:enderman");
				break;
			case ENDERMITE:
				ENTITY_IDS.put(entity, "minecraft:endermite");
				break;
			case ENDER_CRYSTAL:
				ENTITY_IDS.put(entity, "minecraft:ender_crystal");
				break;
			case ENDER_DRAGON:
				ENTITY_IDS.put(entity, "minecraft:ender_dragon");
				break;
			case ENDER_PEARL:
				ENTITY_IDS.put(entity, "minecraft:ender_pearl");
				break;
			case ENDER_SIGNAL:
				ENTITY_IDS.put(entity, "minecraft:eye_of_ender_signal");
				break;
			case EVOKER:
				ENTITY_IDS.put(entity, "minecraft:evocation_villager");
				break;
			case EVOKER_FANGS:
				ENTITY_IDS.put(entity, "minecraft:evocation_fangs");
				break;
			case EXPERIENCE_ORB:
				ENTITY_IDS.put(entity, "minecraft:xp_orb");
				break;
			case FALLING_BLOCK:
				ENTITY_IDS.put(entity, "minecraft:falling_block");
				break;
			case FIREBALL:
				ENTITY_IDS.put(entity, "minecraft:fireball");
				break;
			case FIREWORK:
				ENTITY_IDS.put(entity, "minecraft:fireworks_rocket");
				break;
			case FISHING_HOOK:
				// ENTITY_IDS.put(entity, "minecraft:");
				break;
			case GHAST:
				ENTITY_IDS.put(entity, "minecraft:ghast");
				break;
			case GIANT:
				ENTITY_IDS.put(entity, "minecraft:giant");
				break;
			case GUARDIAN:
				ENTITY_IDS.put(entity, "minecraft:guardian");
				break;
			case HORSE:
				ENTITY_IDS.put(entity, "minecraft:horse");
				break;
			case HUSK:
				ENTITY_IDS.put(entity, "minecraft:husk");
				break;
			case IRON_GOLEM:
				ENTITY_IDS.put(entity, "minecraft:villager_golem");
				break;
			case ITEM_FRAME:
				ENTITY_IDS.put(entity, "minecraft:item_frame");
				break;
			case LEASH_HITCH:
				ENTITY_IDS.put(entity, "minecraft:leash_knot");
				break;
			case LIGHTNING:
				ENTITY_IDS.put(entity, "minecraft:lightning_bolt");
				break;
			case LINGERING_POTION:
				ENTITY_IDS.put(entity, "minecraft:potion");
				break;
			case LLAMA:
				ENTITY_IDS.put(entity, "minecraft:llama");
				break;
			case LLAMA_SPIT:
				ENTITY_IDS.put(entity, "minecraft:llama_spit");
				break;
			case MAGMA_CUBE:
				ENTITY_IDS.put(entity, "minecraft:magma_cube");
				break;
			case MINECART:
				ENTITY_IDS.put(entity, "minecraft:minecart");
				break;
			case MINECART_CHEST:
				ENTITY_IDS.put(entity, "minecraft:chest_minecart");
				break;
			case MINECART_COMMAND:
				ENTITY_IDS.put(entity, "minecraft:commandblock_minecart");
				break;
			case MINECART_FURNACE:
				ENTITY_IDS.put(entity, "minecraft:furnace_minecart");
				break;
			case MINECART_HOPPER:
				ENTITY_IDS.put(entity, "minecraft:hopper_minecart");
				break;
			case MINECART_MOB_SPAWNER:
				ENTITY_IDS.put(entity, "minecraft:spawner_minecart");
				break;
			case MINECART_TNT:
				ENTITY_IDS.put(entity, "minecraft:tnt_minecart");
				break;
			case MULE:
				ENTITY_IDS.put(entity, "minecraft:mule");
				break;
			case MUSHROOM_COW:
				ENTITY_IDS.put(entity, "minecraft:mooshroom");
				break;
			case OCELOT:
				ENTITY_IDS.put(entity, "minecraft:ocelot");
				break;
			case PAINTING:
				ENTITY_IDS.put(entity, "minecraft:painting");
				break;
			case PIG:
				ENTITY_IDS.put(entity, "minecraft:pig");
				break;
			case PIG_ZOMBIE:
				ENTITY_IDS.put(entity, "minecraft:zombie_pigman");
				break;
			case PLAYER:
				ENTITY_IDS.put(entity, "minecraft:player");
				break;
			case POLAR_BEAR:
				ENTITY_IDS.put(entity, "minecraft:polar_bear");
				break;
			case PRIMED_TNT:
				ENTITY_IDS.put(entity, "minecraft:tnt");
				break;
			case RABBIT:
				ENTITY_IDS.put(entity, "minecraft:rabbit");
				break;
			case SHEEP:
				ENTITY_IDS.put(entity, "minecraft:sheep");
				break;
			case SHULKER:
				ENTITY_IDS.put(entity, "minecraft:shulker");
				break;
			case SHULKER_BULLET:
				ENTITY_IDS.put(entity, "minecraft:shulker_bullet");
				break;
			case SILVERFISH:
				ENTITY_IDS.put(entity, "minecraft:silverfish");
				break;
			case SKELETON:
				ENTITY_IDS.put(entity, "minecraft:skeleton");
				break;
			case SKELETON_HORSE:
				ENTITY_IDS.put(entity, "minecraft:skeleton_horse");
				break;
			case SLIME:
				ENTITY_IDS.put(entity, "minecraft:slime");
				break;
			case SMALL_FIREBALL:
				ENTITY_IDS.put(entity, "minecraft:small_fireball");
				break;
			case SNOWBALL:
				ENTITY_IDS.put(entity, "minecraft:snowball");
				break;
			case SNOWMAN:
				ENTITY_IDS.put(entity, "minecraft:snowman");
				break;
			case SPECTRAL_ARROW:
				ENTITY_IDS.put(entity, "minecraft:spectral_arrow");
				break;
			case SPIDER:
				ENTITY_IDS.put(entity, "minecraft:spider");
				break;
			case SPLASH_POTION:
				ENTITY_IDS.put(entity, "minecraft:potion");
				break;
			case SQUID:
				ENTITY_IDS.put(entity, "minecraft:squid");
				break;
			case STRAY:
				ENTITY_IDS.put(entity, "minecraft:stray");
				break;
			case THROWN_EXP_BOTTLE:
				ENTITY_IDS.put(entity, "minecraft:xp_bottle");
				break;
			case TIPPED_ARROW:
				ENTITY_IDS.put(entity, "minecraft:tipped_arrow");
				break;
			case UNKNOWN:
				// ENTITY_IDS.put(entity, "minecraft:");
				break;
			case VEX:
				ENTITY_IDS.put(entity, "minecraft:vex");
				break;
			case VILLAGER:
				ENTITY_IDS.put(entity, "minecraft:villager");
				break;
			case VINDICATOR:
				ENTITY_IDS.put(entity, "minecraft:vindication_illager");
				break;
			case WEATHER:
				// ENTITY_IDS.put(entity, "minecraft:");
				break;
			case WITCH:
				ENTITY_IDS.put(entity, "minecraft:witch");
				break;
			case WITHER:
				ENTITY_IDS.put(entity, "minecraft:wither");
				break;
			case WITHER_SKELETON:
				ENTITY_IDS.put(entity, "minecraft:wither_skeleton");
				break;
			case WITHER_SKULL:
				ENTITY_IDS.put(entity, "minecraft:wither_skull");
				break;
			case WOLF:
				ENTITY_IDS.put(entity, "minecraft:wolf");
				break;
			case ZOMBIE:
				ENTITY_IDS.put(entity, "minecraft:zombie");
				break;
			case ZOMBIE_HORSE:
				ENTITY_IDS.put(entity, "minecraft:zombie_horse");
				break;
			case ZOMBIE_VILLAGER:
				ENTITY_IDS.put(entity, "minecraft:zombie_villager");
			default:
				break;
			}
		}
	}

	public static String getIDForMat(Material mat) {
		return MATERIAL_IDS.get(mat);
	}

	public static Material getMaterialForID(String id) {
		for (Entry<Material, String> s : MATERIAL_IDS.entrySet())
			if (s.getValue().equals(id))
				return s.getKey();
		return null;
	}

	public static String getIDForEntity(EntityType entity) {
		return ENTITY_IDS.get(entity);
	}

	public static EntityType getEntityForID(String id) {
		for (Entry<EntityType, String> s : ENTITY_IDS.entrySet())
			if (s.getValue().equals(id))
				return s.getKey();
		return null;
	}

}

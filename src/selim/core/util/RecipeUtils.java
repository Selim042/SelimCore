package selim.core.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.material.MaterialData;

import selim.core.SelimCore;
import selim.core.recipes.CoreRecipe;
import selim.core.recipes.CoreShapedRecipe;
import selim.core.recipes.CoreShapelessRecipe;

public class RecipeUtils {

	public static final String RECIPE_INV_NAME = "Recipe";

	private static final HashMap<String, List<CoreRecipe>> NAMED_RECIPES = new HashMap<String, List<CoreRecipe>>();
	// private static final List<CoreRecipe> RECIPE_TYPES = new
	// LinkedList<CoreRecipe>();
	private static boolean initilized = false;

	public static CoreRecipe getRecipe(String name) {
		return getRecipe(name, 0);
	}

	public static CoreRecipe getRecipe(String name, int pos) {
		if (!NAMED_RECIPES.containsKey(name))
			return null;
		List<CoreRecipe> recipes = NAMED_RECIPES.get(name);
		if (pos < recipes.size())
			return NAMED_RECIPES.get(name).get(pos);
		return null;
	}

	public static List<CoreRecipe> getRecipes(String name) {
		if (!NAMED_RECIPES.containsKey(name))
			return null;
		return new LinkedList<CoreRecipe>(NAMED_RECIPES.get(name));
	}

	public static String getRecipeName(Recipe recipe) {
		for (Entry<String, List<CoreRecipe>> e : NAMED_RECIPES.entrySet())
			for (Recipe r : e.getValue())
				if (r.equals(recipe))
					return e.getKey();
		return null;
	}

	public static List<String> getRecipeNames() {
		List<String> names = new ArrayList<String>();
		for (String name : NAMED_RECIPES.keySet())
			names.add(name);
		return names;
	}

	// private static void viewRecipeMenu(Player player) {
	// InventoryView view = player.openWorkbench(null, true);
	// PlayerInventory menu = (PlayerInventory) Bukkit.createInventory(null,
	// InventoryType.PLAYER,
	// "recipeViewer");
	// String[] recipeNames = NAMED_RECIPES.keySet().toArray(new
	// String[NAMED_RECIPES.size()]);
	// for (int i = 0; i < 35 && i < recipeNames.length; i++) {
	// Recipe r = getRecipe(recipeNames[i]);
	// ItemStack stack = r.getResult().clone();
	// if (stack.hasItemMeta()) {
	// ItemMeta meta = stack.getItemMeta();
	// if (meta.hasLore()) {
	// List<String> lore = meta.getLore();
	// lore.add(recipeNames[i]);
	// meta.setLore(lore);
	// stack.setItemMeta(meta);
	// }
	// }
	// menu.setItem(i, r.getResult());
	// }
	// }

	public static boolean viewRecipe(Player player, String name) {
		return viewRecipe(player, name, 0);
	}

	public static boolean viewRecipe(Player player, String name, int num) {
		return viewRecipe(player, getRecipe(name, num));
	}

	public static boolean viewRecipe(Player player, CoreRecipe recipe) {
		if (recipe == null)
			return false;
		Inventory recipeInv = recipe.viewRecipe();
		if (recipeInv == null)
			return false;
		player.openInventory(recipeInv);
		Bukkit.getPluginManager().callEvent(new InventoryClickEvent(player.getOpenInventory(),
				SlotType.RESULT, 0, ClickType.RIGHT, InventoryAction.PICKUP_ALL));
		player.sendMessage(recipe.getClass().getName());
		for (int i = 0; i < recipeInv.getSize(); i++) {
			ItemStack s = recipeInv.getItem(i);
			player.sendMessage(i + ": " + s);
		}
		return true;
	}

	@Deprecated
	/**
	 * @deprecated Use {@link #registerCoreShapedRecipe()} instead.
	 */
	public static ShapedRecipe registerShapedRecipe(String name, ItemStack output, String row1,
			String row2, String row3, Object... info) {
		CoreShapedRecipe recipe = registerCoreShapedRecipe(output, row1, row2, row3, info);
		List<CoreRecipe> recipes = NAMED_RECIPES.get(name);
		if (recipes == null)
			recipes = new LinkedList<CoreRecipe>();
		recipes.add(recipe);
		NAMED_RECIPES.put(name, recipes);
		return recipe;
	}

	@Deprecated
	/**
	 * @deprecated Use {@link #registerCoreShapedRecipe()} instead.
	 */
	public static ShapedRecipe registerShapedRecipe(ItemStack output, String row1, String row2,
			String row3, Object... info) {
		CoreShapedRecipe recipe = new CoreShapedRecipe(output);
		recipe.shape(row1, row2, row3);
		for (int i = 0; i < info.length; i += 2) {
			if (info[i] instanceof Character && info[i + 1] instanceof ItemStack) {
				ItemStack stack = (ItemStack) info[i + 1];
				recipe.setIngredient((Character) info[i], stack.getData());
			} else if (info[i] instanceof Character && info[i + 1] instanceof Material) {
				recipe.setIngredient((Character) info[i], (Material) info[i + 1]);
			}
		}
		Bukkit.getServer().addRecipe(recipe);
		return recipe;
	}

	@Deprecated
	/**
	 * @deprecated Use {@link #registerCoreShapelessRecipe()} instead.
	 */
	public static ShapelessRecipe registerShapelessRecipe(String name, ItemStack output,
			Object... info) {
		CoreShapelessRecipe recipe = registerCoreShapelessRecipe(output, info);
		List<CoreRecipe> recipes = NAMED_RECIPES.get(name);
		if (recipes == null)
			recipes = new LinkedList<CoreRecipe>();
		recipes.add(recipe);
		NAMED_RECIPES.put(name, recipes);
		return recipe;
	}

	@Deprecated
	/**
	 * @deprecated Use {@link #registerCoreShapelessRecipe()} instead.
	 */
	public static ShapelessRecipe registerShapelessRecipe(ItemStack output, Object... info) {
		ShapelessRecipe recipe = new ShapelessRecipe(output);
		for (Object obj : info) {
			if (obj instanceof MaterialData)
				recipe.addIngredient((MaterialData) obj);
			else if (obj instanceof Material)
				recipe.addIngredient((Material) obj);
			else if (obj instanceof ItemStack)
				recipe.addIngredient(((ItemStack) obj).getData());
		}
		Bukkit.getServer().addRecipe(recipe);
		return recipe;
	}

	public static CoreShapedRecipe registerCoreShapedRecipe(String name, ItemStack output, String row1,
			String row2, String row3, Object... info) {
		CoreShapedRecipe recipe = registerCoreShapedRecipe(output, row1, row2, row3, info);
		List<CoreRecipe> recipes = NAMED_RECIPES.get(name);
		if (recipes == null)
			recipes = new LinkedList<CoreRecipe>();
		recipes.add(recipe);
		NAMED_RECIPES.put(name, recipes);
		return recipe;
	}

	public static CoreShapedRecipe registerCoreShapedRecipe(ItemStack output, String row1, String row2,
			String row3, Object... info) {
		CoreShapedRecipe recipe = new CoreShapedRecipe(output);
		recipe.shape(row1, row2, row3);
		for (int i = 0; i < info.length; i += 2) {
			if (info[i] instanceof Character && info[i + 1] instanceof ItemStack) {
				ItemStack stack = (ItemStack) info[i + 1];
				recipe.setIngredient((Character) info[i], stack.getData());
			} else if (info[i] instanceof Character && info[i + 1] instanceof Material) {
				recipe.setIngredient((Character) info[i], (Material) info[i + 1]);
			}
		}
		Bukkit.getServer().addRecipe(recipe);
		return recipe;
	}

	public static CoreShapelessRecipe registerCoreShapelessRecipe(String name, ItemStack output,
			Object... info) {
		CoreShapelessRecipe recipe = registerCoreShapelessRecipe(output, info);
		List<CoreRecipe> recipes = NAMED_RECIPES.get(name);
		if (recipes == null)
			recipes = new LinkedList<CoreRecipe>();
		recipes.add(recipe);
		NAMED_RECIPES.put(name, recipes);
		return recipe;
	}

	public static CoreShapelessRecipe registerCoreShapelessRecipe(ItemStack output, Object... info) {
		CoreShapelessRecipe recipe = new CoreShapelessRecipe(output);
		for (Object obj : info) {
			if (obj instanceof MaterialData)
				recipe.addIngredient((MaterialData) obj);
			else if (obj instanceof Material)
				recipe.addIngredient((Material) obj);
			else if (obj instanceof ItemStack)
				recipe.addIngredient(((ItemStack) obj).getData());
		}
		Bukkit.getServer().addRecipe(recipe);
		return recipe;
	}

	public static <T extends CoreRecipe> T registerCoreRecipe(String name, T recipe) {
		List<CoreRecipe> recipes = NAMED_RECIPES.get(name);
		if (recipes == null)
			recipes = new LinkedList<CoreRecipe>();
		recipes.add(recipe);
		NAMED_RECIPES.put(name, recipes);
		return recipe;
	}

	public static <T extends CoreRecipe> T registerCoreRecipe(T recipe) {
		Bukkit.getServer().addRecipe(recipe);
		return recipe;
	}

	/**
	 * Disables all recipes resulting in the given ItemStack
	 */
	public static void disableRecipe(ItemStack result) {
		for (Entry<String, List<CoreRecipe>> s : NAMED_RECIPES.entrySet()) {
			for (Recipe r : s.getValue()) {
				if (r.getResult().isSimilar(result)) {
					List<CoreRecipe> recipes = s.getValue();
					recipes.remove(r);
					s.setValue(recipes);
				}
			}
		}
	}

	/**
	 * Disables any recipe matching the given name
	 */
	public static void disableRecipe(String name) {
		NAMED_RECIPES.remove(name);
	}

	public static void initRecipes() {
		if (initilized)
			return;
		Iterator<Recipe> rIt = Bukkit.recipeIterator();
		while (rIt.hasNext()) {
			Recipe r = rIt.next();
			String id = SelimCore.getVersionHandler().getRecipeName(r);
			List<CoreRecipe> recipes = NAMED_RECIPES.get(id);
			if (recipes == null)
				recipes = new LinkedList<CoreRecipe>();
			recipes.add(CoreRecipe.fromVanillaRecipe(r));
			NAMED_RECIPES.put(id, recipes);
		}
		initilized = true;
	}

}

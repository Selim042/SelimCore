package selim.core.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

public class RecipeUtils {

	public static final String RECIPE_INV_NAME = "Recipe";

	private static final HashMap<String, List<Recipe>> NAMED_RECIPES = new HashMap<String, List<Recipe>>();
	private static boolean initilized = false;

	public static Recipe getRecipe(String name) {
		return getRecipe(name, 0);
	}

	public static Recipe getRecipe(String name, int pos) {
		if (!NAMED_RECIPES.containsKey(name))
			return null;
		List<Recipe> recipes = NAMED_RECIPES.get(name);
		if (pos < recipes.size())
			return NAMED_RECIPES.get(name).get(pos);
		return null;
	}

	public static List<Recipe> getRecipes(String name) {
		if (!NAMED_RECIPES.containsKey(name))
			return null;
		return new LinkedList<Recipe>(NAMED_RECIPES.get(name));
	}

	public static String getRecipeName(Recipe recipe) {
		for (Entry<String, List<Recipe>> e : NAMED_RECIPES.entrySet())
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

	public static boolean viewRecipe(Player player, Recipe recipe) {
		if (recipe == null)
			return false;
		Inventory recipeInv = null;
		if (recipe instanceof ShapedRecipe) {
			ShapedRecipe shapedRecipe = (ShapedRecipe) recipe;
			recipeInv = Bukkit.createInventory(null, InventoryType.WORKBENCH, RECIPE_INV_NAME);
			Map<Character, ItemStack> map = shapedRecipe.getIngredientMap();
			for (int row = 0; row < shapedRecipe.getShape().length; row++) {
				String rowS = shapedRecipe.getShape()[row];
				for (int col = 0; col < rowS.length(); col++) {
					ItemStack stack = map.get(rowS.charAt(col));
					recipeInv.setItem(((row * 3) + 1) + col, stack);
				}
			}
			ItemStack stack = shapedRecipe.getResult();
			recipeInv.setItem(0, stack);
		} else if (recipe instanceof ShapelessRecipe) {
			ShapelessRecipe shapelessRecipe = (ShapelessRecipe) recipe;
			recipeInv = Bukkit.createInventory(null, InventoryType.WORKBENCH, RECIPE_INV_NAME);
			ItemStack result = shapelessRecipe.getResult().clone();
			if (result != null) {
				result = result.clone();
				ItemMeta meta = result.getItemMeta();
				List<String> lore = meta.getLore();
				if (lore == null)
					lore = new LinkedList<String>();
				lore.add("This recipe is shapeless.");
				meta.setLore(lore);
				result.setItemMeta(meta);
			}
			recipeInv.setItem(0, result);
			for (ItemStack ing : shapelessRecipe.getIngredientList()) {
				ItemStack stack = ing.clone();
				if (stack != null) {
					stack = stack.clone();
					ItemMeta meta = stack.getItemMeta();
					List<String> lore = meta.getLore();
					if (lore == null)
						lore = new LinkedList<String>();
					lore.add("This recipe is shapeless.");
					meta.setLore(lore);
					stack.setItemMeta(meta);
				}
				recipeInv.addItem(stack);
			}
			// CraftingInventory craftingRecipeInv = (CraftingInventory)
			// Bukkit.createInventory(null,
			// InventoryType.CRAFTING, RECIPE_INV_NAME);
			// for (ItemStack ing : shapelessRecipe.getIngredientList())
			// craftingRecipeInv.addItem(ing);
			// craftingRecipeInv.setResult(shapelessRecipe.getResult());
			// recipeInv = craftingRecipeInv;
		} else if (recipe instanceof FurnaceRecipe) {
			FurnaceRecipe furnaceRecipe = (FurnaceRecipe) recipe;
			recipeInv = Bukkit.createInventory(null, InventoryType.FURNACE, RECIPE_INV_NAME);
			recipeInv.setItem(2, furnaceRecipe.getResult());
			recipeInv.setItem(0, furnaceRecipe.getInput());
		}
		if (recipeInv == null)
			return false;
		player.openInventory(recipeInv);
		Bukkit.getPluginManager().callEvent(new InventoryClickEvent(player.getOpenInventory(),
				SlotType.RESULT, 0, ClickType.RIGHT, InventoryAction.PICKUP_ALL));
		return true;
	}

	public static ShapedRecipe registerShapedRecipe(String name, ItemStack output, String row1,
			String row2, String row3, Object... info) {
		ShapedRecipe recipe = registerShapedRecipe(output, row1, row2, row3, info);
		List<Recipe> recipes = NAMED_RECIPES.get(name);
		if (recipes == null)
			recipes = new LinkedList<Recipe>();
		recipes.add(recipe);
		NAMED_RECIPES.put(name, recipes);
		return recipe;
	}

	public static ShapedRecipe registerShapedRecipe(ItemStack output, String row1, String row2,
			String row3, Object... info) {
		ShapedRecipe recipe = new ShapedRecipe(output);
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

	public static ShapelessRecipe registerShapelessRecipe(String name, ItemStack output,
			Object... info) {
		ShapelessRecipe recipe = registerShapelessRecipe(output, info);
		List<Recipe> recipes = NAMED_RECIPES.get(name);
		if (recipes == null)
			recipes = new LinkedList<Recipe>();
		recipes.add(recipe);
		NAMED_RECIPES.put(name, recipes);
		return recipe;
	}

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

	/**
	 * Disables all recipes resulting in the given ItemStack
	 */
	public static void disableRecipe(ItemStack result) {
		for (Entry<String, List<Recipe>> s : NAMED_RECIPES.entrySet()) {
			for (Recipe r : s.getValue()) {
				if (r.getResult().isSimilar(result)) {
					List<Recipe> recipes = s.getValue();
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
			String id = StringIDHelper.getIDForMat(r.getResult().getType());
			List<Recipe> recipes = NAMED_RECIPES.get(id);
			if (recipes == null)
				recipes = new LinkedList<Recipe>();
			recipes.add(r);
			NAMED_RECIPES.put(id, recipes);
		}
		initilized = true;
	}

}

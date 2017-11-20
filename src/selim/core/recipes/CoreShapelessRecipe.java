package selim.core.recipes;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import selim.core.util.RecipeUtils;

public class CoreShapelessRecipe extends ShapelessRecipe implements CoreRecipe {

	public CoreShapelessRecipe(ItemStack stack) {
		super(stack);
	}

	public CoreShapelessRecipe(ShapelessRecipe recipe) {
		super(recipe.getResult());
		for (ItemStack i : recipe.getIngredientList())
			this.addIngredient(i.getData());
	}

	@Override
	public Inventory viewRecipe() {
		Inventory recipeInv = Bukkit.createInventory(null, InventoryType.WORKBENCH,
				RecipeUtils.RECIPE_INV_NAME);
		ItemStack result = this.getResult().clone();
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
		for (ItemStack ing : this.getIngredientList()) {
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
		return recipeInv;
	}

}

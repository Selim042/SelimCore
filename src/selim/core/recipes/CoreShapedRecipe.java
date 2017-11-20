package selim.core.recipes;

import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import selim.core.util.RecipeUtils;

public class CoreShapedRecipe extends ShapedRecipe implements CoreRecipe {

	public CoreShapedRecipe(ItemStack stack) {
		super(stack);
	}

	public CoreShapedRecipe(ShapedRecipe recipe) {
		super(recipe.getResult());
		this.shape(recipe.getShape());
		for (Entry<Character, ItemStack> s : recipe.getIngredientMap().entrySet())
			if (s != null && s.getValue() != null)
				this.setIngredient(s.getKey(), s.getValue().getData());
	}

	@Override
	public Inventory viewRecipe() {
		Inventory recipeInv = Bukkit.createInventory(null, InventoryType.WORKBENCH,
				RecipeUtils.RECIPE_INV_NAME);
		Map<Character, ItemStack> map = this.getIngredientMap();
		for (int row = 0; row < this.getShape().length; row++) {
			String rowS = this.getShape()[row];
			for (int col = 0; col < rowS.length(); col++) {
				ItemStack stack = map.get(rowS.charAt(col));
				recipeInv.setItem(((row * 3) + 1) + col, stack);
			}
		}
		ItemStack stack = this.getResult();
		recipeInv.setItem(0, stack);
		return recipeInv;
	}

}

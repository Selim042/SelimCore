package selim.core.recipes;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import selim.core.util.RecipeUtils;

public class CoreFurnaceRecipe extends FurnaceRecipe implements CoreRecipe {

	public CoreFurnaceRecipe(ItemStack result, Material source) {
		super(result, source);
	}

	public CoreFurnaceRecipe(ItemStack result, MaterialData source, float experience) {
		super(result, source, experience);
	}

	public CoreFurnaceRecipe(ItemStack result, MaterialData source) {
		super(result, source);
	}

	public CoreFurnaceRecipe(FurnaceRecipe recipe) {
		super(recipe.getResult(), recipe.getInput().getData(), recipe.getExperience());
	}

	@Override
	public Inventory viewRecipe() {
		Inventory recipeInv = Bukkit.createInventory(null, InventoryType.FURNACE,
				RecipeUtils.RECIPE_INV_NAME);
		recipeInv.setItem(2, this.getResult());
		recipeInv.setItem(0, this.getInput());
		return recipeInv;
	}

}

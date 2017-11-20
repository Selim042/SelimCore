package selim.core.recipes;

import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

public interface CoreRecipe extends Recipe {

	public Inventory viewRecipe();

	public static CoreRecipe fromVanillaRecipe(Recipe recipe) {
		if (recipe instanceof ShapedRecipe)
			return new CoreShapedRecipe((ShapedRecipe) recipe);
		else if (recipe instanceof ShapelessRecipe)
			return new CoreShapelessRecipe((ShapelessRecipe) recipe);
		else if (recipe instanceof FurnaceRecipe)
			return new CoreFurnaceRecipe((FurnaceRecipe) recipe);
		return null;
	}

}

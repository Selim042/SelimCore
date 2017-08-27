package selim.core.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import selim.core.util.RecipeUtils;

public class CommandViewRecipe implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("The command can only be executed by a player.");
			return true;
		}
		if (args.length == 1) {
			Player player = (Player) sender;
			if (!RecipeUtils.viewRecipe(player, args[0]))
				player.sendMessage("Recipe not found.");
			return true;
		} else if (args.length == 2) {
			int num;
			try {
				num = Integer.valueOf(args[1]);
			} catch (NumberFormatException e) {
				sender.sendMessage(command.getUsage());
				return true;
			}
			Player player = (Player) sender;
			if (!RecipeUtils.viewRecipe(player, args[0], num))
				player.sendMessage("Recipe not found");
			return true;
		}
		sender.sendMessage(command.getUsage());
		return true;
	}

	public static class TabCompleterViewRecipe implements TabCompleter {

		@Override
		public List<String> onTabComplete(CommandSender sender, Command command, String alias,
				String[] args) {
			if (args.length > 1)
				return null;
			if (sender instanceof Player) {
				List<String> possibleResults = RecipeUtils.getRecipeNames();
				List<String> results = new ArrayList<String>();
				for (String name : possibleResults)
					if (name != null && name.matches("(?i)(" + args[0] + ").*"))
						results.add(name);
				return results;
			}
			return null;
		}

	}

}

package selim.core.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import selim.core.util.RecipeUtils;

public class CommandViewRecipes implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("The command can only be executed by a player.");
			return true;
		}
		if (args.length != 1) {
			sender.sendMessage(command.getUsage());
			return true;
		}
		Player player = (Player) sender;
		if (!RecipeUtils.viewRecipe(player, args[0]))
			player.sendMessage("Recipe not found");
		return true;
	}

	public static class RecipeViewManager {
//		private static final List<UUID> PLAYERS = new LinkedList<UUID>();
	}

}

package selim.core.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import selim.core.leaderboards.ScoreTracker;
import selim.core.leaderboards.Scoreboard;
import selim.core.leaderboards.ScoreboardManager;

public class CommandSetupScoreboard implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Command must be executed by a player.");
			return true;
		}
		if (args.length != 6) {
			sender.sendMessage(ChatColor.RED + "Usage: " + command.getUsage());
			return true;
		}
		try {
			Player player = (Player) sender;
			ScoreTracker tracker = ScoreTracker.getTracker(args[0]);
			int x = Integer.valueOf(args[1]);
			int y = Integer.valueOf(args[2]);
			int z = Integer.valueOf(args[3]);
			Location loc = new Location(player.getWorld(), x, y, z);
			BlockFace facing = BlockFace.valueOf(args[4].toUpperCase());
			int place = Integer.valueOf(args[5]);
			if (tracker == null) {
				sender.sendMessage(ChatColor.RED + "Invalid tracker ID: " + args[0]);
				return true;
			}
			Scoreboard board = new Scoreboard(loc, facing, tracker.getID(), place);
			ScoreboardManager.addScoreboard(board);
			sender.sendMessage("Scoreboard added.");
			tracker.setUpdated(true);
		} catch (NumberFormatException e) {
			sender.sendMessage(ChatColor.RED + "Usage: " + command.getUsage());
			return true;
		}
		return true;
	}

	public static class TabCompleterSetupScoreboard implements TabCompleter {

		@Override
		public List<String> onTabComplete(CommandSender sender, Command command, String alias,
				String[] args) {
			if (args.length != 1)
				return null;
			Plugin[] plugins = Bukkit.getPluginManager().getPlugins();
			List<String> results = new ArrayList<String>();
			for (Plugin p : plugins) {
				String name = p.getName();
				if (name != null && name.matches("(?i)(" + args[0] + ").*"))
					results.add(p.getName());
			}
			return results;
		}
	}

}

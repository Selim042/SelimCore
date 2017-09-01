package selim.core.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import selim.core.Helper;
import selim.core.leaderboards.ScoreTracker;
import selim.core.leaderboards.Scoreboard;
import selim.core.leaderboards.ScoreboardManager;

public class CommandSetupScoreboard implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!sender.hasPermission("selimCore:setupScoreboard")) {
			sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command.");
			return true;
		}
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
			switch (args.length) {
			case 1:
				List<String> possibleResults = ScoreTracker.getTrackerIDs();
				List<String> results = new ArrayList<String>();
				for (String name : possibleResults)
					if (name != null && name.matches("(?i)(" + args[0] + ").*"))
						results.add(name);
				return results;
			case 2:
				if (!(sender instanceof Player))
					break;
				Player playerX = (Player) sender;
				Block blockX = playerX.getTargetBlock((Set<Material>) null, 5);
				return Collections.singletonList(Integer.toString(blockX.getX()));
			case 3:
				if (!(sender instanceof Player))
					break;
				Player playerY = (Player) sender;
				Block blockY = playerY.getTargetBlock((Set<Material>) null, 5);
				return Collections.singletonList(Integer.toString(blockY.getY()));
			case 4:
				if (!(sender instanceof Player))
					break;
				Player playerZ = (Player) sender;
				Block blockZ = playerZ.getTargetBlock((Set<Material>) null, 5);
				return Collections.singletonList(Integer.toString(blockZ.getZ()));
			case 5:
				if (!(sender instanceof Player))
					break;
				Player playerDir = (Player) sender;
				return Collections.singletonList(Helper.getPlayerFacing(playerDir).name());
			default:
				return null;
			}
			return null;
		}
	}

}

package selim.core.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.Plugin;

public class CommandPluginVersion implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length != 1) {
			sender.sendMessage(ChatColor.RED + command.getUsage());
			return true;
		}
		Plugin plugin = Bukkit.getPluginManager().getPlugin(args[0]);
		sender.sendMessage(args[0] + " version: " + plugin.getDescription().getVersion());
		return true;
	}

	public static class TabCompleterPluginVersion implements TabCompleter {

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

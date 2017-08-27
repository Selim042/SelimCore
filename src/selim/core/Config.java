package selim.core;

import org.bukkit.configuration.file.FileConfiguration;

public class Config {

	private static boolean initialized = false;

	private static boolean metrics;

	public static boolean getMetricsEnabled() {
		return metrics;
	}

	public static void init(FileConfiguration config) {
		if (initialized)
			return;
		config.getBoolean("misc.metrics", true);
	}

}

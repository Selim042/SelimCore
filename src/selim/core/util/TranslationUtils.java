package selim.core.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import selim.core.SelimCorePlugin;

public class TranslationUtils {

	private static HashMap<Player, String> PLAYER_LOCALES = new HashMap<Player, String>();
	private static HashMap<String, HashMap<String, String>> MAPPINGS = new HashMap<String, HashMap<String, String>>();

	public static void init(SelimCorePlugin plugin) {
		for (File f : new File(plugin.getDataFolder(), "lang").listFiles()) {
			String name = f.getName();
			name = name.substring(0, name.indexOf('.'));
			HashMap<String, String> mapping = MAPPINGS.get(name);
			MAPPINGS.put(name, readFile(f, mapping));
		}
	}

	private static HashMap<String, String> readFile(File file, HashMap<String, String> mapping) {
		if (mapping == null)
			mapping = new HashMap<String, String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			mapping = new HashMap<String, String>();
			String line = reader.readLine();
			while (line != null && !line.equals("")) {
				int index = line.indexOf("=");
				String key = line.substring(0, index);
				String value = line.substring(index + 1);
				mapping.put(key, value);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mapping;
	}

	/**
	 * Localizes the given string to en_US
	 */
	public static String translate(String unlocal) {
		HashMap<String, String> mapping = MAPPINGS.get("en_US");
		if (!mapping.containsKey(unlocal))
			return unlocal;
		return mapping.get(unlocal);
	}

	/**
	 * Localizes the given string, defaulting to en_US if null
	 */
	public static String translate(String unlocal, String locale) {
		HashMap<String, String> mapping = MAPPINGS.get(locale == null ? "en_US" : locale);
		if (!mapping.containsKey(unlocal))
			return translate(unlocal, "en_US");
		return mapping.get(unlocal);
	}

	public static String translateForPlayer(Player player, String unlocal) {
		return translate(unlocal, getPlayerLocale(player));
	}

	public static String translateForPlayer(UUID uuid, String unlocal) {
		Player player = Bukkit.getPlayer(uuid);
		if (player == null)
			translate(unlocal);
		return translate(unlocal, getPlayerLocale(player));
	}

	public static void setPlayerLocale(Player player, String locale) {
		PLAYER_LOCALES.put(player, locale);
	}

	public static void setPlayerLocale(UUID uuid, String locale) {
		Player player = Bukkit.getPlayer(uuid);
		if (player == null)
			return;
		PLAYER_LOCALES.put(player, locale);
	}

	public static String getPlayerLocale(Player player) {
		if (PLAYER_LOCALES.containsKey(player))
			return PLAYER_LOCALES.get(player);
		return "en_US";
	}

	public static String getPlayerLocale(UUID uuid) {
		Player player = Bukkit.getPlayer(uuid);
		if (player == null)
			return "en_US";
		if (PLAYER_LOCALES.containsKey(player))
			return PLAYER_LOCALES.get(player);
		return "en_US";
	}

}

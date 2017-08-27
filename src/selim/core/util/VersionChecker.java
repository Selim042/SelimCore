package selim.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

public class VersionChecker {

	private static final String USER_AGENT = "SelimCore";
	private static final String VERSION_REQUEST_URL = "https://api.spiget.org/v2/resources/{{ID}}/versions?size=1&sort=-releaseDate";
	private static final String DESCRIPTION_REQUEST_URL = "https://api.spiget.org/v2/resources/{{ID}}/updates?size=1&sort=date&fields=title";

	/***
	 * Gets the latest version of a given plugin id on
	 * <a href="http://spigotmc.org">SpigotMC.org</a>
	 */
	public static String getResourceVersion(int id) {
		try {
			URL url = new URL(VERSION_REQUEST_URL.replace("{{ID}}", Integer.toString(id)));
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.addRequestProperty("User-Agent", USER_AGENT);
			if (connection.getResponseCode() != 200)
				return null;
			InputStream inputStream = connection.getInputStream();
			InputStreamReader reader = new InputStreamReader(inputStream);
			Object value = JSONValue.parseWithException(reader);

			String data = value.toString();
			if (!data.matches(".*\"name\":\".*\",\"id.*"))
				return null;
			data = data.substring(data.indexOf("\"name\":\"") + 8, data.indexOf("\",\"id\""));
			return data;
		} catch (IOException | ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/***
	 * Gets most recent resource update title from
	 * <a href="http://spigotmc.org">SpigotMC.org</a>
	 */
	public static String getResourceUpdate(int id) {
		try {
			URL url = new URL(DESCRIPTION_REQUEST_URL.replace("{{ID}}", Integer.toString(id)));
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.addRequestProperty("User-Agent", USER_AGENT);
			if (connection.getResponseCode() != 200)
				return null;
			InputStream inputStream = connection.getInputStream();
			InputStreamReader reader = new InputStreamReader(inputStream);
			Object value = JSONValue.parseWithException(reader);

			String data = value.toString();
			if (!data.matches(".*\"title\":\".*\",\"likes.*"))
				return null;
			data = data.substring(data.indexOf("\"title\":\"") + 9, data.indexOf("\",\"likes\""));
			return data;
		} catch (IOException | ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void sendUpdateMessage(Player player, Plugin plugin, int id) {
		player.sendMessage(ChatColor.BLUE + "[" + ChatColor.AQUA + plugin.getName() + ChatColor.BLUE
				+ "] New update available:");
		player.sendMessage(
				ChatColor.BLUE + "Latest version: " + ChatColor.AQUA + getResourceVersion(id));
		player.sendMessage(ChatColor.BLUE + "Installed version: " + ChatColor.AQUA
				+ plugin.getDescription().getVersion());
		player.sendMessage(ChatColor.BLUE + "Changed: " + ChatColor.AQUA + getResourceUpdate(id));
	}

}

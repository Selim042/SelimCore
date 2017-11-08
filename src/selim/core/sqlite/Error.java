package selim.core.sqlite;

import java.util.logging.Level;

import selim.core.SelimCore;
import selim.core.SelimCorePlugin;

/***
 * Taken and slightly modified from
 * <a href="https://www.spigotmc.org/threads/how-to-sqlite.56847/">SpigotMC</a>.
 */
public class Error {

	public static void execute(SelimCorePlugin plugin, Exception ex) {
		SelimCore.getCoreLogger().log(Level.SEVERE, "Couldn't execute MySQL statement: ", ex);
	}

	public static void close(SelimCorePlugin plugin, Exception ex) {
		SelimCore.getCoreLogger().log(Level.SEVERE, "Failed to close MySQL connection: ", ex);
	}
}
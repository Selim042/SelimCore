package selim.core.sqlite;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

import selim.core.SelimCorePlugin;

/***
 * Taken and slightly modified from
 * <a href="https://www.spigotmc.org/threads/how-to-sqlite.56847/">SpigotMC</a>.
 */
public class SQLite extends Database {

	private final String databaseName;

	public SQLite(SelimCorePlugin instance, String databaseName) {
		super(instance, databaseName);
		this.databaseName = databaseName;
	}

	// SQL creation stuff, You can leave the blow stuff untouched.
	public Connection getSQLConnection() {
		File dataFolder = new File(plugin.getDataFolder(),
				"databases" + File.separator + databaseName + ".db");
		if (!dataFolder.exists()) {
			try {
				dataFolder.createNewFile();
			} catch (IOException e) {
				plugin.getLogger().log(Level.SEVERE, "File write error: " + databaseName + ".db");
			}
		}
		try {
			if (connection != null && !connection.isClosed()) {
				return connection;
			}
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:" + dataFolder);
			return connection;
		} catch (SQLException ex) {
			plugin.getLogger().log(Level.SEVERE, "SQLite exception on initialize", ex);
		} catch (ClassNotFoundException ex) {
			plugin.getLogger().log(Level.SEVERE,
					"You need the SQLite JBDC library. I recommend looking here: https://github.com/xerial/sqlite-jdbc/. Put it in /lib folder.");
		}
		return null;
	}

	public void load() {
		connection = getSQLConnection();
		try {
			Statement s = connection.createStatement();
			s.executeUpdate("CREATE TABLE IF NOT EXISTS " + this.databaseName + " ("
					+ "`player` varchar(32) NOT NULL," + "`kills` int(11) NOT NULL,"
					+ "`total` int(11) NOT NULL," + "PRIMARY KEY (`player`)" + ");");
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		initialize();
	}

}
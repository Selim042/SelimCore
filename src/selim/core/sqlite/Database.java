package selim.core.sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import org.bukkit.entity.Player;

import selim.core.SelimCore;
import selim.core.SelimCorePlugin;

/***
 * Taken and slightly modified from
 * <a href="https://www.spigotmc.org/threads/how-to-sqlite.56847/">SpigotMC</a>.
 */
public abstract class Database {

	protected Connection connection;
	protected final SelimCorePlugin plugin;
	private final String databaseName;
	private int tokens = 0;

	protected Database(SelimCorePlugin instance, String databaseName) {
		this.plugin = instance;
		this.databaseName = databaseName;
	}

	public abstract Connection getSQLConnection();

	public abstract void load();

	public void initialize() {
		connection = getSQLConnection();
		try {
			PreparedStatement ps = connection
					.prepareStatement("SELECT * FROM " + databaseName + " WHERE player = ?");
			ResultSet rs = ps.executeQuery();
			close(ps, rs);

		} catch (SQLException ex) {
			SelimCore.getCoreLogger().log(Level.SEVERE, "Unable to retreive connection", ex);
		}
	}

	// These are the methods you can use to get things out of your database. You
	// of course can make new ones to return different things in the database.
	// This returns the number of people the player killed.
	public Integer getTokens(String string) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getSQLConnection();
			ps = conn.prepareStatement("SELECT * FROM " + databaseName + " WHERE player = '" + string + "';");

			rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString("player").equalsIgnoreCase(string.toLowerCase())) {
					return rs.getInt("kills");
				}
			}
		} catch (SQLException ex) {
			SelimCore.getCoreLogger().log(Level.SEVERE, Errors.sqlConnectionExecute(), ex);
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (SQLException ex) {
				SelimCore.getCoreLogger().log(Level.SEVERE, Errors.sqlConnectionClose(), ex);
			}
		}
		return 0;
	}

	// Exact same method here, Except as mentioned above i am looking for total!
	public Integer getTotal(String string) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getSQLConnection();
			ps = conn.prepareStatement("SELECT * FROM " + databaseName + " WHERE player = '" + string + "';");

			rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString("player").equalsIgnoreCase(string.toLowerCase())) {
					return rs.getInt("total");
				}
			}
		} catch (SQLException ex) {
			SelimCore.getCoreLogger().log(Level.SEVERE, Errors.sqlConnectionExecute(), ex);
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (SQLException ex) {
				SelimCore.getCoreLogger().log(Level.SEVERE, Errors.sqlConnectionClose(), ex);
			}
		}
		return 0;
	}

	// Now we need methods to save things to the database
	public void setTokens(Player player, Integer tokens, Integer total) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getSQLConnection();
			ps = conn.prepareStatement("REPLACE INTO " + databaseName + " (player,kills,total) VALUES(?,?,?)");
			ps.setString(1, player.getName().toLowerCase());
			ps.setInt(2, tokens);
			ps.setInt(3, total);
			ps.executeUpdate();
			return;
		} catch (SQLException ex) {
			SelimCore.getCoreLogger().log(Level.SEVERE, Errors.sqlConnectionExecute(), ex);
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (SQLException ex) {
				SelimCore.getCoreLogger().log(Level.SEVERE, Errors.sqlConnectionClose(), ex);
			}
		}
		return;
	}

	public void close(PreparedStatement ps, ResultSet rs) {
		try {
			if (ps != null)
				ps.close();
			if (rs != null)
				rs.close();
		} catch (SQLException ex) {
			Error.close(plugin, ex);
		}
	}

}
package selim.core;

import org.bukkit.plugin.java.JavaPlugin;

import selim.core.sqlite.Database;
import selim.core.sqlite.SQLite;
import selim.core.util.SemanticVersion;

public abstract class SelimCorePlugin extends JavaPlugin {

	@Deprecated
	public double getMinimumCoreVersion() {
		return -1;
	}

	public abstract SemanticVersion getMinimumCoreSemVer();

	public int getSpigotResourceId() {
		return -1;
	}

	public final Database getDatabase(String name) {
		SQLite sql = new SQLite(this, name);
		sql.load();
		return sql;
	}

}

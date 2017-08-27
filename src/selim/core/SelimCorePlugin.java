package selim.core;

import org.bukkit.plugin.java.JavaPlugin;

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

}

package selim.core.games;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.plugin.Plugin;

public class GameJoinEvent extends PlayerEvent {

	private static final HandlerList handlers = new HandlerList();

	private final Plugin plugin;
	private final String gameName;
	private final String joinCmd;

	public GameJoinEvent(Plugin plugin, Player who, String gameName, String joinCmd) {
		super(who);
		this.plugin = plugin;
		this.gameName = gameName;
		this.joinCmd = joinCmd;
	}

	public Plugin getPlugin() {
		return this.plugin;
	}

	public String getGameName() {
		return this.gameName;
	}

	public String getJoinCommand() {
		return this.joinCmd;
	}

	@Override
	public HandlerList getHandlers() {
		return GameJoinEvent.handlers;
	}

	public static HandlerList getHandlerList() {
		return GameJoinEvent.handlers;
	}

}

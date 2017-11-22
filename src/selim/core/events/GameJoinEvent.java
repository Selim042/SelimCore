package selim.core.events;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

import selim.core.games.Game;

public class GameJoinEvent extends GameEvent {

	private static final HandlerList handlers = new HandlerList();

	private final Player player;
	private final String joinCmd;

	public GameJoinEvent(Game game, Player player, String joinCmd) {
		super(game);
		this.player = player;
		this.joinCmd = joinCmd;
	}

	public Player getPlayer() {
		return this.player;
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

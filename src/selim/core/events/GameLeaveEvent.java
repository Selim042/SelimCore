package selim.core.events;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

import selim.core.games.Game;

public class GameLeaveEvent extends GameEvent {

	private static final HandlerList handlers = new HandlerList();

	private final Player player;

	public GameLeaveEvent(Game game, Player player) {
		super(game);
		this.player = player;
	}

	public Player getPlayer() {
		return this.player;
	}

	@Override
	public HandlerList getHandlers() {
		return GameLeaveEvent.handlers;
	}

	public static HandlerList getHandlerList() {
		return GameLeaveEvent.handlers;
	}

}

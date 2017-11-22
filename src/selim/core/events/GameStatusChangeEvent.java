package selim.core.events;

import org.bukkit.event.HandlerList;

import selim.core.games.Game;
import selim.core.games.GameStatus;

public class GameStatusChangeEvent extends GameEvent {

	private final GameStatus oldStatus;
	private final GameStatus newStatus;

	public GameStatusChangeEvent(Game game, GameStatus oldStatus, GameStatus newStatus) {
		super(game);
		this.oldStatus = oldStatus;
		this.newStatus = newStatus;
	}

	public GameStatus getOldStatus() {
		return this.oldStatus;
	}

	public GameStatus getNewStatus() {
		return this.newStatus;
	}

	private static final HandlerList handlers = new HandlerList();

	@Override
	public HandlerList getHandlers() {
		return GameStatusChangeEvent.handlers;
	}

	public static HandlerList getHandlerList() {
		return GameStatusChangeEvent.handlers;
	}
}

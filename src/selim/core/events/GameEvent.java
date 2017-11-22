package selim.core.events;

import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;

import selim.core.games.Game;

public abstract class GameEvent extends Event {

	private final Game game;

	public GameEvent(Game game) {
		this.game = game;
	}

	public Plugin getPlugin() {
		return this.game.getPlugin();
	}

	public Game getGame() {
		return this.game;
	}

}

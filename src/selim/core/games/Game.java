package selim.core.games;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import selim.core.SelimCore;
import selim.core.events.GameJoinEvent;
import selim.core.events.GameLeaveEvent;
import selim.core.events.GameStatusChangeEvent;

public abstract class Game {

	private static final List<Game> GAMES = new LinkedList<Game>();

	public static List<Game> getAllGames() {
		return new LinkedList<Game>(GAMES);
	}

	public static Game getGame(String id, String mapId) {
		for (Game g : GAMES)
			if (g.id.equals(id) && g.mapId.equals(mapId))
				return g;
		return null;
	}

	public static Game getGame(String combinedId) {
		String gameId = combinedId.substring(0, combinedId.indexOf(":"));
		String mapId = combinedId.substring(combinedId.indexOf(":") + 1);
		return getGame(gameId, mapId);
	}

	public static List<Game> getGamesForId(String id) {
		List<Game> games = new LinkedList<Game>();
		for (Game g : games)
			if (g.id.equals(id))
				games.add(g);
		return games;
	}

	public static List<String> getGameIDs() {
		List<String> ids = new LinkedList<String>();
		for (Game g : GAMES)
			ids.add(g.id);
		return ids;
	}

	public static List<String> getFullMapIds() {
		List<String> ids = new LinkedList<String>();
		for (Game g : GAMES)
			ids.add(g.id + ":" + g.mapId);
		return ids;
	}

	private final Plugin plugin;
	private final List<Player> players;
	private int currentPlayers;
	private final String id;
	private final String mapId;
	private final GameSignFormat signFormat;
	private final int maxPlayers;
	private final String[] extras;
	private GameStatus status = GameStatus.LOADING;

	protected Game(Plugin plugin, String id, String mapId, GameSignFormat signFormat, int maxPlayers,
			String... extras) {
		this.plugin = plugin;
		this.players = new LinkedList<Player>();
		this.currentPlayers = 0;
		this.id = id;
		this.mapId = mapId;
		this.signFormat = signFormat;
		this.maxPlayers = maxPlayers;
		this.extras = extras;
		List<Game> games = getGamesForId(id);
		for (Game g : games)
			if (g.id.equals(id) && !g.plugin.equals(plugin))
				throw new IllegalArgumentException("this game id is already in use");
		for (Game g : games)
			if (g.mapId.equals(mapId))
				throw new IllegalArgumentException("this map id is already in use");
		GAMES.add(this);
	}

	public Plugin getPlugin() {
		return this.plugin;
	}

	public List<Player> getPlayers() {
		return this.players;
	}

	public final boolean addPlayer(Player player) {
		if (this.currentPlayers == this.maxPlayers)
			return false;
		for (Player p : players)
			if (p != null && p.equals(player))
				return false;
		this.currentPlayers++;
		this.players.add(player);
		GameJoinEvent event = new GameJoinEvent(this, player, "");
		SelimCore.callEvent(event);
		return true;
	}

	public final boolean removePlayer(Player player) {
		boolean removed = this.players.remove(player);
		if (removed) {
			this.currentPlayers--;
			GameLeaveEvent event = new GameLeaveEvent(this, player);
			SelimCore.callEvent(event);
			return true;
		}
		return false;
	}

	public final boolean isPlayerInGame(Player player) {
		if (player == null)
			return false;
		for (Player p : this.players)
			if (p.equals(player))
				return true;
		return false;
	}

	public int getCurrentPlayers() {
		return this.currentPlayers;
	}

	public int getMaxPlayers() {
		return this.maxPlayers;
	}

	public boolean isFull() {
		return this.currentPlayers == this.maxPlayers;
	}

	public String getId() {
		return this.id;
	}

	public String getMapName() {
		return this.mapId;
	}

	public GameSignFormat getSignFormat() {
		return this.signFormat;
	}

	public String[] getExtras() {
		return this.extras;
	}

	public GameStatus getStatus() {
		return this.status;
	}

	public final void setStatus(GameStatus status) {
		if (status != null) {
			GameStatus oldStatus = this.status;
			this.status = status;
			GameStatusChangeEvent event = new GameStatusChangeEvent(this, oldStatus, status);
			SelimCore.callEvent(event);
		}
	}

	public void broadcast(String message) {
		for (Player p : players)
			p.sendMessage(message);
	}

	public abstract void joinGame(Player player);

	public abstract void leaveGame(Player player);

	public abstract void startGame();

	public abstract void resetGame();

	@Override
	public String toString() {
		return this.plugin.getName() + ":" + this.id + ":" + this.mapId;
	}

}

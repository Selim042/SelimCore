package selim.core.leaderboards;

import java.io.Serializable;

import org.bukkit.entity.Player;

public class Score<T extends Comparable<T> & Serializable> implements Comparable<T> {

	private final ScoreTracker<T> tracker;
	private final Player player;
	private T data;

	protected Score(ScoreTracker<T> tracker, Player player) {
		this(tracker, player, null);
	}

	protected Score(ScoreTracker<T> tracker, Player player, T data) {
		this.tracker = tracker;
		this.player = player;
		this.data = data;
	}

	public Player getPlayer() {
		return this.player;
	}

	public T updateScore(T data) {
		this.data = data;
		tracker.sort();
		return data;
	}

	public T getScore() {
		return this.data;
	}

	@Override
	public int compareTo(T o) {
		return data.compareTo(o);
	}

}

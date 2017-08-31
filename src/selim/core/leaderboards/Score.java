package selim.core.leaderboards;

import org.bukkit.entity.Player;

public class Score {

	private final ScoreTracker tracker;
	private final Player player;
	private int data;

	protected Score(ScoreTracker tracker, Player player) {
		this(tracker, player, 0);
	}

	protected Score(ScoreTracker tracker, Player player, int data) {
		this.tracker = tracker;
		this.player = player;
		this.data = data;
	}

	public Player getPlayer() {
		return this.player;
	}

	public int updateScore(int data) {
		this.data = data;
		tracker.sort();
		return data;
	}

	public int getScore() {
		return this.data;
	}

}

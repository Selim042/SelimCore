package selim.core.leaderboards;

import org.bukkit.entity.Player;

public class Score implements Comparable<Score> {

	private final ScoreTracker tracker;
	private final Player player;
	private int score;

	protected Score(ScoreTracker tracker, Player player) {
		this(tracker, player, 0);
	}

	protected Score(ScoreTracker tracker, Player player, int data) {
		this.tracker = tracker;
		this.player = player;
		this.score = data;
	}

	public Player getPlayer() {
		return this.player;
	}

	public int updateScore(int data) {
		this.score = data;
		tracker.sort();
		return data;
	}

	public int getScore() {
		return this.score;
	}

	@Override
	public int compareTo(Score score) {
		if (score.score == this.score)
			return 0;
		return score.score < this.score ? -1 : 1;
	}

	@Override
	public String toString() {
		return this.player == null ? "NO PLAYER:" + this.score
				: this.player.getDisplayName() + ":" + this.score;
	}

}

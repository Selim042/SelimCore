package selim.core.leaderboards;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class Score implements Comparable<Score> {

	private final ScoreTracker tracker;
	private final UUID playerUUID;
	private int score;

	protected Score(ScoreTracker tracker, Player player) {
		this(tracker, player, 0);
	}

	protected Score(ScoreTracker tracker, Player player, int data) {
		this(tracker, player == null ? null : player.getUniqueId(), data);
	}

	protected Score(ScoreTracker tracker, UUID playerUUID) {
		this(tracker, playerUUID, 0);
	}

	protected Score(ScoreTracker tracker, UUID playerUUID, int data) {
		this.tracker = tracker;
		this.playerUUID = playerUUID;
		this.score = data;
	}

	public UUID getUUID() {
		return this.playerUUID;
	}

	public OfflinePlayer getPlayer() {
		return Bukkit.getOfflinePlayer(this.playerUUID);
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
		return this.playerUUID == null ? "NO PLAYER:" + this.score : this.playerUUID + ":" + this.score;
	}

}

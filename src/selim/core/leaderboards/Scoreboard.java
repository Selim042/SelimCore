package selim.core.leaderboards;

import org.bukkit.Location;

public class Scoreboard<T extends ScoreTracker<?>> {

	private final Location loc;
	private final String id;
	private final int place;

	public Scoreboard(Location loc, String trackerId, int place) {
		this.loc = loc;
		this.id = trackerId;
		this.place = place;
	}

	public int getPlace() {
		return this.place;
	}

	public String getTrackerID() {
		return this.id;
	}

	public Location getLocation() {
		return this.loc;
	}

}

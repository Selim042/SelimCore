package selim.core.leaderboards;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;

import selim.core.Helper;

public class Scoreboard {

	private final Location loc;
	private final BlockFace facing;
	private final String id;
	private final int place;

	public Scoreboard(Location loc, BlockFace facing, String trackerId, int place) {
		this.loc = loc;
		this.facing = facing;
		this.id = trackerId;
		this.place = place;
	}

	public Location getLocation() {
		return this.loc;
	}

	public BlockFace getFacing() {
		return this.facing;
	}

	public String getTrackerID() {
		return this.id;
	}

	public int getPlace() {
		return this.place;
	}

	public void update() {
		ScoreTracker tracker = ScoreTracker.getTracker(this.id);
		Score score = tracker.getPlace(this.place);
		Player player = null;
		if (score != null)
			player = score.getPlayer();
		Block signBlock = loc.getBlock();
		if (signBlock.getType() == Material.WALL_SIGN) {
			Sign sign = (Sign) signBlock.getState();
			sign.setLine(0, "[" + tracker.getName() + "]");
			sign.setLine(1, getPlaceString());
			if (player != null) {
				sign.setLine(2, player.getDisplayName());
				sign.setLine(3, getScoreString());
			} else {
				sign.setLine(2, "");
				sign.setLine(3, "");
			}
			sign.update(true);
			Location skullLoc = Helper.offsetLocation(this.loc.clone().add(0, 1, 0),
					this.facing.getOppositeFace());
			Block skullBlock = skullLoc.getBlock();
			if (skullBlock.getType() == Material.SKULL) {
				Skull skull = (Skull) skullBlock.getState();
				if (player == null) {
					skull.setSkullType(SkullType.SKELETON);
				} else {
					skull.setSkullType(SkullType.PLAYER);
					skull.setOwningPlayer(player);
				}
				skull.update(true);
			}
		}
	}

	private String getPlaceString() {
		String placeString = Integer.toString(this.place);
		switch (this.place) {
		case 1:
			placeString += "st";
			break;
		case 2:
			placeString += "nd";
			break;
		case 3:
			placeString += "rd";
			break;
		default:
			placeString += "th";
			break;
		}
		placeString += " Place";
		return placeString;
	}

	private String getScoreString() {
		String scoreString = "";
		ScoreTracker tracker = ScoreTracker.getTracker(this.id);
		Score score = tracker.getPlace(this.place);
		String scoreS = Integer.toString(score.getScore());
		for (int i = 0; i < scoreS.length(); i++) {
			if ((i + 1) % 4 == 0)
				scoreString += ',';
			scoreString += scoreS.charAt(i);
		}
		return scoreString;
	}

}

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
	private final boolean withHead;

	public Scoreboard(Location loc, BlockFace facing, String trackerId, int place) {
		this(loc, facing, trackerId, place, false);
	}

	public Scoreboard(Location loc, BlockFace facing, String trackerId, int place, boolean withHead) {
		this.loc = loc;
		this.facing = facing;
		this.id = trackerId;
		this.place = place;
		this.withHead = withHead;
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

	@SuppressWarnings("deprecation")
	public void update() {
		ScoreTracker tracker = ScoreTracker.getTracker(this.id);
		Score score = tracker.getPlace(this.place);
		Player player = score.getPlayer();
		Block signBlock = loc.getBlock();
		signBlock.setType(Material.WALL_SIGN);
		Sign sign = (Sign) signBlock.getState();
		signBlock.setData(getSignFacingByte());
		sign.setLine(0, "[" + tracker.getName() + "]");
		sign.setLine(1, getPlaceString());
		sign.setLine(2, player.getDisplayName());
		sign.setLine(3, player.getDisplayName());
		if (withHead) {
			Location skullLoc = Helper.offsetLocation(this.loc, this.facing.getOppositeFace());
			Block skullBlock = skullLoc.getBlock();
			skullBlock.setType(Material.SKULL);
			Skull skull = (Skull) skullBlock.getState();
			skull.setSkullType(SkullType.PLAYER);
			skull.setOwningPlayer(player);
			skull.setRotation(this.facing);
		}
	}

	private byte getSignFacingByte() {
		switch (this.facing) {
		case EAST:
			return 5;
		case NORTH:
			return 2;
		case SOUTH:
			return 3;
		case WEST:
			return 4;
		default:
			return 0;
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

}

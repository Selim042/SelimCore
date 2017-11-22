package selim.core.leaderboards;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.SkullType;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.block.Skull;

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
		OfflinePlayer player = null;
		if (score != null)
			player = score.getPlayer();
		Block signBlock = loc.getBlock();
		if (signBlock.getType() == Material.WALL_SIGN) {
			Sign sign = (Sign) signBlock.getState();
			if (sign == null)
				return;
			ScoreboardSignFormat format = tracker.getFormat();
			if (format == null)
				return;
			String pluginName = tracker.getPluginName();
			String[] extras = tracker.getExtras();
			sign.setLine(0, "[" + tracker.getPluginName() + "]");
			sign.setLine(1, format.formatLine2(score, place, pluginName, extras));
			sign.setLine(2, format.formatLine3(score, place, pluginName, extras));
			sign.setLine(3, format.formatLine4(score, place, pluginName, extras));
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

}

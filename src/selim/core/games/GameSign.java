package selim.core.games;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;

import selim.core.Helper;

public class GameSign {

	private final Location loc;
	private final BlockFace facing;
	private final Game game;

	public GameSign(Location loc, BlockFace facing, Game game) {
		this.loc = loc;
		this.facing = facing;
		this.game = game;
	}

	public Location getLocation() {
		return this.loc;
	}

	public BlockFace getFacing() {
		return this.facing;
	}

	public Game getGame() {
		return this.game;
	}

	@SuppressWarnings("deprecation")
	public void update() {
		Block signBlock = loc.getBlock();
		if (signBlock.getType() == Material.WALL_SIGN) {
			Sign sign = (Sign) signBlock.getState();
			if (sign == null)
				return;
			GameSignFormat format = game.getSignFormat();
			if (format == null)
				return;
			sign.setLine(0, format.formatLine1(game));
			sign.setLine(1, format.formatLine2(game));
			sign.setLine(2, format.formatLine3(game));
			sign.setLine(3, format.formatLine4(game));
			sign.update(true);
			Location colorLoc = Helper.offsetLocation(this.loc.clone(), this.facing.getOppositeFace());
			Block colorBlock = colorLoc.getBlock();
			Material colorMat = colorBlock.getType();
			if (colorMat == Material.STAINED_CLAY || colorMat == Material.STAINED_GLASS
					|| colorMat == Material.CONCRETE || colorMat == Material.WOOL)
				colorBlock.setData(game.getStatus().getBlockColor().getWoolData());
		}
	}

}

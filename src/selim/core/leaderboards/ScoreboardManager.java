package selim.core.leaderboards;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import selim.core.events.GameTickEvent;

public class ScoreboardManager implements Listener {

	private static final List<Scoreboard> BOARDS = new LinkedList<Scoreboard>();

	@EventHandler
	public void updateScoreBoards(GameTickEvent event) {
		for (Scoreboard sb : BOARDS) {
			Block block = sb.getLocation().getBlock();
			BlockState state = block.getState();
			if (state instanceof Sign) {
				Sign sign = (Sign) state;
				ScoreTracker st = ScoreTracker.getTracker(sb.getTrackerID());
				if (st == null || !st.hasUpdated())
					return;
				int place = sb.getPlace();
				Score score = st.getPlace(place);
				sign.setLine(0,
						ChatColor.GOLD + "[ " + ChatColor.RESET + st.getName() + ChatColor.GOLD + " ]");
				sign.setLine(1, score + " place");
				sign.setLine(2, score.getPlayer().getDisplayName());
				sign.setLine(3, score.toString());
			} else {
				BOARDS.remove(sb);
			}
		}
	}

}
